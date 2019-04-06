package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.contacts.repository.schema.AddressEntity;
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

  @Embedded private AddressEntity addressEntity;

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

  public AddressEntity getAddressEntity() {
    return addressEntity;
  }

  public ExportLCEntity setAddressEntity(AddressEntity addressEntity) {
    this.addressEntity = addressEntity;
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
