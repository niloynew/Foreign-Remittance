package com.mislbd.ababil.foreignremittance.domain;

public class ClientRateType {

  private long id;

  private String name;

  private String description;

  public long getId() {
    return id;
  }

  public ClientRateType setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public ClientRateType setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ClientRateType setDescription(String description) {
    this.description = description;
    return this;
  }
}
