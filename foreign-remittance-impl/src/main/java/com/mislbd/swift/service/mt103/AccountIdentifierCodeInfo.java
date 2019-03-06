package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AccountIdentifierCodeInfo implements Model {
  @Id @GeneratedValue private long id;

  private String account = "";
  private String identifierCode;

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getIdentifierCode() {
    return identifierCode;
  }

  public void setIdentifierCode(String identifierCode) {
    this.identifierCode = identifierCode;
  }

  /*public String toSwiftString() {
  	if (account.length() > 0)
  		return "/" + account + Constants.SWIFT_NEWLINE + identifierCode;
  	else
  		return identifierCode;
  }*/

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
