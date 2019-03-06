package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IdAccount {

  private Long id;

  private String productId;

  private String number;

  private String currencyCode;

  private long correspondenceBranchId;

  private String addressLine1;

  private String addressLine2;

  private String addressLine3;

  private String postBox;

  private String countryId;

  private String city;

  private String clearingHouse;

  private String swiftCode;

  private LocalDate openDate;

  private String email;

  private String url;

  private String routingNo;

  private String telephoneNo;

  private String mobileNo;

  private String faxNo;

  private String contactPerson;

  private BigDecimal maintenanceFee;

  private BigDecimal paymentFee;

  private BigDecimal statementFee;

  private BigDecimal minimumBalance;

  private Long businessDays;

  private Long operatingHours;

  private BigDecimal balanceLimits;

  private String advanceWarning;

  private String recStatus;

  private BigDecimal balanceCcy;

  private BigDecimal balanceLcy;

  private LocalDate lastOpDate;

  private BigDecimal blockAmount;

  private String status;

  private BigDecimal clearingAmount;

  private String nostroAcc;

  // private String idAccNm;

  private long brId;

  private long accBrId;

  private long nostroAccId;

  // region <Getter and Setter>

  public Long getId() {
    return id;
  }

  public IdAccount setId(Long id) {
    this.id = id;
    return this;
  }

  public String getProductId() {
    return productId;
  }

  public IdAccount setProductId(String productId) {
    this.productId = productId;
    return this;
  }

  public String getNumber() {
    return number;
  }

  public IdAccount setNumber(String number) {
    this.number = number;
    return this;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public IdAccount setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public long getCorrespondenceBranchId() {
    return correspondenceBranchId;
  }

  public IdAccount setCorrespondenceBranchId(long correspondenceBranchId) {
    this.correspondenceBranchId = correspondenceBranchId;
    return this;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public IdAccount setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
    return this;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public IdAccount setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
    return this;
  }

  public String getAddressLine3() {
    return addressLine3;
  }

  public IdAccount setAddressLine3(String addressLine3) {
    this.addressLine3 = addressLine3;
    return this;
  }

  public String getPostBox() {
    return postBox;
  }

  public IdAccount setPostBox(String postBox) {
    this.postBox = postBox;
    return this;
  }

  public String getCountryId() {
    return countryId;
  }

  public IdAccount setCountryId(String countryId) {
    this.countryId = countryId;
    return this;
  }

  public String getCity() {
    return city;
  }

  public IdAccount setCity(String city) {
    this.city = city;
    return this;
  }

  public String getClearingHouse() {
    return clearingHouse;
  }

  public IdAccount setClearingHouse(String clearingHouse) {
    this.clearingHouse = clearingHouse;
    return this;
  }

  public String getSwiftCode() {
    return swiftCode;
  }

  public IdAccount setSwiftCode(String swiftCode) {
    this.swiftCode = swiftCode;
    return this;
  }

  public LocalDate getOpenDate() {
    return openDate;
  }

  public IdAccount setOpenDate(LocalDate openDate) {
    this.openDate = openDate;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public IdAccount setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public IdAccount setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getRoutingNo() {
    return routingNo;
  }

  public IdAccount setRoutingNo(String routingNo) {
    this.routingNo = routingNo;
    return this;
  }

  public String getTelephoneNo() {
    return telephoneNo;
  }

  public IdAccount setTelephoneNo(String telephoneNo) {
    this.telephoneNo = telephoneNo;
    return this;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public IdAccount setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
    return this;
  }

  public String getFaxNo() {
    return faxNo;
  }

  public IdAccount setFaxNo(String faxNo) {
    this.faxNo = faxNo;
    return this;
  }

  public String getContactPerson() {
    return contactPerson;
  }

  public IdAccount setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
    return this;
  }

  public BigDecimal getMaintenanceFee() {
    return maintenanceFee;
  }

  public IdAccount setMaintenanceFee(BigDecimal maintenanceFee) {
    this.maintenanceFee = maintenanceFee;
    return this;
  }

  public BigDecimal getPaymentFee() {
    return paymentFee;
  }

  public IdAccount setPaymentFee(BigDecimal paymentFee) {
    this.paymentFee = paymentFee;
    return this;
  }

  public BigDecimal getStatementFee() {
    return statementFee;
  }

  public IdAccount setStatementFee(BigDecimal statementFee) {
    this.statementFee = statementFee;
    return this;
  }

  public BigDecimal getMinimumBalance() {
    return minimumBalance;
  }

  public IdAccount setMinimumBalance(BigDecimal minimumBalance) {
    this.minimumBalance = minimumBalance;
    return this;
  }

  public Long getBusinessDays() {
    return businessDays;
  }

  public IdAccount setBusinessDays(Long businessDays) {
    this.businessDays = businessDays;
    return this;
  }

  public Long getOperatingHours() {
    return operatingHours;
  }

  public IdAccount setOperatingHours(Long operatingHours) {
    this.operatingHours = operatingHours;
    return this;
  }

  public BigDecimal getBalanceLimits() {
    return balanceLimits;
  }

  public IdAccount setBalanceLimits(BigDecimal balanceLimits) {
    this.balanceLimits = balanceLimits;
    return this;
  }

  public String getAdvanceWarning() {
    return advanceWarning;
  }

  public IdAccount setAdvanceWarning(String advanceWarning) {
    this.advanceWarning = advanceWarning;
    return this;
  }

  public String getRecStatus() {
    return recStatus;
  }

  public IdAccount setRecStatus(String recStatus) {
    this.recStatus = recStatus;
    return this;
  }

  public BigDecimal getBalanceCcy() {
    return balanceCcy;
  }

  public IdAccount setBalanceCcy(BigDecimal balanceCcy) {
    this.balanceCcy = balanceCcy;
    return this;
  }

  public BigDecimal getBalanceLcy() {
    return balanceLcy;
  }

  public IdAccount setBalanceLcy(BigDecimal balanceLcy) {
    this.balanceLcy = balanceLcy;
    return this;
  }

  public LocalDate getLastOpDate() {
    return lastOpDate;
  }

  public IdAccount setLastOpDate(LocalDate lastOpDate) {
    this.lastOpDate = lastOpDate;
    return this;
  }

  public BigDecimal getBlockAmount() {
    return blockAmount;
  }

  public IdAccount setBlockAmount(BigDecimal blockAmount) {
    this.blockAmount = blockAmount;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public IdAccount setStatus(String status) {
    this.status = status;
    return this;
  }

  public BigDecimal getClearingAmount() {
    return clearingAmount;
  }

  public IdAccount setClearingAmount(BigDecimal clearingAmount) {
    this.clearingAmount = clearingAmount;
    return this;
  }

  public String getNostroAcc() {
    return nostroAcc;
  }

  public IdAccount setNostroAcc(String nostroAcc) {
    this.nostroAcc = nostroAcc;
    return this;
  }

  /*public String getIdAccNm() {
          return idAccNm;
      }

      public IdAccount setIdAccNm(String idAccNm) {
          this.idAccNm = idAccNm;
          return this;
      }
  */
  public long getBrId() {
    return brId;
  }

  public IdAccount setBrId(long brId) {
    this.brId = brId;
    return this;
  }

  public long getAccBrId() {
    return accBrId;
  }

  public IdAccount setAccBrId(long accBrId) {
    this.accBrId = accBrId;
    return this;
  }

  public long getNostroAccId() {
    return nostroAccId;
  }

  public IdAccount setNostroAccId(long nostroAccId) {
    this.nostroAccId = nostroAccId;
    return this;
  }
}
