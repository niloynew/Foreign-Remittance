package com.mislbd.swift.service.subinfo;

import com.mislbd.asset.commons.data.domain.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PartyInfoForOptionA implements Model {
  @Id @GeneratedValue private long id;

  private String partyIdentifier; // synonym of account field
  private String dcMark;
  private String identifierCode; // aka BIC code

  public String getDcMark() {
    return dcMark;
  }

  public void setDcMark(String dcMark) {
    this.dcMark = dcMark;
  }

  public String getPartyIdentifier() {
    return partyIdentifier;
  }

  public void setPartyIdentifier(String partyIdentifier) {
    this.partyIdentifier = partyIdentifier;
  }

  public String getIdentifierCode() {
    return identifierCode;
  }

  public void setIdentifierCode(String identifierCode) {
    this.identifierCode = identifierCode;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
  /*public String toSwiftString()
  {
  	if(partyIdentifier != null){
  		return partyIdentifier + Constants.SWIFT_NEWLINE + identifierCode;
  	}

  	return  identifierCode;
  }*/
}
