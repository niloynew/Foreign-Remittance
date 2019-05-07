package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.CreateRemittanceChargeMappingCommand;
import com.mislbd.ababil.foreignremittance.command.DeleteChargeMappingCommand;
import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeMapping;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.service.ChargeMappingService;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "charge-mappings", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChargeMappingController {

  private final ChargeMappingService chargeMappingService;
  private final CommandProcessor commandProcessor;

  public ChargeMappingController(
      ChargeMappingService chargeMappingService, CommandProcessor commandProcessor) {
    this.chargeMappingService = chargeMappingService;
    this.commandProcessor = commandProcessor;
  }

  @GetMapping
  public ResponseEntity<?> getChargeMappings(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "remittanceType", required = false) final RemittanceType remittanceType,
      @RequestParam(value = "typeId", required = false) final Long typeId,
      @RequestParam(value = "chargeId", required = false) final Long chargeId,
      @RequestParam(value = "chargeModifiable", required = false) final Boolean chargeModifiable) {
    if (asPage) {
      return ResponseEntity.ok(
          chargeMappingService.findAll(
              pageable, remittanceType, typeId, chargeId, chargeModifiable));
    } else {
      return ResponseEntity.ok(
          chargeMappingService.findAll(remittanceType, typeId, chargeId, chargeModifiable));
    }
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> saveChargeMapping(
      @Valid @RequestBody RemittanceChargeMapping chargeMapping) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(
                new CreateRemittanceChargeMappingCommand(chargeMapping)));
  }

  @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deleteChargeMapping(@PathVariable("id") Long id) {
    commandProcessor.executeUpdate(new DeleteChargeMappingCommand(id));
    return status(ACCEPTED).build();
  }
}
