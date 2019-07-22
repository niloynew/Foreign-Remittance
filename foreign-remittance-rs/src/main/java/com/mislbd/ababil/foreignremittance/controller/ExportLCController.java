package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;

import com.mislbd.ababil.foreignremittance.command.SaveExportLCCommand;
import com.mislbd.ababil.foreignremittance.domain.ExportLC;
import com.mislbd.ababil.foreignremittance.query.ExportCByIdQuery;
import com.mislbd.ababil.foreignremittance.query.ExportCQuery;
import com.mislbd.ababil.foreignremittance.service.ExportLCService;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "export-lcs", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExportLCController {

  private final CommandProcessor commandProcessor;
  private final ExportLCService exportLCService;
  private final QueryManager queryManager;

  public ExportLCController(
      CommandProcessor commandProcessor,
      ExportLCService exportLCService,
      QueryManager queryManager) {
    this.commandProcessor = commandProcessor;
    this.exportLCService = exportLCService;
    this.queryManager = queryManager;
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
    QueryResult<?> queryResult =
        queryManager.executeQuery(
            new ExportCQuery(asPage, pageable, name, ownerName, address, country, cpName, cpEmail));
    return ResponseEntity.ok(queryResult);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<?> getLc(@PathVariable("id") long id) {
    QueryResult<?> queryResult = queryManager.executeQuery(new ExportCByIdQuery(id));

    return ResponseEntity.ok(queryResult);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(ACCEPTED)
  public ResponseEntity<?> saveExportLC(@RequestBody ExportLC exportLC) {
    commandProcessor.executeResult(new SaveExportLCCommand(exportLC));
    return ResponseEntity.accepted().build();
  }
}
