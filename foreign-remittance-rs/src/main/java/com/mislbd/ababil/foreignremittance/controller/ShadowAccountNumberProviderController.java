package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.query.ShadowAccountNumberQuery;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
    path = "/id-products/{productId}/shadow-accounts/",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class ShadowAccountNumberProviderController {

  private final QueryManager queryManager;

  public ShadowAccountNumberProviderController(QueryManager queryManager) {
    this.queryManager = queryManager;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<?> getShadowAccount(
      @PathVariable("productId") long productId, @RequestParam(name = "branchId") long branchId) {
    QueryResult<?> queryResult =
        queryManager.executeQuery(new ShadowAccountNumberQuery(productId, branchId));
    return ResponseEntity.ok(queryResult.getResult());
  }
}
