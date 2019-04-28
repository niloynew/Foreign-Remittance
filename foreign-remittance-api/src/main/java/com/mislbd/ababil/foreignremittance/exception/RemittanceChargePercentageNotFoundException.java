package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class RemittanceChargePercentageNotFoundException extends NotFoundException {

  public RemittanceChargePercentageNotFoundException(String message) {
    super(
        Error.CHARGE_PERCENTAGE_NOT_FOUND.getModule(),
        Error.CHARGE_PERCENTAGE_NOT_FOUND.getCode(),
        Error.CHARGE_PERCENTAGE_NOT_FOUND.getMessages());
  }

  public RemittanceChargePercentageNotFoundException() {
    super(
        Error.CHARGE_PERCENTAGE_NOT_FOUND.getModule(),
        Error.CHARGE_PERCENTAGE_NOT_FOUND.getCode(),
        Error.CHARGE_PERCENTAGE_NOT_FOUND.getMessages());
  }
}
