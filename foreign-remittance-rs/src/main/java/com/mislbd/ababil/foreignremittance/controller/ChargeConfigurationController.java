package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.CreateRemittanceChargeCommand;
import com.mislbd.ababil.foreignremittance.command.UpdateRemittanceChargeCommand;
import com.mislbd.ababil.foreignremittance.domain.ChargeAccountType;
import com.mislbd.ababil.foreignremittance.domain.RemittanceCharge;
import com.mislbd.ababil.foreignremittance.service.RemittanceChargeService;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/remittance-charges", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChargeConfigurationController {

  private final RemittanceChargeService remittanceChargeService;
  private final CommandProcessor commandProcessor;

  public ChargeConfigurationController(
      RemittanceChargeService remittanceChargeService, CommandProcessor commandProcessor) {
    this.remittanceChargeService = remittanceChargeService;
    this.commandProcessor = commandProcessor;
  }

  @GetMapping
  public ResponseEntity<?> getRemittanceCharges(
      Pageable pageable,
      @RequestParam(value = "chargeName", required = false) final String chargeName,
      @RequestParam(value = "chargeAccountType", required = false)
          final ChargeAccountType chargeAccountType,
      @RequestParam(value = "vatAccountType", required = false)
          final ChargeAccountType vatAccountType,
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

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> saveCharge(
      @Valid @RequestBody RemittanceCharge charge) {
    return status(CREATED)
        .body(commandProcessor.executeResult(new CreateRemittanceChargeCommand(charge)));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<RemittanceCharge> getChargeById(@PathVariable("id") long id) {
    return remittanceChargeService
        .findRemittanceChargeById(id)
        .map(ResponseEntity::ok)
        .orElseGet(status(NOT_FOUND)::build);
  }

  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(ACCEPTED)
  public ResponseEntity<Void> updateCharge(
      @PathVariable("id") long id, @Valid @RequestBody RemittanceCharge charge) {
    commandProcessor.executeUpdate(new UpdateRemittanceChargeCommand(charge));
    return status(ACCEPTED).build();
  }
}
