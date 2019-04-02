package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.SaveExportLCCommand;
import com.mislbd.ababil.foreignremittance.domain.ExportLC;
import com.mislbd.ababil.foreignremittance.service.ExportLCService;
import com.mislbd.asset.command.api.CommandProcessor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "export-lcs", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExportLCController {

  private final CommandProcessor commandProcessor;
  private final ExportLCService exportLCService;

  public ExportLCController(CommandProcessor commandProcessor, ExportLCService exportLCService) {
    this.commandProcessor = commandProcessor;
    this.exportLCService = exportLCService;
  }

  @GetMapping
  public ResponseEntity<?> getLcs(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "ownerName", required = false) String ownerName,
      @RequestParam(value = "address", required = false) String address,
      @RequestParam(value = "country", required = false) String country,
      @RequestParam(value = "cpName", required = false) String cpName,
      @RequestParam(value = "cpEmail", required = false) String cpEmail) {
    if (asPage) {
      return ResponseEntity.ok(
          exportLCService.getLcs(pageable, name, ownerName, address, country, cpName, cpEmail));
    } else {
      return ResponseEntity.ok(
          exportLCService.getLcList(name, ownerName, address, country, cpName, cpEmail));
    }
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<ExportLC> getLc(@PathVariable("id") Long id) {
    return exportLCService.getLc(id).map(ResponseEntity::ok).orElseGet(status(NOT_FOUND)::build);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(ACCEPTED)
  public ResponseEntity<?> saveExportLC(@RequestBody ExportLC exportLC) {
    commandProcessor.executeResult(new SaveExportLCCommand(exportLC));
    return ResponseEntity.accepted().build();
  }
}
