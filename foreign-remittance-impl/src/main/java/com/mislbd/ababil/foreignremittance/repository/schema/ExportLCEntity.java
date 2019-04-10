package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.contacts.repository.schema.ContactInformationEntity;
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

  //  @Embedded private AddressEntity addressEntity;

  @Column(name = "STREET")
  private String street;

  @Column(name = "BUILDING_IDENTIFIER")
  private String buildingIdentifier;

  @Column(name = "SUIT_IDENTIFIER")
  private String suitIdentifier;

  @Column(name = "FLOOR_IDENTIFIER")
  private String floorIdentifier;

  @Column(name = "DISTRICT_NAME")
  private String districtName;

  @Column(name = "PO_BOX_NUMBER")
  private String poBoxNumber;

  @Column(name = "POST_CODE")
  private String postCode;

  @Column(name = "CITY")
  private String city;

  @Column(name = "STATE")
  private String state;

  @Column(name = "COUNTRY")
  private String country;

  @Embedded private ContactInformationEntity contactInformationEntity;

  @Column(name = "BLACK_LISTED")
  private Boolean blackListed;

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

  //  public AddressEntity getAddressEntity() {
  //    return addressEntity;
  //  }
  //
  //  public ExportLCEntity setAddressEntity(AddressEntity addressEntity) {
  //    this.addressEntity = addressEntity;
  //    return this;
  //  }

  public String getStreet() {
    return street;
  }

  public ExportLCEntity setStreet(String street) {
    this.street = street;
    return this;
  }

  public String getBuildingIdentifier() {
    return buildingIdentifier;
  }

  public ExportLCEntity setBuildingIdentifier(String buildingIdentifier) {
    this.buildingIdentifier = buildingIdentifier;
    return this;
  }

  public String getSuitIdentifier() {
    return suitIdentifier;
  }

  public ExportLCEntity setSuitIdentifier(String suitIdentifier) {
    this.suitIdentifier = suitIdentifier;
    return this;
  }

  public String getFloorIdentifier() {
    return floorIdentifier;
  }

  public ExportLCEntity setFloorIdentifier(String floorIdentifier) {
    this.floorIdentifier = floorIdentifier;
    return this;
  }

  public String getDistrictName() {
    return districtName;
  }

  public ExportLCEntity setDistrictName(String districtName) {
    this.districtName = districtName;
    return this;
  }

  public String getPoBoxNumber() {
    return poBoxNumber;
  }

  public ExportLCEntity setPoBoxNumber(String poBoxNumber) {
    this.poBoxNumber = poBoxNumber;
    return this;
  }

  public String getPostCode() {
    return postCode;
  }

  public ExportLCEntity setPostCode(String postCode) {
    this.postCode = postCode;
    return this;
  }

  public String getCity() {
    return city;
  }

  public ExportLCEntity setCity(String city) {
    this.city = city;
    return this;
  }

  public String getState() {
    return state;
  }

  public ExportLCEntity setState(String state) {
    this.state = state;
    return this;
  }

  public String getCountry() {
    return country;
  }

  public ExportLCEntity setCountry(String country) {
    this.country = country;
    return this;
  }

  public ContactInformationEntity getContactInformationEntity() {
    return contactInformationEntity;
  }

  public ExportLCEntity setContactInformationEntity(
      ContactInformationEntity contactInformationEntity) {
    this.contactInformationEntity = contactInformationEntity;
    return this;
  }

  public Boolean getBlackListed() {
    return blackListed;
  }

  public ExportLCEntity setBlackListed(Boolean blackListed) {
    this.blackListed = blackListed;
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
