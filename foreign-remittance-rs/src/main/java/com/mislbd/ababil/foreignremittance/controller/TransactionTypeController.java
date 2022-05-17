package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.query.TransactionTypeQuery;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
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

  private final QueryManager queryManager;

  public TransactionTypeController(QueryManager queryManager) {

    this.queryManager = queryManager;
  }

  @GetMapping
  public ResponseEntity<?> getTransactionTypes(
      Pageable pageable,
      @RequestParam(required = false, name = "id") Long id,
      @RequestParam(name = "asPage") final boolean asPage,
      @RequestParam(name = "remittance-type") final RemittanceType remittanceType) {
    QueryResult<?> queryResult =
        queryManager.executeQuery(new TransactionTypeQuery(pageable, id, asPage, remittanceType));
    return ResponseEntity.ok(queryResult.getResult());
  }
}
