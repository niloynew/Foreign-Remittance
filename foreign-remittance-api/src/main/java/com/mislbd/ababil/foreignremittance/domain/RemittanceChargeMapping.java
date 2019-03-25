package com.mislbd.ababil.foreignremittance.domain;

import javax.validation.constraints.NotNull;

public class RemittanceChargeMapping {

  private long id;

  @NotNull private Long chargeId;

  private String chargeName;

  private String operationName;

  @NotNull private Long operationId;

  private Boolean chargeModificable;

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

  public Boolean getChargeModificable() {
    return chargeModificable;
  }

  public RemittanceChargeMapping setChargeModificable(Boolean chargeModificable) {
    this.chargeModificable = chargeModificable;
    return this;
  }
}
