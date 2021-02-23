package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.CreateIDProductCommand;
import com.mislbd.ababil.foreignremittance.domain.IDProduct;
import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
import com.mislbd.ababil.foreignremittance.exception.IDProductCurrenciesNotFoundException;
import com.mislbd.ababil.foreignremittance.repository.jpa.IDProductRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.IDProductEntity;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.ValidationHandler;
import java.util.Optional;

@Aggregate
public class IDProductValidationCommandHandlerAggregate {

  private final IDProductRepository productRepository;

  public IDProductValidationCommandHandlerAggregate(IDProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @ValidationHandler
  public void createIDProduct(CreateIDProductCommand command) {
    IDProduct idProduct = (IDProduct) command.getPayload();
    Optional<IDProductEntity> productEntity = productRepository.findByCode(idProduct.getCode());
    if (productEntity.isPresent()) {
      throw new ForeignRemittanceBaseException("Code already used");
    }

    if (idProduct.getCurrencies() == null || idProduct.getCurrencies().isEmpty()) {
      throw new IDProductCurrenciesNotFoundException();
    }
  }
}
