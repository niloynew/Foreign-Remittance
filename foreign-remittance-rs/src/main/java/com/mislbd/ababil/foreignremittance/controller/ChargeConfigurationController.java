package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.ChargeAccountType;
import com.mislbd.ababil.foreignremittance.domain.RemittanceCharge;
import com.mislbd.ababil.foreignremittance.service.RemittanceChargeService;
import com.mislbd.asset.commons.data.domain.PagedResult;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/remittance-charge", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChargeConfigurationController {

    private final RemittanceChargeService remittanceChargeService;

    public ChargeConfigurationController(RemittanceChargeService remittanceChargeService) {
        this.remittanceChargeService = remittanceChargeService;
    }

    @GetMapping
    public ResponseEntity<?> getRemittanceCharges(
            Pageable pageable,
            @RequestParam(value = "chargeName", required = false) final String chargeName,
            @RequestParam(value = "chargeAccountType", required = false) final ChargeAccountType chargeAccountType,
            @RequestParam(value = "vatAccountType", required = false) final ChargeAccountType vatAccountType,
            @RequestParam(value = "status", required = false) final Boolean status,
            @RequestParam(value = "asPage", required = false) final boolean asPage) {
        if (asPage) {
            PagedResult<RemittanceCharge> pagedResults =
                    remittanceChargeService.getCharges(
                            pageable, chargeName, chargeAccountType, vatAccountType, status);
            return ResponseEntity.ok(pagedResults);
        } else {
            List<RemittanceCharge> accounts =
                    remittanceChargeService.getCharges(chargeName, chargeAccountType, vatAccountType, status);
            return ResponseEntity.ok(accounts);
        }
    }

}
