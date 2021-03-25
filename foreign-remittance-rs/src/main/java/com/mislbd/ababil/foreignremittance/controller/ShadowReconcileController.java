package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.RejectShadowTransactionRecordCommand;
import com.mislbd.ababil.foreignremittance.command.SettleShadowTransactionRecordCommand;
import com.mislbd.ababil.foreignremittance.domain.OtherCbsSystemSettlementStatus;
import com.mislbd.ababil.foreignremittance.domain.ShadowTransactionRecord;
import com.mislbd.ababil.foreignremittance.domain.ShadowTransactionRecordList;
import com.mislbd.ababil.foreignremittance.query.AbabilShadowTransactionQuery;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import java.time.LocalDate;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/shadow-txns", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShadowReconcileController {

  private final CommandProcessor commandProcessor;
  private final QueryManager queryManager;

  public ShadowReconcileController(CommandProcessor commandProcessor, QueryManager queryManager) {
    this.commandProcessor = commandProcessor;
    this.queryManager = queryManager;
  }

  @GetMapping(path = "/reconcile/txns")
  public ResponseEntity<?> getUnreconciledTransactions(
      Pageable pageable,
      @RequestParam(value = "accountNumber", required = false) String accountNumber,
      @RequestParam(value = "fromDate", required = false) LocalDate fromDate,
      @RequestParam(value = "toDate", required = false) LocalDate toDate,
      @RequestParam(value = "reconcileStatus", required = false)
          OtherCbsSystemSettlementStatus status) {
    QueryResult<?> queryResult =
        queryManager.executeQuery(
            new AbabilShadowTransactionQuery(pageable, accountNumber, fromDate, toDate, status));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @PostMapping(path = "/reconcile/txns", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Integer> reconcileMultipleTransaction(
      @Valid @RequestBody ShadowTransactionRecordList shadowTransactionRecordList) {
    CommandResponse<Integer> numberOfSuccessReconcile =
        commandProcessor.executeResult(
            new SettleShadowTransactionRecordCommand(shadowTransactionRecordList));
    return status(CREATED).body(numberOfSuccessReconcile.getContent());
  }

  @PutMapping(path = "/reconcile/txns/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> reconcileMultipleTransaction(
      @Valid @RequestBody ShadowTransactionRecord shadowTransactionRecord,
      @PathVariable("id") Long txnId) {
    commandProcessor.executeResult(
        new RejectShadowTransactionRecordCommand(shadowTransactionRecord, txnId));
    return ResponseEntity.accepted().build();
  }
}
