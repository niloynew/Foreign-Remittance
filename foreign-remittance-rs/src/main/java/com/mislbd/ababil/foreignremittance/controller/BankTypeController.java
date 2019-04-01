package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.BankType;
import com.mislbd.ababil.foreignremittance.service.BankTypeService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
