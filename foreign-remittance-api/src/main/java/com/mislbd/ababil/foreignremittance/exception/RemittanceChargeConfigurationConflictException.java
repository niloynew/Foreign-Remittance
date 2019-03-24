package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.ConflictException;

public class RemittanceChargeConfigurationConflictException extends ConflictException {

  public RemittanceChargeConfigurationConflictException(String message) {
    super(
        Error.CHARGE_CONFIGURATION_CONFLICT_FOUND.getModule(),
        Error.CHARGE_CONFIGURATION_CONFLICT_FOUND.getCode(),
        Error.CHARGE_CONFIGURATION_CONFLICT_FOUND.getMessages());
  }

  public RemittanceChargeConfigurationConflictException() {
    super(
        Error.CHARGE_CONFIGURATION_CONFLICT_FOUND.getModule(),
        Error.CHARGE_CONFIGURATION_CONFLICT_FOUND.getCode(),
        Error.CHARGE_CONFIGURATION_CONFLICT_FOUND.getMessages());
  }
}
