package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.ababil.contacts.domain.Address;
import com.mislbd.ababil.contacts.domain.ContactInformation;

public class ExportLC {

  private long id;

  private String name;

  private String shortName;

  private String ownerName;

  private String designation;

  private Address address;

  private ContactInformation contactInformation;

  private Boolean blackListed;

  // ### Contact Person Information //

  private String cpName;

  private String cpPhone;

  private String cpDesignation;

  private String cpEmail;

  public long getId() {
    return id;
  }

  public ExportLC setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public ExportLC setName(String name) {
    this.name = name;
    return this;
  }

  public String getShortName() {
    return shortName;
  }

  public ExportLC setShortName(String shortName) {
    this.shortName = shortName;
    return this;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public ExportLC setOwnerName(String ownerName) {
    this.ownerName = ownerName;
    return this;
  }

  public String getDesignation() {
    return designation;
  }

  public ExportLC setDesignation(String designation) {
    this.designation = designation;
    return this;
  }

  public Address getAddress() {
    return address;
  }

  public ExportLC setAddress(Address address) {
    this.address = address;
    return this;
  }

  public ContactInformation getContactInformation() {
    return contactInformation;
  }

  public ExportLC setContactInformation(ContactInformation contactInformation) {
    this.contactInformation = contactInformation;
    return this;
  }

  public Boolean getBlackListed() {
    return blackListed;
  }

  public ExportLC setBlackListed(Boolean blackListed) {
    this.blackListed = blackListed;
    return this;
  }

  public String getCpName() {
    return cpName;
  }

  public ExportLC setCpName(String cpName) {
    this.cpName = cpName;
    return this;
  }

  public String getCpPhone() {
    return cpPhone;
  }

  public ExportLC setCpPhone(String cpPhone) {
    this.cpPhone = cpPhone;
    return this;
  }

  public String getCpDesignation() {
    return cpDesignation;
  }

  public ExportLC setCpDesignation(String cpDesignation) {
    this.cpDesignation = cpDesignation;
    return this;
  }

  public String getCpEmail() {
    return cpEmail;
  }

  public ExportLC setCpEmail(String cpEmail) {
    this.cpEmail = cpEmail;
    return this;
  }
}
