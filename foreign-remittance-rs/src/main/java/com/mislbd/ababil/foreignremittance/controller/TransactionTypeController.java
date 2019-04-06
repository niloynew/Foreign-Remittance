package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.domain.TransactionType;
import com.mislbd.ababil.foreignremittance.service.TransactionTypeService;
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
@RequestMapping(path = "transaction-types", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionTypeController {

  private final TransactionTypeService transactionTypeService;

  public TransactionTypeController(TransactionTypeService transactionTypeService) {
    this.transactionTypeService = transactionTypeService;
  }

  @GetMapping
  public ResponseEntity<?> getTransactionTypes(
      Pageable pageable,
      @RequestParam(name = "asPage") final boolean asPage,
      @RequestParam(name = "remittance-type") final RemittanceType remittanceType) {
    if (asPage) {
      PagedResult<TransactionType> pagedResults =
          transactionTypeService.getTypes(pageable, remittanceType);
      return ResponseEntity.ok(pagedResults);
    } else {
      List<TransactionType> types = transactionTypeService.getTypes(remittanceType);
      return ResponseEntity.ok(types);
    }
  }
}
