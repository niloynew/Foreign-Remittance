package com.mislbd.ababil.foreignremittance.domain;

public enum RemittanceType {
  INWARD_REMITTANCE("Inward Remittance"),
  OUTWARD_REMITTANCE("Outward Remittance");

  private final String name;

  RemittanceType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
