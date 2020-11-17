package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class BankTypeNotFoundException extends NotFoundException {

  public BankTypeNotFoundException(String message) {
    super(
        Error.BANK_TYPE_NOT_FOUND_EXCEPTION.getModule(),
        Error.BANK_TYPE_NOT_FOUND_EXCEPTION.getCode(),
        message);
  }

  public BankTypeNotFoundException() {
    super(
        Error.BANK_TYPE_NOT_FOUND_EXCEPTION.getModule(),
        Error.BANK_TYPE_NOT_FOUND_EXCEPTION.getCode(),
        Error.BANK_TYPE_NOT_FOUND_EXCEPTION.getMessages());
  }
}
