package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.ababil.contacts.domain.ContactInformation;

public class ExportLC {

  private long id;

  private String name;

  private String shortName;

  private String ownerName;

  private String designation;

  //  private Address address;

  private String street;
  private String buildingIdentifier;
  private String suitIdentifier;
  private String floorIdentifier;
  private String districtName;
  private String poBoxNumber;
  private String postCode;
  private String city;
  private String state;
  private String country;

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

  //  public Address getAddress() {
  //    return address;
  //  }
  //
  //  public ExportLC setAddress(Address address) {
  //    this.address = address;
  //    return this;
  //  }

  public String getStreet() {
    return street;
  }

  public ExportLC setStreet(String street) {
    this.street = street;
    return this;
  }

  public String getBuildingIdentifier() {
    return buildingIdentifier;
  }

  public ExportLC setBuildingIdentifier(String buildingIdentifier) {
    this.buildingIdentifier = buildingIdentifier;
    return this;
  }

  public String getSuitIdentifier() {
    return suitIdentifier;
  }

  public ExportLC setSuitIdentifier(String suitIdentifier) {
    this.suitIdentifier = suitIdentifier;
    return this;
  }

  public String getFloorIdentifier() {
    return floorIdentifier;
  }

  public ExportLC setFloorIdentifier(String floorIdentifier) {
    this.floorIdentifier = floorIdentifier;
    return this;
  }

  public String getDistrictName() {
    return districtName;
  }

  public ExportLC setDistrictName(String districtName) {
    this.districtName = districtName;
    return this;
  }

  public String getPoBoxNumber() {
    return poBoxNumber;
  }

  public ExportLC setPoBoxNumber(String poBoxNumber) {
    this.poBoxNumber = poBoxNumber;
    return this;
  }

  public String getPostCode() {
    return postCode;
  }

  public ExportLC setPostCode(String postCode) {
    this.postCode = postCode;
    return this;
  }

  public String getCity() {
    return city;
  }

  public ExportLC setCity(String city) {
    this.city = city;
    return this;
  }

  public String getState() {
    return state;
  }

  public ExportLC setState(String state) {
    this.state = state;
    return this;
  }

  public String getCountry() {
    return country;
  }

  public ExportLC setCountry(String country) {
    this.country = country;
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
