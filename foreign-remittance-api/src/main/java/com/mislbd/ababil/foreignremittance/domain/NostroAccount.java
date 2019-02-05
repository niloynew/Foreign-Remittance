package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NostroAccount {

  private Long nostroAccId;

  private String number;

  private String name;

  private String currencyCode;

  private String correspondenceBrId;

  private String addressLine1;

  private String addressLine2;

  private String addressLine3;

  private String nostroPostBox;

  private long countryId;

  private String city;

  private String clearHouse;

  private String swiftCode;

  private LocalDate accOpenDate;

  private String email;

  private String url;

  private String routingUidNo;

  private String telephoneNo;

  private String mobileNo;

  private String faxNo;

  private String contactPerson;

  private BigDecimal maintenanceFee;

  private Long operatingHours;

  private BigDecimal nostroBalanceLimits;

  private String advanceWarning;

  private String recStatus;

  private BigDecimal balanceCcy;

  private LocalDate lastOpDate;

  private BigDecimal blockAmount;

  private String status;

  private BigDecimal clearingAmount;

  private long brId;

  // region <Getter and Setter>

  public Long getNostroAccId() {
    return nostroAccId;
  }

  public NostroAccount setNostroAccId(Long nostroAccId) {
    this.nostroAccId = nostroAccId;
    return this;
  }

  public String getNumber() {
    return number;
  }

  public NostroAccount setNumber(String number) {
    this.number = number;
    return this;

  }

  public String getName() {
    return name;
  }

  public NostroAccount setName(String name) {
    this.name = name;
    return this;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public NostroAccount setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public String getCorrespondenceBrId() {
    return correspondenceBrId;
  }

  public NostroAccount setCorrespondenceBrId(String correspondenceBrId) {
    this.correspondenceBrId = correspondenceBrId;
    return this;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public NostroAccount setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
    return this;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public NostroAccount setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
    return this;
  }

  public String getAddressLine3() {
    return addressLine3;
  }

  public NostroAccount setAddressLine3(String addressLine3) {
    this.addressLine3 = addressLine3;
    return this;
  }

  public String getNostroPostBox() {
    return nostroPostBox;
  }

  public NostroAccount setNostroPostBox(String nostroPostBox) {
    this.nostroPostBox = nostroPostBox;
    return this;
  }

  public long getCountryId() {
    return countryId;
  }

  public NostroAccount setCountryId(long countryId) {
    this.countryId = countryId;
    return this;
  }

  public String getCity() {
    return city;
  }

  public NostroAccount setCity(String city) {
    this.city = city;
    return this;
  }

  public String getClearHouse() {
    return clearHouse;
  }

  public NostroAccount setClearHouse(String clearHouse) {
    this.clearHouse = clearHouse;
    return this;
  }

  public String getSwiftCode() {
    return swiftCode;
  }

  public NostroAccount setSwiftCode(String swiftCode) {
    this.swiftCode = swiftCode;
    return this;
  }

  public LocalDate getAccOpenDate() {
    return accOpenDate;
  }

  public NostroAccount setAccOpenDate(LocalDate accOpenDate) {
    this.accOpenDate = accOpenDate;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public NostroAccount setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public NostroAccount setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getRoutingUidNo() {
    return routingUidNo;
  }

  public NostroAccount setRoutingUidNo(String routingUidNo) {
    this.routingUidNo = routingUidNo;
    return this;
  }

  public String getTelephoneNo() {
    return telephoneNo;
  }

  public NostroAccount setTelephoneNo(String telephoneNo) {
    this.telephoneNo = telephoneNo;
    return this;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public NostroAccount setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
    return this;
  }

  public String getFaxNo() {
    return faxNo;
  }

  public NostroAccount setFaxNo(String faxNo) {
    this.faxNo = faxNo;
    return this;
  }

  public String getContactPerson() {
    return contactPerson;
  }

  public NostroAccount setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
    return this;
  }

  public BigDecimal getMaintenanceFee() {
    return maintenanceFee;
  }

  public NostroAccount setMaintenanceFee(BigDecimal maintenanceFee) {
    this.maintenanceFee = maintenanceFee;
    return this;
  }

  public Long getOperatingHours() {
    return operatingHours;
  }

  public NostroAccount setOperatingHours(Long operatingHours) {
    this.operatingHours = operatingHours;
    return this;
  }

  public BigDecimal getNostroBalanceLimits() {
    return nostroBalanceLimits;
  }

  public NostroAccount setNostroBalanceLimits(BigDecimal nostroBalanceLimits) {
    this.nostroBalanceLimits = nostroBalanceLimits;
    return this;
  }

  public String getAdvanceWarning() {
    return advanceWarning;
  }

  public NostroAccount setAdvanceWarning(String advanceWarning) {
    this.advanceWarning = advanceWarning;
    return this;
  }

  public String getRecStatus() {
    return recStatus;
  }

  public NostroAccount setRecStatus(String recStatus) {
    this.recStatus = recStatus;
    return this;
  }

  public BigDecimal getBalanceCcy() {
    return balanceCcy;
  }

  public NostroAccount setBalanceCcy(BigDecimal balanceCcy) {
    this.balanceCcy = balanceCcy;
    return this;
  }

  public LocalDate getLastOpDate() {
    return lastOpDate;
  }

  public NostroAccount setLastOpDate(LocalDate lastOpDate) {
    this.lastOpDate = lastOpDate;
    return this;
  }

  public BigDecimal getBlockAmount() {
    return blockAmount;
  }

  public NostroAccount setBlockAmount(BigDecimal blockAmount) {
    this.blockAmount = blockAmount;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public NostroAccount setStatus(String status) {
    this.status = status;
    return this;
  }

  public BigDecimal getClearingAmount() {
    return clearingAmount;
  }

  public NostroAccount setClearingAmount(BigDecimal clearingAmount) {
    this.clearingAmount = clearingAmount;
    return this;
  }

  public long getBrId() {
    return brId;
  }

  public NostroAccount setBrId(long brId) {
    this.brId = brId;
    return this;
  }

  // endregion
}
