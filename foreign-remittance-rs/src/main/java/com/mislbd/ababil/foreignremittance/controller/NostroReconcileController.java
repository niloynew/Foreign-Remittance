package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.ProcessNostroReconcileCommand;
import com.mislbd.ababil.foreignremittance.command.UpdateNostroReconcileCommand;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDtoBroker;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDtoBrokerList;
import com.mislbd.ababil.foreignremittance.query.NostroReconcileQuery;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import java.time.LocalDate;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class NostroReconcileController {

  private final CommandProcessor commandProcessor;
  private final QueryManager queryManager;

  public NostroReconcileController(CommandProcessor commandProcessor, QueryManager queryManager) {
    this.commandProcessor = commandProcessor;
    this.queryManager = queryManager;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<?> getMessages(
      Pageable pageable,
      @RequestParam(required = false) boolean asPage,
      @RequestParam(required = false) Long id,
      @RequestParam(required = false) String advBranch,
      @RequestParam(required = false) String accountNo,
      @RequestParam(required = false) boolean selected,
      @RequestParam(required = false) LocalDate valueDate) {

    QueryResult<?> queryResult =
        queryManager.executeQuery(
            new NostroReconcileQuery(
                pageable, asPage, id, advBranch, accountNo, selected, valueDate));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateMessage(
      @PathVariable("id") Long id,
      @Valid @RequestBody NostroReconcileDtoBroker nostroReconcileDto) {
    commandProcessor.executeUpdate(new UpdateNostroReconcileCommand(id, nostroReconcileDto));
    return status(ACCEPTED).build();
  }

  @PutMapping(path = "/process", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Integer> processMultipleMessage(
      @Valid @RequestBody NostroReconcileDtoBrokerList nostroReconcileDtoList) {
    return ResponseEntity.ok(
        (Integer)
            commandProcessor
                .executeResult(new ProcessNostroReconcileCommand(nostroReconcileDtoList))
                .getContent());
  }
}
