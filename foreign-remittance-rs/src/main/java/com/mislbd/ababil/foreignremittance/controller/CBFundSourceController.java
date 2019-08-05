package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.query.CBFundSourceQuery;
import com.mislbd.ababil.foreignremittance.service.CBFundSourceService;
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
@RequestMapping(path = "cb-fund-sources", produces = MediaType.APPLICATION_JSON_VALUE)
public class CBFundSourceController {
  private final CBFundSourceService cbFundSourceService;
  private final QueryManager queryManager;

  public CBFundSourceController(
      CBFundSourceService cbFundSourceService, QueryManager queryManager) {
    this.cbFundSourceService = cbFundSourceService;
    this.queryManager = queryManager;
  }

  @GetMapping
  public ResponseEntity<?> getFundSources(
      Pageable pageable,
      @RequestParam(required = false, name = "id") Long id,
      @RequestParam(name = "asPage") final boolean asPage) {
    QueryResult<?> queryResult =
        queryManager.executeQuery(new CBFundSourceQuery(pageable, id, asPage));
    return ResponseEntity.ok(queryResult);
  }
}
