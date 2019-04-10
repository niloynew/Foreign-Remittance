package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class RemittanceVatAmountNotFoundException extends NotFoundException {

  public RemittanceVatAmountNotFoundException(String message) {
    super(
        Error.CHARGE_VAT_AMOUNT_NOT_FOUND.getModule(),
        Error.CHARGE_VAT_AMOUNT_NOT_FOUND.getCode(),
        Error.CHARGE_VAT_AMOUNT_NOT_FOUND.getMessages());
  }

  public RemittanceVatAmountNotFoundException() {
    super(
        Error.CHARGE_VAT_AMOUNT_NOT_FOUND.getModule(),
        Error.CHARGE_VAT_AMOUNT_NOT_FOUND.getCode(),
        Error.CHARGE_VAT_AMOUNT_NOT_FOUND.getMessages());
  }
}
