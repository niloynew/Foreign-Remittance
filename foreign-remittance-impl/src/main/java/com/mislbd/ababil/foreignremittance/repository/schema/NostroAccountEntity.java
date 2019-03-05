package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = SchemaConstant.NOSTRO_ACCOUNT_TABLE_NAME)
public class NostroAccountEntity extends BaseEntity {

  @Id private Long nostroAccId;

  @Column(name = "nostroAccNo")
  private String number;

  @Column(name = "nostroAccNm")
  private String name;

  @Column(name = "nostroCurrId")
  private String currencyCode;

  @Column(name = "nostroCorrespondenceBrId")
  private String correspondenceBrId;

  @Column(name = "nostroBankAddress1")
  private String addressLine1;

  @Column(name = "nostroBankAddress2")
  private String addressLine2;

  @Column(name = "nostroBankAddress3")
  private String addressLine3;

  @Column(name = "nostroPostBox")
  private String nostroPostBox;

  @Column(name = "nostroCountry")
  private long countryId;

  @Column(name = "nostroCity")
  private String city;

  @Column(name = "nostroNmClearHouse")
  private String clearHouse;

  @Column(name = "nostroSwiftCode")
  private String swiftCode;

  @Column(name = "nostroAccOpenDate")
  private LocalDate accOpenDate;

  @Column(name = "nostroEMail")
  private String email;

  @Column(name = "nostroUrl")
  private String url;

  @Column(name = "nostroRoutingUidNo")
  private String routingUidNo;

  @Column(name = "nostroTelephoneNo")
  private String telephoneNo;

  @Column(name = "nostroMobileNo")
  private String mobileNo;

  @Column(name = "nostroFaxNo;")
  private String faxNo;

  @Column(name = "nostroContactPerson")
  private String contactPerson;

  @Column(name = "nostroMaintenanceFee")
  private BigDecimal maintenanceFee;

  @Column(name = "nostroOperatingHours")
  private Long operatingHours;

  @Column(name = "nostroBalanceLimits")
  private BigDecimal nostroBalanceLimits;

  @Column(name = "nostroAdvanceWarning")
  private String advanceWarning;

  private String recStatus;

  /*private String setupUserid;
  private Time setupDatetime;
  private String verifyUserid;
  private Time verigyDatetime;
  private String updateUserid;
  private Time updateDatetime;*/

  @Column(name = "nostroBalanceCcy")
  private BigDecimal balanceCcy;

  @Column(name = "nostroLastOpDate")
  private LocalDate lastOpDate;

  @Column(name = "nostroBlockAmount")
  private BigDecimal blockAmount;

  @Column(name = "nostroStatus ")
  private String status;

  @Column(name = "nostroAccClearingAmount")
  private BigDecimal clearingAmount;

  @Column(name = "nostroBrId")
  private long brId;

  public Long getNostroAccId() {
    return nostroAccId;
  }

  public NostroAccountEntity setNostroAccId(Long nostroAccId) {
    this.nostroAccId = nostroAccId;
    return this;
  }

  public String getNumber() {
    return number;
  }

  public NostroAccountEntity setNumber(String number) {
    this.number = number;
    return this;
  }

  public String getName() {
    return name;
  }

  public NostroAccountEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public NostroAccountEntity setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public String getCorrespondenceBrId() {
    return correspondenceBrId;
  }

  public NostroAccountEntity setCorrespondenceBrId(String correspondenceBrId) {
    this.correspondenceBrId = correspondenceBrId;
    return this;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public NostroAccountEntity setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
    return this;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public NostroAccountEntity setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
    return this;
  }

  public String getAddressLine3() {
    return addressLine3;
  }

  public NostroAccountEntity setAddressLine3(String addressLine3) {
    this.addressLine3 = addressLine3;
    return this;
  }

  public String getNostroPostBox() {
    return nostroPostBox;
  }

  public NostroAccountEntity setNostroPostBox(String nostroPostBox) {
    this.nostroPostBox = nostroPostBox;
    return this;
  }

  public long getCountryId() {
    return countryId;
  }

  public NostroAccountEntity setCountryId(long countryId) {
    this.countryId = countryId;
    return this;
  }

  public String getCity() {
    return city;
  }

  public NostroAccountEntity setCity(String city) {
    this.city = city;
    return this;
  }

  public String getClearHouse() {
    return clearHouse;
  }

  public NostroAccountEntity setClearHouse(String clearHouse) {
    this.clearHouse = clearHouse;
    return this;
  }

  public String getSwiftCode() {
    return swiftCode;
  }

  public NostroAccountEntity setSwiftCode(String swiftCode) {
    this.swiftCode = swiftCode;
    return this;
  }

  public LocalDate getAccOpenDate() {
    return accOpenDate;
  }

  public NostroAccountEntity setAccOpenDate(LocalDate accOpenDate) {
    this.accOpenDate = accOpenDate;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public NostroAccountEntity setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public NostroAccountEntity setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getRoutingUidNo() {
    return routingUidNo;
  }

  public NostroAccountEntity setRoutingUidNo(String routingUidNo) {
    this.routingUidNo = routingUidNo;
    return this;
  }

  public String getTelephoneNo() {
    return telephoneNo;
  }

  public NostroAccountEntity setTelephoneNo(String telephoneNo) {
    this.telephoneNo = telephoneNo;
    return this;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public NostroAccountEntity setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
    return this;
  }

  public String getFaxNo() {
    return faxNo;
  }

  public NostroAccountEntity setFaxNo(String faxNo) {
    this.faxNo = faxNo;
    return this;
  }

  public String getContactPerson() {
    return contactPerson;
  }

  public NostroAccountEntity setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
    return this;
  }

  public BigDecimal getMaintenanceFee() {
    return maintenanceFee;
  }

  public NostroAccountEntity setMaintenanceFee(BigDecimal maintenanceFee) {
    this.maintenanceFee = maintenanceFee;
    return this;
  }

  public Long getOperatingHours() {
    return operatingHours;
  }

  public NostroAccountEntity setOperatingHours(Long operatingHours) {
    this.operatingHours = operatingHours;
    return this;
  }

  public BigDecimal getNostroBalanceLimits() {
    return nostroBalanceLimits;
  }

  public NostroAccountEntity setNostroBalanceLimits(BigDecimal nostroBalanceLimits) {
    this.nostroBalanceLimits = nostroBalanceLimits;
    return this;
  }

  public String getAdvanceWarning() {
    return advanceWarning;
  }

  public NostroAccountEntity setAdvanceWarning(String advanceWarning) {
    this.advanceWarning = advanceWarning;
    return this;
  }

  public String getRecStatus() {
    return recStatus;
  }

  public NostroAccountEntity setRecStatus(String recStatus) {
    this.recStatus = recStatus;
    return this;
  }

  public BigDecimal getBalanceCcy() {
    return balanceCcy;
  }

  public NostroAccountEntity setBalanceCcy(BigDecimal balanceCcy) {
    this.balanceCcy = balanceCcy;
    return this;
  }

  public LocalDate getLastOpDate() {
    return lastOpDate;
  }

  public NostroAccountEntity setLastOpDate(LocalDate lastOpDate) {
    this.lastOpDate = lastOpDate;
    return this;
  }

  public BigDecimal getBlockAmount() {
    return blockAmount;
  }

  public NostroAccountEntity setBlockAmount(BigDecimal blockAmount) {
    this.blockAmount = blockAmount;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public NostroAccountEntity setStatus(String status) {
    this.status = status;
    return this;
  }

  public BigDecimal getClearingAmount() {
    return clearingAmount;
  }

  public NostroAccountEntity setClearingAmount(BigDecimal clearingAmount) {
    this.clearingAmount = clearingAmount;
    return this;
  }

  public long getBrId() {
    return brId;
  }

  public NostroAccountEntity setBrId(long brId) {
    this.brId = brId;
    return this;
  }
}
