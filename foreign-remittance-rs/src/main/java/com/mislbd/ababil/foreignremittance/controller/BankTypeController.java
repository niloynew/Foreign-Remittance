package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.domain.BankType;
import com.mislbd.ababil.foreignremittance.service.BankTypeService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "bank-types", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankTypeController {

  private final BankTypeService bankTypeService;

  public BankTypeController(BankTypeService bankTypeService) {
    this.bankTypeService = bankTypeService;
  }

  @GetMapping
  public ResponseEntity<?> getBankTypes(
      Pageable pageable, @RequestParam(name = "asPage") final boolean asPage) {
    if (asPage) {
      PagedResult<BankType> pagedResults = bankTypeService.getBankTypes(pageable);
      return ResponseEntity.ok(pagedResults);
    } else {
      List<BankType> fundSources = bankTypeService.getBankTypes();
      return ResponseEntity.ok(fundSources);
    }
  }

  @GetMapping(path = "/{bankTypeId}")
  public ResponseEntity<BankType> getBankTypes(@PathVariable("bankTypeId") long bankTypeId) {
    return bankTypeService
        .getBankType(bankTypeId)
        .map(ResponseEntity::ok)
        .orElseGet(status(NOT_FOUND)::build);
  }
}
