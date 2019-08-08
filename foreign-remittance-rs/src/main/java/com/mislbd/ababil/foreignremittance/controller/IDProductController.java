package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.CreateIDProductCommand;
import com.mislbd.ababil.foreignremittance.command.DeleteIDProductCommand;
import com.mislbd.ababil.foreignremittance.command.UpdateIDProductCommand;
import com.mislbd.ababil.foreignremittance.domain.IDProduct;
import com.mislbd.ababil.foreignremittance.query.IDProductByIdQuery;
import com.mislbd.ababil.foreignremittance.query.IDProductQuery;
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
@RequestMapping(path = "/id-products", produces = MediaType.APPLICATION_JSON_VALUE)
public class IDProductController {
  private final CommandProcessor commandProcessor;
  private final QueryManager queryManager;

  public IDProductController(CommandProcessor commandProcessor, QueryManager queryManager) {
    this.commandProcessor = commandProcessor;
    this.queryManager = queryManager;
  }

  @GetMapping
  public ResponseEntity<?> getIDProducts(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "name", required = false) final String name,
      @RequestParam(value = "code", required = false) final String code,
      @RequestParam(value = "currency", required = false) final String currency) {
    QueryResult<?> queryResult =
        queryManager.executeQuery(new IDProductQuery(asPage, pageable, name, code, currency));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @GetMapping(path = "/{productId}")
  public ResponseEntity<?> getIDProduct(@PathVariable("productId") Long productId) {
    QueryResult<?> queryResult = queryManager.executeQuery(new IDProductByIdQuery(productId));
    return ResponseEntity.ok(queryResult.getResult());
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> createIDProduct(
      @Valid @RequestBody IDProduct idProduct) {
    return status(CREATED)
        .body(commandProcessor.executeResult(new CreateIDProductCommand(idProduct)));
  }

  @PutMapping(path = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateIDProduct(
      @PathVariable("productId") Long productId, @Valid @RequestBody IDProduct idProduct) {
    commandProcessor.executeUpdate(new UpdateIDProductCommand(idProduct, productId));
    return status(ACCEPTED).build();
  }

  @DeleteMapping(path = "/{productId}")
  public ResponseEntity<Void> deleteIDProduct(@PathVariable("productId") Long productId) {
    commandProcessor.executeUpdate(new DeleteIDProductCommand(productId));
    return status(ACCEPTED).build();
  }
}
