package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.CreateRemittanceChargeMappingCommand;
import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeMapping;
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
      Pageable pageable, @RequestParam(value = "asPage", required = false) final boolean asPage) {
    if (asPage) {
      return ResponseEntity.ok(chargeMappingService.findAll(pageable));
    } else {
      return ResponseEntity.ok(chargeMappingService.findAll());
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
}
