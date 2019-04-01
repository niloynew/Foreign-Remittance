package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.service.HORateTypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "ho-rate-types", produces = MediaType.APPLICATION_JSON_VALUE)
public class HORateTypeController {

  private final HORateTypeService hoRateTypeService;

  public HORateTypeController(HORateTypeService hoRateTypeService) {
    this.hoRateTypeService = hoRateTypeService;
  }

  @GetMapping
  public ResponseEntity<?> getClientRates(
      Pageable pageable, @RequestParam(value = "asPage", required = false) final boolean asPage) {
    if (asPage) {
      return ResponseEntity.ok(hoRateTypeService.getClientRateTypes(pageable));
    } else {
      return ResponseEntity.ok(hoRateTypeService.getClientRateTypes());
    }
  }
}
