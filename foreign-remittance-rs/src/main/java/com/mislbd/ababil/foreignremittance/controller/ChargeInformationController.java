package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeInformation;
import com.mislbd.ababil.foreignremittance.service.RemittanceChargeInformationService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/remittance-charge-info", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChargeInformationController {

  private final RemittanceChargeInformationService remittanceChargeInformationService;

  public ChargeInformationController(
      RemittanceChargeInformationService remittanceChargeInformationService) {
    this.remittanceChargeInformationService = remittanceChargeInformationService;
  }

  @GetMapping
  public ResponseEntity<List<RemittanceChargeInformation>> getCharges(
      @RequestParam(value = "typeId") Long typeId,
      @RequestParam(value = "account-number", required = false) String accountNumber,
      @RequestParam(value = "amount", required = false) BigDecimal amount) {
    return ResponseEntity.ok(
        remittanceChargeInformationService.getChargeInfo(typeId, accountNumber, amount));
  }
}
