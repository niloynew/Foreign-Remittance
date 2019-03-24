package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class RemittanceChargeSlabNotFoundException extends NotFoundException {

  public RemittanceChargeSlabNotFoundException(String message) {
    super(
        Error.CHARGE_SLAB_NOT_FOUND.getModule(),
        Error.CHARGE_SLAB_NOT_FOUND.getCode(),
        Error.CHARGE_SLAB_NOT_FOUND.getMessages());
  }

  public RemittanceChargeSlabNotFoundException() {
    super(
        Error.CHARGE_SLAB_NOT_FOUND.getModule(),
        Error.CHARGE_SLAB_NOT_FOUND.getCode(),
        Error.CHARGE_SLAB_NOT_FOUND.getMessages());
  }
}
