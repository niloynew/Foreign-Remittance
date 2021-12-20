package com.mislbd.ababil.foreignremittance.exception;

public class ExternalModuleSettlementAccountNotFoundException
    extends ForeignRemittanceBaseException {

  private static final String ERROR_MESSAGE = "External module settlement account not found";
  private static final String ERROR_CODE = "9901";

  public ExternalModuleSettlementAccountNotFoundException() {
    super(ERROR_CODE, ERROR_MESSAGE);
  }

  public ExternalModuleSettlementAccountNotFoundException(String message) {
    super(ERROR_CODE, message);
  }
}
