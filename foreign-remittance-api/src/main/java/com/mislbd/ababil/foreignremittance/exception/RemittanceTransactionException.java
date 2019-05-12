package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class RemittanceTransactionException extends NotFoundException {

  public RemittanceTransactionException(String message) {
    super(
        Error.REMITTANCE_TRANSACTION_EXCEPTION.getModule(),
        Error.REMITTANCE_TRANSACTION_EXCEPTION.getCode(),
        message);
  }

  public RemittanceTransactionException() {
    super(
        Error.REMITTANCE_TRANSACTION_EXCEPTION.getModule(),
        Error.REMITTANCE_TRANSACTION_EXCEPTION.getCode(),
        Error.REMITTANCE_TRANSACTION_EXCEPTION.getMessages());
  }
}
