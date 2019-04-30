package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.SaveInwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.service.RemittanceTransactionService;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.commons.data.domain.PagedResult;
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
  private final RemittanceTransactionService remittanceTransactionService;

  public RemittanceTransactionController(
      CommandProcessor commandProcessor,
      RemittanceTransactionService remittanceTransactionService) {
    this.commandProcessor = commandProcessor;
    this.remittanceTransactionService = remittanceTransactionService;
  }

  @GetMapping(path = "/remittance-transaction")
  public ResponseEntity<?> getTransactions(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "voucherNumber", required = false) final String voucherNumber,
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
    if (asPage) {
      PagedResult<RemittanceTransaction> pagedTransactions =
          remittanceTransactionService.getTransactions(
              pageable,
              voucherNumber,
              remittanceType,
              transactionReferenceNumber,
              applicantName,
              beneficiaryName,
              fromDate,
              toDate);
      return ResponseEntity.ok(pagedTransactions);
    } else {
      List<RemittanceTransaction> transactions =
          remittanceTransactionService.getTransactions(
              voucherNumber,
              remittanceType,
              transactionReferenceNumber,
              applicantName,
              beneficiaryName,
              fromDate,
              toDate);
      return ResponseEntity.ok(transactions);
    }
  }

  @PostMapping(path = "/inward-remittance-transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> disburseRemittanceFromBranch(
      @RequestBody @Valid RemittanceTransaction remittanceTransaction) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(
                new SaveInwardRemittanceTransactionCommand(remittanceTransaction)));
  }

  @PostMapping(
      path = "/outward-remittance-transaction",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> outgoingRemittanceFromBranch(
      @RequestBody @Valid RemittanceTransaction remittanceTransaction) {
    commandProcessor.executeResult(
        new SaveInwardRemittanceTransactionCommand(remittanceTransaction));
    return ResponseEntity.accepted().build();
  }
}
