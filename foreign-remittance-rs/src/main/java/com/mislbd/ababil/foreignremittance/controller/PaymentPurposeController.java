package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.PaymentPurpose;
import com.mislbd.ababil.foreignremittance.service.PaymentPurposeService;
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
@RequestMapping(path = "payment-purposes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentPurposeController {

  private final PaymentPurposeService paymentPurposeService;

  public PaymentPurposeController(PaymentPurposeService paymentPurposeService) {
    this.paymentPurposeService = paymentPurposeService;
  }

  @GetMapping
  public ResponseEntity<?> getPaymentPurposes(
      Pageable pageable,
      @RequestParam(required = false, name = "id") Long id,
      @RequestParam(name = "asPage") final boolean asPage) {
    if (asPage) {
      PagedResult<PaymentPurpose> pagedResults =
          paymentPurposeService.getPaymentPurposes(pageable, id);
      return ResponseEntity.ok(pagedResults);
    } else {
      List<PaymentPurpose> types = paymentPurposeService.getPaymentPurposes(id);
      return ResponseEntity.ok(types);
    }
  }
}
