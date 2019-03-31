package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.EXPORT_LC_TABLE_NAME)
public class ExportLCEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "EXPORT_LC_ID_GEN")
  @SequenceGenerator(
      name = "EXPORT_LC_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.EXPORT_LC_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "SHORT_NAME")
  private String shortName;

  @Column(name = "OWNER_NAME")
  private String ownerName;

  @Column(name = "DESIGNATION")
  private String designation;

  @Column(name = "ADDRESS", length = 500)
  private String address;

  @Column(name = "CITY")
  private String city;

  @Column(name = "POSTAL_CODE")
  private String postalCode;

  @Column(name = "PHONE")
  private String phone;

  @Column(name = "MOBILE")
  private String mobile;

  @Column(name = "WEB")
  private String web;

  @Column(name = "FAX")
  private String fax;

  @Column(name = "TELEX")
  private String telex;

  @Column(name = "BLACK_LISTED")
  private Boolean blackListed;

  @Column(name = "COUNTRY")
  private Long country;

  @Column(name = "DIVISION")
  private Long division;

  @Column(name = "DISTRICT")
  private Long district;

  @Column(name = "CP_NAME")
  private String cpName;

  @Column(name = "CP_PHONE")
  private String cpPhone;

  @Column(name = "CP_DESIGNATION")
  private String cpDesignation;

  @Column(name = "CP_MAIL")
  private String cpEmail;

  public long getId() {
    return id;
  }

  public ExportLCEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public ExportLCEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getShortName() {
    return shortName;
  }

  public ExportLCEntity setShortName(String shortName) {
    this.shortName = shortName;
    return this;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public ExportLCEntity setOwnerName(String ownerName) {
    this.ownerName = ownerName;
    return this;
  }

  public String getDesignation() {
    return designation;
  }

  public ExportLCEntity setDesignation(String designation) {
    this.designation = designation;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public ExportLCEntity setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getCity() {
    return city;
  }

  public ExportLCEntity setCity(String city) {
    this.city = city;
    return this;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public ExportLCEntity setPostalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public ExportLCEntity setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public ExportLCEntity setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getWeb() {
    return web;
  }

  public ExportLCEntity setWeb(String web) {
    this.web = web;
    return this;
  }

  public String getFax() {
    return fax;
  }

  public ExportLCEntity setFax(String fax) {
    this.fax = fax;
    return this;
  }

  public String getTelex() {
    return telex;
  }

  public ExportLCEntity setTelex(String telex) {
    this.telex = telex;
    return this;
  }

  public Boolean getBlackListed() {
    return blackListed;
  }

  public ExportLCEntity setBlackListed(Boolean blackListed) {
    this.blackListed = blackListed;
    return this;
  }

  public Long getCountry() {
    return country;
  }

  public ExportLCEntity setCountry(Long country) {
    this.country = country;
    return this;
  }

  public Long getDivision() {
    return division;
  }

  public ExportLCEntity setDivision(Long division) {
    this.division = division;
    return this;
  }

  public Long getDistrict() {
    return district;
  }

  public ExportLCEntity setDistrict(Long district) {
    this.district = district;
    return this;
  }

  public String getCpName() {
    return cpName;
  }

  public ExportLCEntity setCpName(String cpName) {
    this.cpName = cpName;
    return this;
  }

  public String getCpPhone() {
    return cpPhone;
  }

  public ExportLCEntity setCpPhone(String cpPhone) {
    this.cpPhone = cpPhone;
    return this;
  }

  public String getCpDesignation() {
    return cpDesignation;
  }

  public ExportLCEntity setCpDesignation(String cpDesignation) {
    this.cpDesignation = cpDesignation;
    return this;
  }

  public String getCpEmail() {
    return cpEmail;
  }

  public ExportLCEntity setCpEmail(String cpEmail) {
    this.cpEmail = cpEmail;
    return this;
  }
}
