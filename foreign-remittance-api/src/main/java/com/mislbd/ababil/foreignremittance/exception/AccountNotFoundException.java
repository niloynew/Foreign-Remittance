package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class AccountNotFoundException extends NotFoundException {
  public AccountNotFoundException() {
    super(
        Error.ACCOUNT_NOT_FOUND.getModule(),
        Error.ACCOUNT_NOT_FOUND.getCode(),
        Error.ACCOUNT_NOT_FOUND.getMessages());
  }

  public AccountNotFoundException(String message) {
    super(Error.ACCOUNT_NOT_FOUND.getModule(), Error.ACCOUNT_NOT_FOUND.getCode(), message);
  }
}
