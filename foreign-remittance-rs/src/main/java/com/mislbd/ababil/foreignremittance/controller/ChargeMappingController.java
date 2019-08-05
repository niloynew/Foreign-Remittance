package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.CreateRemittanceChargeMappingCommand;
import com.mislbd.ababil.foreignremittance.command.DeleteChargeMappingCommand;
import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeMapping;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.query.ChargeMappingQuery;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "charge-mappings", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChargeMappingController {

  private final QueryManager queryManager;
  private final CommandProcessor commandProcessor;

  public ChargeMappingController(QueryManager queryManager, CommandProcessor commandProcessor) {
    this.queryManager = queryManager;
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
    QueryResult<?> queryResult =
        queryManager.executeQuery(
            new ChargeMappingQuery(
                pageable, asPage, remittanceType, typeId, chargeId, chargeModifiable));
    return ResponseEntity.ok(queryResult);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> saveChargeMapping(
      @Valid @RequestBody RemittanceChargeMapping chargeMapping) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(
                new CreateRemittanceChargeMappingCommand(chargeMapping)));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteChargeMapping(@PathVariable("id") Long id) {
    commandProcessor.executeUpdate(new DeleteChargeMappingCommand(id));
    return status(ACCEPTED).build();
  }
}
