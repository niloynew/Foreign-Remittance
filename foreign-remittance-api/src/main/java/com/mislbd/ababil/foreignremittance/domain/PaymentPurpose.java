package com.mislbd.ababil.foreignremittance.domain;

public class PaymentPurpose {

  private long id;
  private String level;
  private String code;
  private String description;

  // region <Getter and Setter>

  public long getId() {
    return id;
  }

  public PaymentPurpose setId(long id) {
    this.id = id;
    return this;
  }

  public String getLevel() {
    return level;
  }

  public PaymentPurpose setLevel(String level) {
    this.level = level;
    return this;
  }

  public String getCode() {
    return code;
  }

  public PaymentPurpose setCode(String code) {
    this.code = code;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public PaymentPurpose setDescription(String description) {
    this.description = description;
    return this;
  }

  // <end region>
}
