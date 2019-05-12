package com.mislbd.ababil.foreignremittance.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class RemittanceChargeMapping {

  private long id;

  @Enumerated(EnumType.STRING)
  private RemittanceType remittanceType;

  @NotNull private Long chargeId;

  private String chargeName;

  private String typeName;

  @NotNull private Long typeId;

  private Boolean chargeModifiable;

  public long getId() {
    return id;
  }

  public RemittanceChargeMapping setId(long id) {
    this.id = id;
    return this;
  }

  public RemittanceType getRemittanceType() {
    return remittanceType;
  }

  public RemittanceChargeMapping setRemittanceType(RemittanceType remittanceType) {
    this.remittanceType = remittanceType;
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

  public String getTypeName() {
    return typeName;
  }

  public RemittanceChargeMapping setTypeName(String typeName) {
    this.typeName = typeName;
    return this;
  }

  public Long getTypeId() {
    return typeId;
  }

  public RemittanceChargeMapping setTypeId(Long typeId) {
    this.typeId = typeId;
    return this;
  }

  public Boolean getChargeModifiable() {
    return chargeModifiable;
  }

  public RemittanceChargeMapping setChargeModifiable(Boolean chargeModifiable) {
    this.chargeModifiable = chargeModifiable;
    return this;
  }
}
