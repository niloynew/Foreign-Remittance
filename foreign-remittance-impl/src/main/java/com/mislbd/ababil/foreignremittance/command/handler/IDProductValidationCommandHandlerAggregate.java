package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.CreateIDProductCommand;
import com.mislbd.ababil.foreignremittance.domain.IDProduct;
import com.mislbd.ababil.foreignremittance.exception.IDProductCurrenciesNotFoundException;

public class IDProductValidationCommandHandlerAggregate {

  public void createIDProduct(CreateIDProductCommand command) {
    IDProduct idProduct = (IDProduct) command.getPayload();

    if (idProduct.getCurrencies() == null || idProduct.getCurrencies().isEmpty()) {
      throw new IDProductCurrenciesNotFoundException();
    }
  }
}
