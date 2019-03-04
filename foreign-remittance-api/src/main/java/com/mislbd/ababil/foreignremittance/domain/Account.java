package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

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

    private String idAccNm;

    private long brId;

    private long accBrId;

    private long nostroAccId;

// region <Getter and Setter>

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProductId() {
        return productId;
    }

    public Account setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Account setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Account setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public long getCorrespondenceBranchId() {
        return correspondenceBranchId;
    }

    public Account setCorrespondenceBranchId(long correspondenceBranchId) {
        this.correspondenceBranchId = correspondenceBranchId;
        return this;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public Account setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public Account setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public Account setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
        return this;
    }

    public String getPostBox() {
        return postBox;
    }

    public Account setPostBox(String postBox) {
        this.postBox = postBox;
        return this;
    }

    public String getCountryId() {
        return countryId;
    }

    public Account setCountryId(String countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Account setCity(String city) {
        this.city = city;
        return this;
    }

    public String getClearingHouse() {
        return clearingHouse;
    }

    public Account setClearingHouse(String clearingHouse) {
        this.clearingHouse = clearingHouse;
        return this;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public Account setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
        return this;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public Account setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Account setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getRoutingNo() {
        return routingNo;
    }

    public Account setRoutingNo(String routingNo) {
        this.routingNo = routingNo;
        return this;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public Account setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
        return this;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public Account setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public Account setFaxNo(String faxNo) {
        this.faxNo = faxNo;
        return this;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public Account setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public BigDecimal getMaintenanceFee() {
        return maintenanceFee;
    }

    public Account setMaintenanceFee(BigDecimal maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
        return this;
    }

    public BigDecimal getPaymentFee() {
        return paymentFee;
    }

    public Account setPaymentFee(BigDecimal paymentFee) {
        this.paymentFee = paymentFee;
        return this;
    }

    public BigDecimal getStatementFee() {
        return statementFee;
    }

    public Account setStatementFee(BigDecimal statementFee) {
        this.statementFee = statementFee;
        return this;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public Account setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
        return this;
    }

    public Long getBusinessDays() {
        return businessDays;
    }

    public Account setBusinessDays(Long businessDays) {
        this.businessDays = businessDays;
        return this;
    }

    public Long getOperatingHours() {
        return operatingHours;
    }

    public Account setOperatingHours(Long operatingHours) {
        this.operatingHours = operatingHours;
        return this;
    }

    public BigDecimal getBalanceLimits() {
        return balanceLimits;
    }

    public Account setBalanceLimits(BigDecimal balanceLimits) {
        this.balanceLimits = balanceLimits;
        return this;
    }

    public String getAdvanceWarning() {
        return advanceWarning;
    }

    public Account setAdvanceWarning(String advanceWarning) {
        this.advanceWarning = advanceWarning;
        return this;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public Account setRecStatus(String recStatus) {
        this.recStatus = recStatus;
        return this;
    }

    public BigDecimal getBalanceCcy() {
        return balanceCcy;
    }

    public Account setBalanceCcy(BigDecimal balanceCcy) {
        this.balanceCcy = balanceCcy;
        return this;
    }

    public BigDecimal getBalanceLcy() {
        return balanceLcy;
    }

    public Account setBalanceLcy(BigDecimal balanceLcy) {
        this.balanceLcy = balanceLcy;
        return this;
    }

    public LocalDate getLastOpDate() {
        return lastOpDate;
    }

    public Account setLastOpDate(LocalDate lastOpDate) {
        this.lastOpDate = lastOpDate;
        return this;
    }

    public BigDecimal getBlockAmount() {
        return blockAmount;
    }

    public Account setBlockAmount(BigDecimal blockAmount) {
        this.blockAmount = blockAmount;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Account setStatus(String status) {
        this.status = status;
        return this;
    }

    public BigDecimal getClearingAmount() {
        return clearingAmount;
    }

    public Account setClearingAmount(BigDecimal clearingAmount) {
        this.clearingAmount = clearingAmount;
        return this;
    }

    public String getNostroAcc() {
        return nostroAcc;
    }

    public Account setNostroAcc(String nostroAcc) {
        this.nostroAcc = nostroAcc;
        return this;
    }

    public String getIdAccNm() {
        return idAccNm;
    }

    public Account setIdAccNm(String idAccNm) {
        this.idAccNm = idAccNm;
        return this;
    }

    public long getBrId() {
        return brId;
    }

    public Account setBrId(long brId) {
        this.brId = brId;
        return this;
    }

    public long getAccBrId() {
        return accBrId;
    }

    public Account setAccBrId(long accBrId) {
        this.accBrId = accBrId;
        return this;
    }

    public long getNostroAccId() {
        return nostroAccId;
    }

    public Account setNostroAccId(long nostroAccId) {
        this.nostroAccId = nostroAccId;
        return this;
    }
}
