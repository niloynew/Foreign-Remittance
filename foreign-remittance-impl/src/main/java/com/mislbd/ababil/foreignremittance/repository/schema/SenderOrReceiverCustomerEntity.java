package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.contacts.repository.schema.ContactInformationEntity;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_SENDER_RECEIVER_CUSTOMER_TABLE_NAME)
public class SenderOrReceiverCustomerEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_SENDER_RECEIVER_CUSTOMER_ID_GEN")
  @SequenceGenerator(
      name = "ID_SENDER_RECEIVER_CUSTOMER_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_SENDER_RECEIVER_CUSTOMER_SEQUENCE_NAME)
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
}
