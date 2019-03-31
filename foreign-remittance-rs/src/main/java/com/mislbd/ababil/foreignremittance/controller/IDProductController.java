package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.CreateIDProductCommand;
import com.mislbd.ababil.foreignremittance.command.DeleteIDProductCommand;
import com.mislbd.ababil.foreignremittance.command.UpdateIDProductCommand;
import com.mislbd.ababil.foreignremittance.domain.IDProduct;
import com.mislbd.ababil.foreignremittance.service.IDProductService;
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
@RequestMapping(path = "/id-products", produces = MediaType.APPLICATION_JSON_VALUE)
public class IDProductController {
  private final CommandProcessor commandProcessor;
  private final IDProductService idproductService;

  public IDProductController(CommandProcessor commandProcessor, IDProductService idproductService) {
    this.commandProcessor = commandProcessor;
    this.idproductService = idproductService;
  }

  @GetMapping
  public ResponseEntity<?> getIDProducts(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "name", required = false) final String name,
      @RequestParam(value = "code", required = false) final String code,
      @RequestParam(value = "currency", required = false) final String currency) {
    if (asPage) {
      PagedResult<IDProduct> pagedIDProducts =
          idproductService.findIDProducts(pageable, name, code, currency);
      return ResponseEntity.ok(pagedIDProducts);
    } else {
      List<IDProduct> products = idproductService.findIDProducts(name, code, currency);
      return ResponseEntity.ok(products);
    }
  }

  @GetMapping(path = "/{productId}")
  public ResponseEntity<IDProduct> getIDProduct(@PathVariable("productId") Long productId) {
    return idproductService
        .findIDProduct(productId)
        .map(ResponseEntity::ok)
        .orElseGet(status(NOT_FOUND)::build);
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

  public ResponseEntity<Void> deleteIDProduct(@PathVariable("productId") Long productId) {
    commandProcessor.executeUpdate(new DeleteIDProductCommand(productId));
    return status(ACCEPTED).build();
  }
}
