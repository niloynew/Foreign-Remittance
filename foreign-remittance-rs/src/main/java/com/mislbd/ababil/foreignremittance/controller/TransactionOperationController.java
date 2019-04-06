package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.domain.TransactionOperation;
import com.mislbd.ababil.foreignremittance.service.TransactionOperationService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/operations", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionOperationController {

  private final TransactionOperationService transactionOperationService;

  public TransactionOperationController(TransactionOperationService transactionOperationService) {
    this.transactionOperationService = transactionOperationService;
  }

  @GetMapping
  public ResponseEntity<?> getTransactionOperations(
      Pageable pageable,
      @RequestParam(value = "typeId") final long typeId,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "remittance-type") final RemittanceType remittanceType) {
    if (asPage) {
      PagedResult<TransactionOperation> pagedResults =
          transactionOperationService.getOperations(pageable, typeId, remittanceType);
      return ResponseEntity.ok(pagedResults);
    } else {
      List<TransactionOperation> operations =
          transactionOperationService.getOperations(typeId, remittanceType);
      return ResponseEntity.ok(operations);
    }
  }
}
