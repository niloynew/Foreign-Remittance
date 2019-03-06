package com.mislbd.swift.service.subinfo;

import com.mislbd.asset.commons.data.domain.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PartyInfoForOptionB implements Model {
  @Id @GeneratedValue private long id;

  private String partyIdentifier;
  private String location;
  private String dcMark;

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getPartyIdentifier() {
    return partyIdentifier;
  }

  public void setPartyIdentifier(String partyIdentifier) {
    this.partyIdentifier = partyIdentifier;
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
      return partyIdentifier + Constants.SWIFT_NEWLINE + location;
    }

    return location;
  }*/
}
