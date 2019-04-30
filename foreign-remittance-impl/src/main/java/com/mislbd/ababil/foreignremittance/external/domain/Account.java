package com.mislbd.ababil.foreignremittance.external.domain;

import java.io.Serializable;

public class Account implements Serializable {

  private long id;
  private String number;
  private String currencyCode;
  private String status;

  public long getId() {
    return id;
  }

  public Account setId(long id) {
    this.id = id;
    return this;
  }

  public String getNumber() {
    return number;
  }

  public Account setNumber(String number) {
    this.number = number;
    return this;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public Account setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public Account setStatus(String status) {
    this.status = status;
    return this;
  }
}
