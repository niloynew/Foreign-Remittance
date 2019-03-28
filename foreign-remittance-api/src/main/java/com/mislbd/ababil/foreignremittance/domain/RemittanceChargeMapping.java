package com.mislbd.ababil.foreignremittance.domain;

import javax.validation.constraints.NotNull;

public class RemittanceChargeMapping {

  private long id;

  @NotNull private Long chargeId;

  private String chargeName;

  private String operationName;

  @NotNull private Long operationId;

  private Boolean chargeModifiable;

  private String typeName;

  public long getId() {
    return id;
  }

  public RemittanceChargeMapping setId(long id) {
    this.id = id;
    return this;
  }

  public Long getChargeId() {
    return chargeId;
  }

  public RemittanceChargeMapping setChargeId(Long chargeId) {
    this.chargeId = chargeId;
    return this;
  }

  public String getChargeName() {
    return chargeName;
  }

  public RemittanceChargeMapping setChargeName(String chargeName) {
    this.chargeName = chargeName;
    return this;
  }

  public String getOperationName() {
    return operationName;
  }

  public RemittanceChargeMapping setOperationName(String operationName) {
    this.operationName = operationName;
    return this;
  }

  public Long getOperationId() {
    return operationId;
  }

  public RemittanceChargeMapping setOperationId(Long operationId) {
    this.operationId = operationId;
    return this;
  }

  public Boolean getChargeModifiable() {
    return chargeModifiable;
  }

  public RemittanceChargeMapping setChargeModifiable(Boolean chargeModifiable) {
    this.chargeModifiable = chargeModifiable;
    return this;
  }

  public String getTypeName() {
    return typeName;
  }

  public RemittanceChargeMapping setTypeName(String typeName) {
    this.typeName = typeName;
    return this;
  }
}
