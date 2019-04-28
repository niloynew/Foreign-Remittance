package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.asset.commons.central.ExtendedRuntimeException;

public class RemittanceChargeMinMaxAmountException extends ExtendedRuntimeException {

  public RemittanceChargeMinMaxAmountException(String message) {
    super(
        Error.CHARGE_MIN_MAX_EXCEPTION.getModule(),
        Error.CHARGE_MIN_MAX_EXCEPTION.getCode(),
        Error.CHARGE_MIN_MAX_EXCEPTION.getMessages());
  }

  public RemittanceChargeMinMaxAmountException() {
    super(
        Error.CHARGE_MIN_MAX_EXCEPTION.getModule(),
        Error.CHARGE_MIN_MAX_EXCEPTION.getCode(),
        Error.CHARGE_MIN_MAX_EXCEPTION.getMessages());
  }
}
