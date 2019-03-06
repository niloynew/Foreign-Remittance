package com.mislbd.swift.service.subinfo;

import com.mislbd.asset.commons.data.domain.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PartyInfoForOptionD implements Model {
  @Id @GeneratedValue private long id;

  private String partyIdentifier;
  private String dcMark;
  private String nameAndAddress;

  public String getPartyIdentifier() {
    return partyIdentifier;
  }

  public void setPartyIdentifier(String partyIdentifier) {
    this.partyIdentifier = partyIdentifier;
  }

  public String getNameAndAddress() {
    return nameAndAddress;
  }

  public void setNameAndAddress(String nameAndAddress) {
    this.nameAndAddress = nameAndAddress;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDcMark() {
    return dcMark;
  }

  public void setDcMark(String dcMark) {
    this.dcMark = dcMark;
  }

  /* public String toSwiftString() {
    if (partyIdentifier != null) {
      return "/" + partyIdentifier + Constants.SWIFT_NEWLINE + nameAndAddress;
    }

    return nameAndAddress;
  }*/
}
