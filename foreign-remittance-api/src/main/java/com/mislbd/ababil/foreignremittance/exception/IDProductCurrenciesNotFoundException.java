package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class IDProductCurrenciesNotFoundException extends NotFoundException {
  public IDProductCurrenciesNotFoundException() {
    super(
        Error.ID_PRODUCT_CURRENCIES_NOT_EXISTS.getModule(),
        Error.ID_PRODUCT_CURRENCIES_NOT_EXISTS.getCode(),
        Error.ID_PRODUCT_CURRENCIES_NOT_EXISTS.getMessages());
  }

  public IDProductCurrenciesNotFoundException(String message) {
    super(
        Error.ID_PRODUCT_CURRENCIES_NOT_EXISTS.getModule(),
        Error.ID_PRODUCT_CURRENCIES_NOT_EXISTS.getCode(),
        message);
  }
}
