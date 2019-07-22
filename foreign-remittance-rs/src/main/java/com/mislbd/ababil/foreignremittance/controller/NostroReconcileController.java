package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.UpdateNostroReconcileCommand;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDto;
import com.mislbd.ababil.foreignremittance.service.NostroReconcileServce;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class NostroReconcileController {

  private final NostroReconcileServce nostroReconcileServce;
  private final CommandProcessor commandProcessor;

  public NostroReconcileController(
      NostroReconcileServce nostroReconcileServce, CommandProcessor commandProcessor) {
    this.nostroReconcileServce = nostroReconcileServce;
    this.commandProcessor = commandProcessor;
  }

  /*@RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<PagedResult<RemittanceMsgDto>> getMessages(
      Pageable pageable,
      @RequestParam(required = false) String msgType,
      @RequestParam(required = false) String lcNo,
      @RequestParam(required = false) BigDecimal amount,
      @RequestParam(required = false) LocalDate valueDate) {

    PagedResult<RemittanceMsgDto> pagedMessages =
        (PagedResult<RemittanceMsgDto>)
            swiftMsgService.findAll(pageable, msgType, lcNo, amount, valueDate);
    return ResponseEntity.ok(pagedMessages);
  }*/
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<?> getMessages(
      Pageable pageable,
      @RequestParam(required = false) boolean asPage,
      @RequestParam(required = false) Long id,
      @RequestParam(required = false) String accountNo,
      @RequestParam(required = false) boolean selected,
      @RequestParam(required = false) LocalDate valueDate) {

    if (asPage) {
      PagedResult<NostroReconcileDto> pagedMessages =
          (PagedResult<NostroReconcileDto>)
              nostroReconcileServce.getMessages(pageable, id, accountNo, selected, valueDate);
      return ResponseEntity.ok(pagedMessages);
    } else {
      List<NostroReconcileDto> messages =
          nostroReconcileServce.getMessages(id, accountNo, selected, valueDate);
      return ResponseEntity.ok(messages);
    }
  }

  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateMessage(
      @PathVariable("id") Long id, @Valid @RequestBody NostroReconcileDto nostroReconcileDto) {
    commandProcessor.executeUpdate(new UpdateNostroReconcileCommand(id, nostroReconcileDto));
    return status(ACCEPTED).build();
  }
}
