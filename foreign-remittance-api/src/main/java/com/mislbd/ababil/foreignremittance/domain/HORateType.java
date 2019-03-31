package com.mislbd.ababil.foreignremittance.domain;

public class HORateType {

  private long id;

  private String name;

  private String description;

  public long getId() {
    return id;
  }

  public HORateType setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public HORateType setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public HORateType setDescription(String description) {
    this.description = description;
    return this;
  }
}
