package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InstructionCode implements Model {

  @Id @GeneratedValue private long id;

  private InstructionCodeCodes selectedCode;
  private String additionalInformation = "";

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public InstructionCodeCodes getSelectedCode() {
    return selectedCode;
  }

  public void setSelectedCode(InstructionCodeCodes selectedCode) {
    this.selectedCode = selectedCode;
  }

  public String getAdditionalInformation() {
    return additionalInformation;
  }

  public void setAdditionalInformation(String additionalInformation) {
    this.additionalInformation = additionalInformation;
  }

  public String toSwiftString() {
    if (additionalInformation.length() != 0) additionalInformation = "/" + additionalInformation;
    return selectedCode + additionalInformation;
  }
}
