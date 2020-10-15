package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.command.CreatePublishSingleCustomerCreditTransferMessageCommand;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.swift.broker.model.raw.mt1xx.MT103MessageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SwiftMessageController {
    private final CommandProcessor commandProcessor;
    private final QueryManager queryManager;

    public SwiftMessageController(
            CommandProcessor commandProcessor, QueryManager queryManager) {
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

}