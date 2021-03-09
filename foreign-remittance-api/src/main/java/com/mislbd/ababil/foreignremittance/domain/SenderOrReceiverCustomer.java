package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.ababil.contacts.domain.ContactInformation;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SenderOrReceiverCustomer {

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
}
