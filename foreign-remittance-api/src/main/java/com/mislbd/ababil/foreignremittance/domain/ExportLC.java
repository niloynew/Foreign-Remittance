package com.mislbd.ababil.foreignremittance.domain;

public class ExportLC {

  private long id;

  private String name;

  private String shortName;

  private String ownerName;

  private String designation;

  private String address;

  private String city;

  private String postalCode;

  private String phone;

  private String mobile;

  private String web;

  private String fax;

  private String telex;

  private Boolean blackListed;

  private Long country;

  private Long division;

  private Long district;

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

  public String getAddress() {
    return address;
  }

  public ExportLC setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getCity() {
    return city;
  }

  public ExportLC setCity(String city) {
    this.city = city;
    return this;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public ExportLC setPostalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public ExportLC setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public ExportLC setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getWeb() {
    return web;
  }

  public ExportLC setWeb(String web) {
    this.web = web;
    return this;
  }

  public String getFax() {
    return fax;
  }

  public ExportLC setFax(String fax) {
    this.fax = fax;
    return this;
  }

  public String getTelex() {
    return telex;
  }

  public ExportLC setTelex(String telex) {
    this.telex = telex;
    return this;
  }

  public Boolean getBlackListed() {
    return blackListed;
  }

  public ExportLC setBlackListed(Boolean blackListed) {
    this.blackListed = blackListed;
    return this;
  }

  public Long getCountry() {
    return country;
  }

  public ExportLC setCountry(Long country) {
    this.country = country;
    return this;
  }

  public Long getDivision() {
    return division;
  }

  public ExportLC setDivision(Long division) {
    this.division = division;
    return this;
  }

  public Long getDistrict() {
    return district;
  }

  public ExportLC setDistrict(Long district) {
    this.district = district;
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
