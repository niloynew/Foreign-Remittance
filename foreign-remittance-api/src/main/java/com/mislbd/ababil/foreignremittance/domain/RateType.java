package com.mislbd.ababil.foreignremittance.domain;

public class RateType {

  private long id;

  private String code;

  private String name;

  private String description;

  public long getId() {
    return id;
  }

  public RateType setId(long id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public RateType setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public RateType setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public RateType setDescription(String description) {
    this.description = description;
    return this;
  }
}
