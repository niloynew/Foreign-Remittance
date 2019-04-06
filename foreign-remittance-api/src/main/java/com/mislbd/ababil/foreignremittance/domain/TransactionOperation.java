package com.mislbd.ababil.foreignremittance.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class TransactionOperation {

  private long id;

  @NotNull private String name;

  private String description;

  @NotNull private long typeId;

  @NotNull
  @Enumerated(EnumType.STRING)
  private RemittanceType remittanceType;

  public long getId() {
    return id;
  }

  public TransactionOperation setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public TransactionOperation setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public TransactionOperation setDescription(String description) {
    this.description = description;
    return this;
  }

  public long getTypeId() {
    return typeId;
  }

  public TransactionOperation setTypeId(long typeId) {
    this.typeId = typeId;
    return this;
  }

  public RemittanceType getRemittanceType() {
    return remittanceType;
  }

  public TransactionOperation setRemittanceType(RemittanceType remittanceType) {
    this.remittanceType = remittanceType;
    return this;
  }
}
