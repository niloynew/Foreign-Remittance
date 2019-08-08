package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.query.ChargeInformationQuery;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import java.math.BigDecimal;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/remittance-charge-info", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChargeInformationController {
  private final QueryManager queryManager;

  public ChargeInformationController(QueryManager queryManager) {
    this.queryManager = queryManager;
  }

  @GetMapping
  public ResponseEntity<?> getCharges(
      @RequestParam(value = "remittanceType", required = false) final RemittanceType remittanceType,
      @RequestParam(value = "typeId") Long typeId,
      @RequestParam(value = "account-number", required = false) String accountNumber,
      @RequestParam(value = "amount", required = false) BigDecimal amount) {
    QueryResult<?> queryResult =
        queryManager.executeQuery(
            new ChargeInformationQuery(remittanceType, typeId, accountNumber, amount));
    return ResponseEntity.ok(queryResult.getResult());
  }
}
