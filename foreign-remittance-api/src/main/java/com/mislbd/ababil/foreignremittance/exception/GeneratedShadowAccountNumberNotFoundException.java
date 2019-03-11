package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class GeneratedShadowAccountNumberNotFoundException extends NotFoundException {
  public GeneratedShadowAccountNumberNotFoundException() {
    super(
        Error.GENERATED_SHADOW_ACCOUNT_NUMBER_NOT_FOUND.getModule(),
        Error.GENERATED_SHADOW_ACCOUNT_NUMBER_NOT_FOUND.getCode(),
        Error.GENERATED_SHADOW_ACCOUNT_NUMBER_NOT_FOUND.getMessages());
  }

  public GeneratedShadowAccountNumberNotFoundException(String message) {
    super(
        Error.GENERATED_SHADOW_ACCOUNT_NUMBER_NOT_FOUND.getModule(),
        Error.GENERATED_SHADOW_ACCOUNT_NUMBER_NOT_FOUND.getCode(),
        message);
  }
}
