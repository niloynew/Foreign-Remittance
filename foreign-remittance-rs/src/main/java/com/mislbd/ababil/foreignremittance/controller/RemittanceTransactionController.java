package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.CreateRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.RemittanceTransactionCorrectionCommand;
import com.mislbd.ababil.foreignremittance.domain.RemittanceCategory;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransactionStatus;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.query.RemittanceTransactionIdQuery;
import com.mislbd.ababil.foreignremittance.query.RemittanceTransactionQuery;
import com.mislbd.ababil.foreignremittance.service.RemittanceTransactionService;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
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
  private final RemittanceTransactionService remittanceTransactionService;

  public RemittanceTransactionController(
      CommandProcessor commandProcessor,
      QueryManager queryManager,
      RemittanceTransactionService remittanceTransactionService) {
    this.commandProcessor = commandProcessor;
    this.queryManager = queryManager;
    this.remittanceTransactionService = remittanceTransactionService;
  }

  @GetMapping(path = "/remittance-transaction")
  public ResponseEntity<?> getTransactions(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "status", required = false) final RemittanceTransactionStatus status,
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

    QueryResult<?> queryResult =
        queryManager.executeQuery(
            new RemittanceTransactionQuery(
                asPage,
                pageable,
                remittanceType,
                transactionReferenceNumber,
                applicantName,
                beneficiaryName,
                fromDate,
                toDate,
                status));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @GetMapping(path = "/remittance-transaction/{transactionId}")
  public ResponseEntity<?> getTransaction(@PathVariable("transactionId") Long transactionId) {
    QueryResult<?> queryResult =
        queryManager.executeQuery(new RemittanceTransactionIdQuery(transactionId));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @PostMapping(path = "/remittance-transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> remittanceTransactionFromBranch(
      @RequestBody @Valid RemittanceTransaction remittanceTransaction) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(
                new CreateRemittanceTransactionCommand(remittanceTransaction)));
  }

  @PostMapping(path = "/remittance-transaction/{id}/correction/{voucherNumber}")
  public ResponseEntity<CommandResponse<Void>> correctionRemittanceTransaction(
      @PathVariable("id") Long id, @PathVariable("voucherNumber") Long voucherNumber) {
    return status(ACCEPTED)
        .body(
            commandProcessor.executeResult(
                new RemittanceTransactionCorrectionCommand(voucherNumber, id)));
  }

  @GetMapping(path = "/remittance-categories")
  public ResponseEntity<List<RemittanceCategory>> getRemittanceCategories() {
    List<RemittanceCategory> categories = remittanceTransactionService.getRemittanceCategories();
    return categories.isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(categories);
  }

  @GetMapping(path = "/remittance-categories/{id}")
  public ResponseEntity<RemittanceCategory> getRemittanceCategories(@PathVariable("id") Long id) {
    return ResponseEntity.ok(remittanceTransactionService.getRemittanceCategoryById(id));
  }

  @GetMapping(path = "/reference-numbers/{branch}/{categoryId}")
  public ResponseEntity<?> generateTransactionReferenceNumber(
      @PathVariable("branch") Long branch, @PathVariable("categoryId") Long categoryId) {
    return ResponseEntity.ok(
        remittanceTransactionService.generateTransactionReferenceNumber(branch, categoryId));
  }
}
