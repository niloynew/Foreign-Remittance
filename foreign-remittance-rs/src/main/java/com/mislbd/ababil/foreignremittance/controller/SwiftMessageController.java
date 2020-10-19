package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.CreateGenerateSingleCustomerCreditTransferMessageCommand;
import com.mislbd.ababil.foreignremittance.command.CreatePublishSingleCustomerCreditTransferMessageCommand;
import com.mislbd.ababil.foreignremittance.command.CreateValidateSingleCustomerCreditTransferMessageCommand;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.swift.broker.model.MessageResponse;
import com.mislbd.swift.broker.model.ProcessResult;
import com.mislbd.swift.broker.model.raw.mt1xx.MT103MessageRequest;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SwiftMessageController {
  private final CommandProcessor commandProcessor;
  private final QueryManager queryManager;

  public SwiftMessageController(CommandProcessor commandProcessor, QueryManager queryManager) {
    this.commandProcessor = commandProcessor;
    this.queryManager = queryManager;
  }

  @PostMapping(path = "/publish-mt103-message", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> publishSingleCustomerCreditTransferMessage(
      @RequestBody @Valid MT103MessageRequest mt103MessageRequest) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(
                new CreatePublishSingleCustomerCreditTransferMessageCommand(mt103MessageRequest)));
  }

  @PostMapping(path = "/validate-mt103-message", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<ProcessResult>> validateSingleCustomerCreditTransferMessage(
      @RequestBody @Valid MT103MessageRequest mt103MessageRequest) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(
                new CreateValidateSingleCustomerCreditTransferMessageCommand(mt103MessageRequest)));
  }

    @PostMapping(path = "/generate-mt103-message", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandResponse<MessageResponse>> generateSingleCustomerCreditTransferMessage(
            @RequestBody @Valid MT103MessageRequest mt103MessageRequest) {
        return status(CREATED)
                .body(
                        commandProcessor.executeResult(
                                new CreateGenerateSingleCustomerCreditTransferMessageCommand(mt103MessageRequest)));
    }



}
