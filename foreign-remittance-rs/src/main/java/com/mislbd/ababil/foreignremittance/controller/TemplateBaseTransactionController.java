package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.CbsTemplateTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.service.TemplateBaseTransactionService;
import com.mislbd.transaction.api.transaction.model.CbsTransaction;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/buildTransactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TemplateBaseTransactionController {

  private final TemplateBaseTransactionService transactionService;

  public TemplateBaseTransactionController(TemplateBaseTransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @RequestMapping(
      path = {"build"},
      method = {RequestMethod.POST},
      consumes = {"application/json"})
  public ResponseEntity<List<CbsTemplateTransaction>> buildTransaction(
      @RequestBody RemittanceTransaction remittanceTransaction) {
    return ResponseEntity.ok(transactionService.buildTransaction(remittanceTransaction));
  }
}
