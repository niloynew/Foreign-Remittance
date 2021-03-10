package com.mislbd.ababil.foreignremittance.exception;

import static com.mislbd.ababil.foreignremittance.exception.Error.ID_BASE_EXCEPTION;

import com.mislbd.ababil.asset.exception.NotAcceptableException;

public class ForeignRemittanceBaseException extends NotAcceptableException {

  public ForeignRemittanceBaseException() {
    super(
        ID_BASE_EXCEPTION.getModule(),
        ID_BASE_EXCEPTION.getCode(),
        ID_BASE_EXCEPTION.getMessages());
  }

  public ForeignRemittanceBaseException(String message) {
    super(ID_BASE_EXCEPTION.getModule(), ID_BASE_EXCEPTION.getCode(), message);
  }

  public ForeignRemittanceBaseException(String errorCode, String message) {
    super(ID_BASE_EXCEPTION.getModule(), errorCode, message);
  }
}
