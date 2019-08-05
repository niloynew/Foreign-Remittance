package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.query.BankTypeIdQuery;
import com.mislbd.ababil.foreignremittance.query.BankTypeQuery;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "bank-types", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankTypeController {

  private final QueryManager queryManager;

  public BankTypeController(QueryManager queryManager) {
    this.queryManager = queryManager;
  }

  @GetMapping
  public ResponseEntity<?> getBankTypes(
      Pageable pageable, @RequestParam(name = "asPage") final boolean asPage) {
    QueryResult<?> queryResult = queryManager.executeQuery(new BankTypeQuery(asPage, pageable));
    if (queryResult.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(queryResult.getResult());
  }

  @GetMapping(path = "/{bankTypeId}")
  public ResponseEntity<?> getBankTypes(@PathVariable("bankTypeId") long bankTypeId) {

    QueryResult<?> queryResult = queryManager.executeQuery(new BankTypeIdQuery(bankTypeId));
    if (queryResult.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(queryResult.getResult());
  }
}
