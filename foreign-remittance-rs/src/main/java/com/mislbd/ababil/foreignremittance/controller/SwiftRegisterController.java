package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;

import com.mislbd.ababil.foreignremittance.command.UpdateSwiftRegisterCommand;
import com.mislbd.ababil.foreignremittance.domain.SwiftRegister;
import com.mislbd.ababil.foreignremittance.query.RemittanceTransactionIdQuery;
import com.mislbd.ababil.foreignremittance.query.SwiftRegisterIdQuery;
import com.mislbd.ababil.foreignremittance.query.SwiftRegisterQuery;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import com.mislbd.swift.broker.model.RoutingStatus;

import java.util.Date;

import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getSwiftRegisters(
            Pageable pageable,
            @RequestParam(value = "asPage", required = false) final boolean asPage,
            @RequestParam(value = "referenceNo", required = false) final String referenceNo,
            @RequestParam(value = "senderAddress", required = false) final String senderAddress,
            @RequestParam(value = "receiverAddress", required = false) final String receiverAddress,
            @RequestParam(value = "status", required = false) final RoutingStatus status,
            @RequestParam(value = "messageRoutingDateTimeFrom", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final Date messageRoutingDateTimeFrom,
            @RequestParam(value = "messageRoutingDateTimeTo", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final Date messageRoutingDateTimeTo) {

        QueryResult<?> queryResult =
                queryManager.executeQuery(
                        new SwiftRegisterQuery(
                                pageable,
                                asPage,
                                referenceNo,
                                senderAddress,
                                receiverAddress,
                                status,
                                messageRoutingDateTimeFrom,
                                messageRoutingDateTimeTo));
        if (queryResult.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(queryResult.getResult());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(ACCEPTED)
    public ResponseEntity<?> updateRegister(@RequestBody SwiftRegister register) {
        commandProcessor.executeResult(new UpdateSwiftRegisterCommand(register));
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/swift-register/{registerId}")
    public ResponseEntity<?> getRegister(@PathVariable("registerId") Long registerId) {
        QueryResult<?> queryResult =
                queryManager.executeQuery(new SwiftRegisterIdQuery(registerId));
        return ResponseEntity.ok(queryResult.getResult());
    }



}
