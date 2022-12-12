package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.CreateRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.RemittanceTransactionCorrectionCommand;
import com.mislbd.ababil.foreignremittance.command.RemittanceTransactionUpdateCommand;
import com.mislbd.ababil.foreignremittance.domain.*;
import com.mislbd.ababil.foreignremittance.query.InwardTxnsByReferenceNumberQuery;
import com.mislbd.ababil.foreignremittance.query.RemittanceTransactionIdQuery;
import com.mislbd.ababil.foreignremittance.query.RemittanceTransactionQuery;
import com.mislbd.ababil.foreignremittance.service.RemittanceTransactionService;
import com.mislbd.ababil.foreignremittance.service.TransactionRegisterService;
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
  private final TransactionRegisterService transactionRegisterService;

  public RemittanceTransactionController(
      CommandProcessor commandProcessor,
      QueryManager queryManager,
      RemittanceTransactionService remittanceTransactionService,
      TransactionRegisterService transactionRegisterService) {
    this.commandProcessor = commandProcessor;
    this.queryManager = queryManager;
    this.remittanceTransactionService = remittanceTransactionService;
    this.transactionRegisterService = transactionRegisterService;
  }

  @GetMapping(path = "/remittance-transactions")
  public ResponseEntity<?> getTransactions(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "remittanceType", required = false) final RemittanceType remittanceType,
      @RequestParam(value = "transactionReferenceNumber", required = false)
          final String transactionReferenceNumber,
      @RequestParam(value = "fromDate", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          final LocalDate fromDate,
      @RequestParam(value = "toDate", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          final LocalDate toDate,
      @RequestParam(value = "status", required = false) final RemittanceTransactionStatus status,
      @RequestParam(value = "salesContractNumber", required = false)
          final String salesContractNumber) {

    QueryResult<?> queryResult =
        queryManager.executeQuery(
            new RemittanceTransactionQuery(
                asPage,
                pageable,
                remittanceType,
                transactionReferenceNumber,
                fromDate,
                toDate,
                status,
                salesContractNumber));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @GetMapping(path = "/remittance-transactions/{transactionId}")
  public ResponseEntity<?> getTransaction(@PathVariable("transactionId") Long transactionId) {
    QueryResult<?> queryResult =
        queryManager.executeQuery(new RemittanceTransactionIdQuery(transactionId));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @PostMapping(path = "/remittance-transactions", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> remittanceTransactionFromBranch(
      @RequestBody @Valid RemittanceTransaction remittanceTransaction) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(
                new CreateRemittanceTransactionCommand(remittanceTransaction)));
  }

  @PostMapping(path = "/remittance-transactions/{id}/correction/{voucherNumber}")
  public ResponseEntity<CommandResponse<Void>> correctionRemittanceTransaction(
      @PathVariable("id") Long id, @PathVariable("voucherNumber") Long voucherNumber) {
    return status(ACCEPTED)
        .body(
            commandProcessor.executeResult(
                new RemittanceTransactionCorrectionCommand(
                    new RemittanceTransactionCorrectionRequest(id, voucherNumber))));
  }

  @PutMapping(path = "/remittance-transactions")
  public ResponseEntity<Void> updateRemittanceInformation(
      @RequestBody RemittanceTransactionUpdateDto dto) {
    commandProcessor.executeUpdate(new RemittanceTransactionUpdateCommand(dto));
    return ResponseEntity.accepted().build();
  }

  @GetMapping(path = "/remittance-categories-list/{remittanceType}")
  public ResponseEntity<List<RemittanceCategory>> getRemittanceCategoriesList(
      @PathVariable("remittanceType") RemittanceType remittanceType) {
    List<RemittanceCategory> categories =
        remittanceTransactionService.getRemittanceCategories(remittanceType);
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

  /*@GetMapping(path = "/inward-reference-numbers/{branch}/{categoryId}")
  public ResponseEntity<?> generateInwardTransactionReferenceNumber(
      @PathVariable("branch") Long branch, @PathVariable("categoryId") Long categoryId) {
    return ResponseEntity.ok(
        remittanceTransactionService.generateInwardTransactionReferenceNumber(branch, categoryId));
  }*/

  @GetMapping(path = "remittance-transactions/registers/{transactionId}")
  public ResponseEntity<List<TransactionRegister>> getRegisterByRemittance(
      @PathVariable("transactionId") Long transactionId) {
    return ResponseEntity.ok(
        transactionRegisterService.findTransactionRegisterByRemittanceTransaction(transactionId));
  }

  @GetMapping(path = "/inward-remittance-txns/{referenceNumber}")
  public ResponseEntity<Boolean> isInwardTxnExistsWithReferenceNumber(
      @PathVariable("referenceNumber") String referenceNumber) {
    QueryResult<Boolean> queryResult =
        queryManager.executeQuery(new InwardTxnsByReferenceNumberQuery(referenceNumber));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @GetMapping(path = "/remittance-transactions/tf/outward")
  public ResponseEntity<?> getRemittanceListForTfOutward(
      @RequestParam(value = "customerId") Long customerId,
      @RequestParam(value = "currency") String currency,
      @RequestParam(value = "adviceNumber", required = false) String adviceNumber) {
    List<ExportRelatedRemittanceInformation> informationList =
        remittanceTransactionService.getRemittanceInformationForTf(
            customerId, currency, adviceNumber, RemittanceType.OUTWARD_REMITTANCE);
    return ResponseEntity.ok(informationList);
  }

  @GetMapping(path = "/remittance-transactions/tf/inward")
  public ResponseEntity<?> getRemittanceListForTfInward(
      @RequestParam(value = "customerId") Long customerId,
      @RequestParam(value = "currency") String currency,
      @RequestParam(value = "lcNumber", required = false) String lcNumber) {
    List<ExportRelatedRemittanceInformation> informationList =
        remittanceTransactionService.getRemittanceInformationForTf(
            customerId, currency, lcNumber, RemittanceType.INWARD_REMITTANCE);
    return ResponseEntity.ok(informationList);
  }
}
