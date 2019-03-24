package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class RemittanceVatPercentageNotFoundException extends NotFoundException {

  public RemittanceVatPercentageNotFoundException(String message) {
    super(
        Error.CHARGE_VAT_PERCENTAGE_NOT_FOUND.getModule(),
        Error.CHARGE_VAT_PERCENTAGE_NOT_FOUND.getCode(),
        Error.CHARGE_VAT_PERCENTAGE_NOT_FOUND.getMessages());
  }

  public RemittanceVatPercentageNotFoundException() {
    super(
        Error.CHARGE_VAT_PERCENTAGE_NOT_FOUND.getModule(),
        Error.CHARGE_VAT_PERCENTAGE_NOT_FOUND.getCode(),
        Error.CHARGE_VAT_PERCENTAGE_NOT_FOUND.getMessages());
  }
}
