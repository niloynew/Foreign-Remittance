package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.command.SaveNostroAccountCommand;
import com.mislbd.ababil.foreignremittance.command.SaveShadowAccountCommand;
import com.mislbd.ababil.foreignremittance.command.SaveSwiftRegisterCommand;
import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.domain.SwiftRegister;
import com.mislbd.ababil.foreignremittance.query.AccountQuery;
import com.mislbd.ababil.foreignremittance.query.SwiftRegisterQuery;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequestMapping(path = "/swift-registers", produces = MediaType.APPLICATION_JSON_VALUE)
public class SwiftRegisterController {

    private final CommandProcessor commandProcessor;
    private final QueryManager queryManager;

    public SwiftRegisterController(CommandProcessor commandProcessor, QueryManager queryManager) {
        this.commandProcessor = commandProcessor;
        this.queryManager = queryManager;
    }

    @GetMapping
    public ResponseEntity<?> getIDAccounts(
            Pageable pageable,
            @RequestParam(value = "asPage", required = false) final boolean asPage,
            @RequestParam(value = "referenceNo", required = false) final String referenceNo,
            @RequestParam(value = "senderAddress", required = false) final String senderAddress,
            @RequestParam(value = "receiverAddress", required = false) final String receiverAddress,
            @RequestParam(value = "status", required = false) final String status,
            @RequestParam(value = "messageRoutingDateTime", required = false) final Date messageRoutingDateTime
    ) {

        QueryResult<?> queryResult =
                queryManager.executeQuery(
                        new SwiftRegisterQuery(
                                pageable,
                                asPage,
                                referenceNo,
                                senderAddress,
                                receiverAddress,
                                status,
                                messageRoutingDateTime));
        if (queryResult.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(queryResult.getResult());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(ACCEPTED)
    public ResponseEntity<?> saveRegister(@RequestBody SwiftRegister register) {
        commandProcessor.executeResult(new SaveSwiftRegisterCommand(register));
        return ResponseEntity.accepted().build();
    }
}
