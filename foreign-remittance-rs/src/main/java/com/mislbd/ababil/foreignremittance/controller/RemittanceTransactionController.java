package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.command.SaveRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.asset.command.api.CommandProcessor;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RemittanceTransactionController {

  private final CommandProcessor commandProcessor;

  public RemittanceTransactionController(CommandProcessor commandProcessor) {
    this.commandProcessor = commandProcessor;
  }

  @PostMapping(path = "/inward-remittance-transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> disburseRemittanceFromBranch(
      @RequestBody @Valid RemittanceTransaction remittanceTransaction) {
    commandProcessor.executeResult(new SaveRemittanceTransactionCommand(remittanceTransaction));
    return ResponseEntity.accepted().build();
  }

  @PostMapping(
      path = "/outward-remittance-transaction",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> outgoingRemittanceFromBranch(
      @RequestBody @Valid RemittanceTransaction remittanceTransaction) {
    commandProcessor.executeResult(new SaveRemittanceTransactionCommand(remittanceTransaction));
    return ResponseEntity.accepted().build();
  }
}
