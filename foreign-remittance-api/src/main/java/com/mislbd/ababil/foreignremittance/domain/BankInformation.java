package com.mislbd.ababil.foreignremittance.domain;

public class BankInformation {

  private Long bankTypeId;

  private String swiftCode;

  public Long getBankTypeId() {
    return bankTypeId;
  }

  public BankInformation setBankTypeId(Long bankTypeId) {
    this.bankTypeId = bankTypeId;
    return this;
  }

  public String getSwiftCode() {
    return swiftCode;
  }

  public BankInformation setSwiftCode(String swiftCode) {
    this.swiftCode = swiftCode;
    return this;
  }
}
