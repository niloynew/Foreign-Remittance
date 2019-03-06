package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AccountNameAndAddressInfo implements Model {
  @Id @GeneratedValue private long id;

  private String account = "";
  private String nameAndAddress = "";

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getNameAndAddress() {
    return nameAndAddress;
  }

  public void setNameAndAddress(String nameAndAddress) {
    // this.nameAndAddress = SwiftStringUtiliy.getTextFromMultilineControls(nameAndAddress);
    this.nameAndAddress = nameAndAddress;
  }

  /*public String toSwiftString() {
    if (account.length() > 0) return "/" + account + Constants.SWIFT_NEWLINE + nameAndAddress;
    else return nameAndAddress;
  }*/

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
