package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.AuthorizeSingleCustomerCreditTransferMessageCommand;
import com.mislbd.ababil.foreignremittance.command.CreateSingleCustomerCreditTransferMessageCommand;
import com.mislbd.ababil.foreignremittance.command.GenerateSingleCustomerCreditTransferMessageCommand;
import com.mislbd.ababil.foreignremittance.command.PublishSingleCustomerCreditTransferMessageCommand;
import com.mislbd.ababil.foreignremittance.domain.XmmRequest;
import com.mislbd.ababil.foreignremittance.service.SwiftMsgService;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.swift.broker.model.MessageResponse;
import com.mislbd.swift.broker.model.ProcessResult;
import com.mislbd.swift.broker.model.raw.mt1xx.MT103MessageRequest;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SwiftMessageController {
  private final CommandProcessor commandProcessor;
  private final QueryManager queryManager;
  private SwiftMsgService swiftMessageService;

  public SwiftMessageController(
      CommandProcessor commandProcessor,
      QueryManager queryManager,
      SwiftMsgService swiftMessageService) {
    this.commandProcessor = commandProcessor;
    this.queryManager = queryManager;
    this.swiftMessageService = swiftMessageService;
  }

  @PostMapping(path = "/publish-mt103-message", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> publishSingleCustomerCreditTransferMessage(
      @RequestBody @Valid MT103MessageRequest mt103MessageRequest) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(
                new PublishSingleCustomerCreditTransferMessageCommand(mt103MessageRequest)));
  }

  @PostMapping(
      path = "/authorize-mt103-message/{referenceNumber}",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> authorizeSingleCustomerCreditTransferMessage(
      @PathVariable("referenceNumber") String transactionReferenceNumber) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(
                new AuthorizeSingleCustomerCreditTransferMessageCommand(
                    transactionReferenceNumber)));
  }

  @PostMapping(path = "/save-mt103-message", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<ProcessResult>> saveSingleCustomerCreditTransferMessage(
      @RequestBody @Valid MT103MessageRequest mt103MessageRequest) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(
                new CreateSingleCustomerCreditTransferMessageCommand(mt103MessageRequest)));
  }

  @PostMapping(path = "/generate-mt103-message", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<MessageResponse>>
      generateSingleCustomerCreditTransferMessage(
          @RequestBody @Valid MT103MessageRequest mt103MessageRequest) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(
                new GenerateSingleCustomerCreditTransferMessageCommand(mt103MessageRequest)));
  }

  @PostMapping(path = "/publishXmm103", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> publishCategory7Message(@Valid @RequestBody XmmRequest xmmRequest)
      throws IOException {
    return ResponseEntity.ok(swiftMessageService.generateSwiftMT103(xmmRequest));
  }
}
