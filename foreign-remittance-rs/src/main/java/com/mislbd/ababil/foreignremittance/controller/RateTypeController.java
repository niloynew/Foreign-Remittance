package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.service.RateTypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "client-rate-types")
public class RateTypeController {

  private final RateTypeService rateTypeService;

  public RateTypeController(RateTypeService rateTypeService) {
    this.rateTypeService = rateTypeService;
  }

  @GetMapping
  public ResponseEntity<?> getClientRates(
      Pageable pageable, @RequestParam(value = "asPage", required = false) final boolean asPage) {
    if (asPage) {
      return ResponseEntity.ok(rateTypeService.getClientRateTypes(pageable));
    } else {
      return ResponseEntity.ok(rateTypeService.getClientRateTypes());
    }
  }
}
