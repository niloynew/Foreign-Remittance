package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class ChargeMappingNotFoundException extends NotFoundException {

  public ChargeMappingNotFoundException() {
    super(
        Error.CHARGE_MAPPING_NOT_FOUND.getModule(),
        Error.CHARGE_MAPPING_NOT_FOUND.getCode(),
        Error.CHARGE_MAPPING_NOT_FOUND.getMessages());
  }

  public ChargeMappingNotFoundException(String message) {
    super(
        Error.CHARGE_MAPPING_NOT_FOUND.getModule(),
        Error.CHARGE_MAPPING_NOT_FOUND.getCode(),
        message);
  }
}
