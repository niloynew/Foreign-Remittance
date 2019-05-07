package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class RemittanceTransactionNotFoundException extends NotFoundException {
  public RemittanceTransactionNotFoundException() {
    super(
        Error.REMITTANCE_TRANSACTION_NOT_FOUND_EXCEPTION.getModule(),
        Error.REMITTANCE_TRANSACTION_NOT_FOUND_EXCEPTION.getCode(),
        Error.REMITTANCE_TRANSACTION_NOT_FOUND_EXCEPTION.getMessages());
  }

  public RemittanceTransactionNotFoundException(String message) {
    super(
        Error.REMITTANCE_TRANSACTION_NOT_FOUND_EXCEPTION.getModule(),
        Error.REMITTANCE_TRANSACTION_NOT_FOUND_EXCEPTION.getCode(),
        message);
  }
}
