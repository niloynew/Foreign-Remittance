package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.CBFundSource;
import com.mislbd.ababil.foreignremittance.service.CBFundSourceService;
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
@RequestMapping(path = "cb-fund-sources", produces = MediaType.APPLICATION_JSON_VALUE)
public class CBFundSourceController {
  private final CBFundSourceService cbFundSourceService;

  public CBFundSourceController(CBFundSourceService cbFundSourceService) {
    this.cbFundSourceService = cbFundSourceService;
  }

  @GetMapping
  public ResponseEntity<?> getFundSources(
      Pageable pageable,
      @RequestParam(required = false, name = "id") Long id,
      @RequestParam(name = "asPage") final boolean asPage) {
    if (asPage) {
      PagedResult<CBFundSource> pagedResults = cbFundSourceService.getFundSources(pageable, id);
      return ResponseEntity.ok(pagedResults);
    } else {
      List<CBFundSource> fundSources = cbFundSourceService.getFundSources(id);
      return ResponseEntity.ok(fundSources);
    }
  }
}
