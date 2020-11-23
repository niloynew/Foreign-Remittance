package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;
import com.mislbd.ababil.foreignremittance.command.CreateInwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.CreateOutwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.query.Mt103RequestRemittanceTransactionIdQuery;
import com.mislbd.ababil.foreignremittance.query.RemittanceTransactionIdQuery;
import com.mislbd.ababil.foreignremittance.query.RemittanceTransactionQuery;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import java.time.LocalDate;
import javax.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RemittanceTransactionController {

  private final CommandProcessor commandProcessor;
  private final QueryManager queryManager;
  private QueryResult<?> queryResult;

  public RemittanceTransactionController(
      CommandProcessor commandProcessor, QueryManager queryManager) {
    this.commandProcessor = commandProcessor;
    this.queryManager = queryManager;
  }

  @GetMapping(path = "/remittance-transaction")
  public ResponseEntity<?> getTransactions(
      @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "globalTransactionNo", required = false)
          final String globalTransactionNo,
      @RequestParam(value = "remittanceType", required = false) final RemittanceType remittanceType,
      @RequestParam(value = "transactionReferenceNumber", required = false)
          final String transactionReferenceNumber,
      @RequestParam(value = "applicant", required = false) final String applicantName,
      @RequestParam(value = "beneficiaryName", required = false) final String beneficiaryName,
      @RequestParam(value = "fromDate", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          final LocalDate fromDate,
      @RequestParam(value = "toDate", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          final LocalDate toDate) {
    pageNumber = pageNumber != null ? pageNumber : 0;
    Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by("globalTransactionNo").descending());

    QueryResult<?> queryResult =
        queryManager.executeQuery(
            new RemittanceTransactionQuery(
                asPage,
                pageable,
                globalTransactionNo,
                remittanceType,
                transactionReferenceNumber,
                applicantName,
                beneficiaryName,
                fromDate,
                toDate));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @GetMapping(path = "/remittance-transaction/{transactionId}")
  public ResponseEntity<?> getTransaction(@PathVariable("transactionId") Long transactionId) {
    QueryResult<?> queryResult =
        queryManager.executeQuery(new RemittanceTransactionIdQuery(transactionId));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @PostMapping(path = "/inward-remittance-transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> disburseRemittanceFromBranch(
      @RequestBody @Valid RemittanceTransaction remittanceTransaction) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(
                new CreateInwardRemittanceTransactionCommand(remittanceTransaction)));
  }

  @PostMapping(
      path = "/outward-remittance-transaction",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> outgoingRemittanceFromBranch(
      @RequestBody @Valid RemittanceTransaction remittanceTransaction) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(
                new CreateOutwardRemittanceTransactionCommand(remittanceTransaction)));
  }

  @GetMapping(path = "/outward-remittance-transaction/{transactionId}")
  public ResponseEntity<?> getMt103RequestByRemittanceTransactionId(
      @PathVariable("transactionId") Long transactionId) {

    QueryResult<?> queryResult =
        queryManager.executeQuery(new Mt103RequestRemittanceTransactionIdQuery(transactionId));
    return ResponseEntity.ok(queryResult.getResult());
  }
}
