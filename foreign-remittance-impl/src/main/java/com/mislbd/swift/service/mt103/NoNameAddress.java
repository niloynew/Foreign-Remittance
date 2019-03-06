package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** Created by user on 1/2/2016. */
@Entity
public class NoNameAddress implements Model {

  @Id @GeneratedValue private long id;
  private CodeListField59F swiftNumber;
  private String nameAndAddress;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public CodeListField59F getSwiftNumber() {
    return swiftNumber;
  }

  public void setSwiftNumber(CodeListField59F swiftNumber) {
    this.swiftNumber = swiftNumber;
  }

  public String getNameAndAddress() {
    return nameAndAddress;
  }

  public void setNameAndAddress(String nameAndAddress) {
    this.nameAndAddress = nameAndAddress;
  }
}
