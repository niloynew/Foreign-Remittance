package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.query.PaymentPurposeQuery;
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
@RequestMapping(path = "payment-purposes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentPurposeController {

  private final QueryManager queryManager;

  public PaymentPurposeController(QueryManager queryManager) {
    this.queryManager = queryManager;
  }

  @GetMapping
  public ResponseEntity<?> getPaymentPurposes(
      Pageable pageable,
      @RequestParam(required = false, name = "id") Long id,
      @RequestParam(name = "asPage") final boolean asPage,
      @RequestParam(value = "code", required = false) final String code,
      @RequestParam(value = "description", required = false) final String description) {
    QueryResult<?> queryResult =
        queryManager.executeQuery(new PaymentPurposeQuery(asPage, pageable, id, code, description));
    return ResponseEntity.ok(queryResult);
  }
}
