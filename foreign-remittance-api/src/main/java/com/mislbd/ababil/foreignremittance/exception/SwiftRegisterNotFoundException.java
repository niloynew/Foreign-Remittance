package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class SwiftRegisterNotFoundException extends NotFoundException {

  public SwiftRegisterNotFoundException(String message) {
    super(
        Error.SWIFT_REGISTER_NOT_FOUND_EXCEPTION.getModule(),
        Error.SWIFT_REGISTER_NOT_FOUND_EXCEPTION.getCode(),
        message);
  }

  public SwiftRegisterNotFoundException() {
    super(
        Error.SWIFT_REGISTER_NOT_FOUND_EXCEPTION.getModule(),
        Error.SWIFT_REGISTER_NOT_FOUND_EXCEPTION.getCode(),
        Error.SWIFT_REGISTER_NOT_FOUND_EXCEPTION.getMessages());
  }
}
