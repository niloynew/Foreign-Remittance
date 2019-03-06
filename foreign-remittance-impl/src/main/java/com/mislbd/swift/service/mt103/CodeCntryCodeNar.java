package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CodeCntryCodeNar implements Model {

  @Id @GeneratedValue private long id;
  private String code;
  private String countryCode;
  private String narative;
  private String continuationOfNar;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getNarative() {
    return narative;
  }

  public void setNarative(String narative) {
    this.narative = narative;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getContinuationOfNar() {
    return continuationOfNar;
  }

  public void setContinuationOfNar(String continuationOfNar) {
    this.continuationOfNar = continuationOfNar;
  }
}
