package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;

import com.mislbd.ababil.foreignremittance.command.SaveSenderOrReceiverCustomerCommand;
import com.mislbd.ababil.foreignremittance.domain.SenderOrReceiverCustomer;
import com.mislbd.ababil.foreignremittance.query.SenderOrReceiverCustomerByIdQuery;
import com.mislbd.ababil.foreignremittance.query.SenderOrReceiverCustomerQuery;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "sender-receiver-customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class SenderOrReceiverCustomerController {

  private final CommandProcessor commandProcessor;
  private final QueryManager queryManager;

  public SenderOrReceiverCustomerController(
      CommandProcessor commandProcessor, QueryManager queryManager) {
    this.commandProcessor = commandProcessor;
    this.queryManager = queryManager;
  }

  @GetMapping
  public ResponseEntity<?> getSenderOrReceiverCustomers(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "ownerName", required = false) String ownerName,
      @RequestParam(value = "address", required = false) String address,
      @RequestParam(value = "country", required = false) String country,
      @RequestParam(value = "cpName", required = false) String cpName,
      @RequestParam(value = "cpEmail", required = false) String cpEmail) {
    QueryResult<?> queryResult =
        queryManager.executeQuery(
            new SenderOrReceiverCustomerQuery(
                asPage, pageable, name, ownerName, address, country, cpName, cpEmail));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<?> getLc(@PathVariable("id") long id) {
    QueryResult<?> queryResult =
        queryManager.executeQuery(new SenderOrReceiverCustomerByIdQuery(id));

    return ResponseEntity.ok(queryResult.getResult());
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(ACCEPTED)
  public ResponseEntity<?> saveExportLC(
      @RequestBody SenderOrReceiverCustomer senderOrReceiverCustomer) {
    CommandResponse<SenderOrReceiverCustomer> customerCommandResponse =
        commandProcessor.executeResult(
            new SaveSenderOrReceiverCustomerCommand(senderOrReceiverCustomer));
    return ResponseEntity.accepted().body(customerCommandResponse);
  }
}
