package com.mislbd.swift.service.subinfo;

import com.mislbd.asset.commons.data.domain.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** Created by user on 12/24/2015. */
@Entity
public class PartyInfoForOptionF implements Model {

  @Id @GeneratedValue private long id;
  private String account;
  private long number;
  private String nameAndAdreesDetails;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public long getNumber() {
    return number;
  }

  public void setNumber(long number) {
    this.number = number;
  }

  public String getNameAndAdreesDetails() {
    return nameAndAdreesDetails;
  }

  public void setNameAndAdreesDetails(String nameAndAdreesDetails) {
    this.nameAndAdreesDetails = nameAndAdreesDetails;
  }
}
