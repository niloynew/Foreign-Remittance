package com.mislbd.ababil.foreignremittance.domain;

public class CBFundSource {

  private long id;
  private String code;
  private String description;

  // region <Getter and Setter>

  public long getId() {
    return id;
  }

  public CBFundSource setId(long id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public CBFundSource setCode(String code) {
    this.code = code;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public CBFundSource setDescription(String description) {
    this.description = description;
    return this;
  }

  // <end region>
}
