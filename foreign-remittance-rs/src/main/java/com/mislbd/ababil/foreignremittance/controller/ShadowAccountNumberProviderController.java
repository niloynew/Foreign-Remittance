package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.service.IDProductService;
import com.mislbd.ababil.foreignremittance.service.ShadowAccountNumberProviderService;
import com.mislbd.security.core.NgSession;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
    path = "/id-products/{productId}/shadow-accounts/",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class ShadowAccountNumberProviderController {

  private final ShadowAccountNumberProviderService shadowAccountNumberProviderService;
  private final IDProductService idProductService;
  private final NgSession ngSession;

  public ShadowAccountNumberProviderController(
      ShadowAccountNumberProviderService shadowAccountNumberProviderService,
      IDProductService idProductService,
      NgSession ngSession) {
    this.shadowAccountNumberProviderService = shadowAccountNumberProviderService;
    this.idProductService = idProductService;
    this.ngSession = ngSession;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<String> getShadowAccount(
      @PathVariable("productId") long productId, @RequestParam(name = "branchId") long branchId) {

    if (idProductService.isExists(productId)) {
      return ResponseEntity.ok(
          shadowAccountNumberProviderService.getAccountNumber(
              productId, ngSession.getUserBranch(), ngSession.getUsername()));
    }

    return ResponseEntity.notFound().build();
  }
}
