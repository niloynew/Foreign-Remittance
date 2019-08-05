package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.CreateRemittanceChargeCommand;
import com.mislbd.ababil.foreignremittance.command.UpdateRemittanceChargeCommand;
import com.mislbd.ababil.foreignremittance.domain.ChargeAccountType;
import com.mislbd.ababil.foreignremittance.domain.RemittanceCharge;
import com.mislbd.ababil.foreignremittance.query.ChargeConfigurationIdQuery;
import com.mislbd.ababil.foreignremittance.query.ChargeConfigurationQuery;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/remittance-charges", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChargeConfigurationController {

  private final CommandProcessor commandProcessor;
  private final QueryManager queryManager;

  public ChargeConfigurationController(
      CommandProcessor commandProcessor, QueryManager queryManager) {
    this.commandProcessor = commandProcessor;
    this.queryManager = queryManager;
  }

  @GetMapping
  public ResponseEntity<?> getRemittanceCharges(
      Pageable pageable,
      @RequestParam(value = "chargeName", required = false) final String chargeName,
      @RequestParam(value = "chargeAccountType", required = false)
          final ChargeAccountType chargeAccountType,
      @RequestParam(value = "vatAccountType", required = false)
          final ChargeAccountType vatAccountType,
      @RequestParam(value = "status", required = false) final Boolean status,
      @RequestParam(value = "asPage", required = false) final boolean asPage) {
    QueryResult<?> queryResult =
        queryManager.executeQuery(
            new ChargeConfigurationQuery(
                pageable, chargeName, chargeAccountType, vatAccountType, status, asPage));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<?> getChargeById(@PathVariable("id") long id) {

    QueryResult<?> queryResult = queryManager.executeQuery(new ChargeConfigurationIdQuery(id));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> saveCharge(
      @Valid @RequestBody RemittanceCharge charge) {
    return status(CREATED)
        .body(commandProcessor.executeResult(new CreateRemittanceChargeCommand(charge)));
  }

  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(ACCEPTED)
  public ResponseEntity<Void> updateCharge(
      @PathVariable("id") long id, @Valid @RequestBody RemittanceCharge charge) {
    commandProcessor.executeUpdate(new UpdateRemittanceChargeCommand(charge));
    return status(ACCEPTED).build();
  }
}
