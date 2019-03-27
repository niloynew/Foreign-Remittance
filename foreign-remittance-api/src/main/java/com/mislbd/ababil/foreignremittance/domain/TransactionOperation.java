package com.mislbd.ababil.foreignremittance.domain;

import javax.validation.constraints.NotNull;

public class TransactionOperation {

  private long id;

  @NotNull private String name;

  private String description;

  @NotNull private long typeId;

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
}
