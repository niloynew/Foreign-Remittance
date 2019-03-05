package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class IDProductNotFoundException extends NotFoundException {
  public IDProductNotFoundException() {
    super(
        Error.ID_PRODUCT_NOT_FOUND.getModule(),
        Error.ID_PRODUCT_NOT_FOUND.getCode(),
        Error.ID_PRODUCT_NOT_FOUND.getMessages());
  }

  public IDProductNotFoundException(String message) {
    super(Error.ID_PRODUCT_NOT_FOUND.getModule(), Error.ID_PRODUCT_NOT_FOUND.getCode(), message);
  }
}
