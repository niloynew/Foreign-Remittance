package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class RemittanceChargeAmountNotFoundException extends NotFoundException {

  public RemittanceChargeAmountNotFoundException(String message) {
    super(
        Error.CHARGE_AMOUNT_NOT_FOUND.getModule(),
        Error.CHARGE_AMOUNT_NOT_FOUND.getCode(),
        Error.CHARGE_AMOUNT_NOT_FOUND.getMessages());
  }

  public RemittanceChargeAmountNotFoundException() {
    super(
        Error.CHARGE_AMOUNT_NOT_FOUND.getModule(),
        Error.CHARGE_AMOUNT_NOT_FOUND.getCode(),
        Error.CHARGE_AMOUNT_NOT_FOUND.getMessages());
  }
}
