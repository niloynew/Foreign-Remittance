package com.mislbd.ababil.foreignremittance.domain;

import javax.validation.constraints.NotNull;

public class TransactionType {

  private long id;

  @NotNull private String name;

  private String description;

  public long getId() {
    return id;
  }

  public TransactionType setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public TransactionType setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public TransactionType setDescription(String description) {
    this.description = description;
    return this;
  }
}
