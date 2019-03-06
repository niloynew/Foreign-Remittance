package com.mislbd.swift.service.mt103;

import com.mislbd.swift.service.subinfo.Country;

public class CodeCountrycodeNarrativeInfo {

  private String code;
  private Country country;
  private String narrative = "";

  public String getSelectedCode() {
    return code;
  }

  public void setSelectedCode(String selectedCode) {
    if (selectedCode.length() != 0) selectedCode = "/" + selectedCode + "/";
    this.code = selectedCode;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public String getNarrative() {
    return narrative;
  }

  public void setNarrative(String narrative) {
    if (narrative.length() != 0) narrative = "//" + narrative;
    this.narrative = narrative;
  }

  public String toSwiftString() {
    return code + country.getCode() + narrative;
  }
}
