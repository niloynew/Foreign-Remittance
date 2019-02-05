package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.ID_ACCOUNT_TABLE_NAME)
public class IdAccountEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_ACCOUNT_ID_GEN")
  @SequenceGenerator(
      name = "ID_ACCOUNT_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_ACCOUNT_SEQUENCE_NAME)
  @Column(name = "idAccId")
  private Long id;

  @Column(name = "prodId")
  private String productId;

  @Column(name = "idAccNo")
  private String number;

  @Column(name = "idCurrId")
  private String currencyCode;

  @Column(name = " idCorrespondenceBrId")
  private long correspondenceBranchId;

  @Column(name = "idBankAddress1")
  private String addressLine1;

  @Column(name = "idBankAddress2")
  private String addressLine2;

  @Column(name = "idBankAddress3")
  private String addressLine3;

  @Column(name = "idPostBox")
  private String postBox;

  @Column(name = "idCountry")
  private String countryId;

  @Column(name = "idCity")
  private String city;

  @Column(name = "idNmClearHouse")
  private String clearingHouse;

  @Column(name = "idSwiftCode")
  private String swiftCode;

  @Column(name = "idAccOpenDate")
  private LocalDate openDate;

  @Column(name = "idEMail")
  private String email;

  @Column(name = "idUrl")
  private String url;

  @Column(name = "idRoutingUidNo")
  private String routingNo;

  @Column(name = "idTelephoneNo")
  private String telephoneNo;

  @Column(name = "idMobileNo")
  private String mobileNo;

  @Column(name = "idFaxNo")
  private String faxNo;

  @Column(name = "idContactPerson")
  private String contactPerson;

  @Column(name = "idMaintenanceFee")
  private BigDecimal maintenanceFee;

  @Column(name = "idInterBankPaymentFee")
  private BigDecimal paymentFee;

  @Column(name = "idStatementFee")
  private BigDecimal statementFee;

  @Column(name = "idMinimumBalance")
  private BigDecimal minimumBalance;

  @Column(name = "idBusinessDays")
  private Long businessDays;

  @Column(name = "idOperatingHours")
  private Long operatingHours;

  @Column(name = "idBalanceLimits")
  private BigDecimal balanceLimits;

  @Column(name = "idAdvanceWarning")
  private String advanceWarning;

  private String recStatus;
  /* private String setupUserid;
  private LocalDate setupDatetime;
  private String verifyUserid;
  private Time verigyDatetime;
  private String updateUserid;
  private Time updateDatetime;*/
  @Column(name = "idBalanceCcy")
  private BigDecimal balanceCcy;

  @Column(name = "idBalanceLcy")
  private BigDecimal balanceLcy;

  @Column(name = "idLastOpDate")
  private LocalDate lastOpDate;

  @Column(name = "idBlockAmount")
  private BigDecimal blockAmount;

  @Column(name = "idStatus")
  private String status;

  @Column(name = "idAccClearingAmount")
  private BigDecimal clearingAmount;

  private String nostroAcc;
  private String idAccNm;

  @Column(name = "idBrId")
  private long brId;

  @Column(name = "idAccBrId")
  private long accBrId;

  private long nostroAccId;

  // region <Getter and Setter>

  public Long getId() {
    return id;
  }

  public IdAccountEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public String getProductId() {
    return productId;
  }

  public IdAccountEntity setProductId(String productId) {
    this.productId = productId;
    return this;
  }

  public String getNumber() {
    return number;
  }

  public IdAccountEntity setNumber(String number) {
    this.number = number;
    return this;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public IdAccountEntity setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public long getCorrespondenceBranchId() {
    return correspondenceBranchId;
  }

  public IdAccountEntity setCorrespondenceBranchId(long correspondenceBranchId) {
    this.correspondenceBranchId = correspondenceBranchId;
    return this;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public IdAccountEntity setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
    return this;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public IdAccountEntity setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
    return this;
  }

  public String getAddressLine3() {
    return addressLine3;
  }

  public IdAccountEntity setAddressLine3(String addressLine3) {
    this.addressLine3 = addressLine3;
    return this;
  }

  public String getPostBox() {
    return postBox;
  }

  public IdAccountEntity setPostBox(String postBox) {
    this.postBox = postBox;
    return this;
  }

  public String getCountryId() {
    return countryId;
  }

  public IdAccountEntity setCountryId(String countryId) {
    this.countryId = countryId;
    return this;
  }

  public String getCity() {
    return city;
  }

  public IdAccountEntity setCity(String city) {
    this.city = city;
    return this;
  }

  public String getClearingHouse() {
    return clearingHouse;
  }

  public IdAccountEntity setClearingHouse(String clearingHouse) {
    this.clearingHouse = clearingHouse;
    return this;
  }

  public String getSwiftCode() {
    return swiftCode;
  }

  public IdAccountEntity setSwiftCode(String swiftCode) {
    this.swiftCode = swiftCode;
    return this;
  }

  public LocalDate getOpenDate() {
    return openDate;
  }

  public IdAccountEntity setOpenDate(LocalDate openDate) {
    this.openDate = openDate;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public IdAccountEntity setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public IdAccountEntity setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getRoutingNo() {
    return routingNo;
  }

  public IdAccountEntity setRoutingNo(String routingNo) {
    this.routingNo = routingNo;
    return this;
  }

  public String getTelephoneNo() {
    return telephoneNo;
  }

  public IdAccountEntity setTelephoneNo(String telephoneNo) {
    this.telephoneNo = telephoneNo;
    return this;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public IdAccountEntity setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
    return this;
  }

  public String getFaxNo() {
    return faxNo;
  }

  public IdAccountEntity setFaxNo(String faxNo) {
    this.faxNo = faxNo;
    return this;
  }

  public String getContactPerson() {
    return contactPerson;
  }

  public IdAccountEntity setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
    return this;
  }

  public BigDecimal getMaintenanceFee() {
    return maintenanceFee;
  }

  public IdAccountEntity setMaintenanceFee(BigDecimal maintenanceFee) {
    this.maintenanceFee = maintenanceFee;
    return this;
  }

  public BigDecimal getPaymentFee() {
    return paymentFee;
  }

  public IdAccountEntity setPaymentFee(BigDecimal paymentFee) {
    this.paymentFee = paymentFee;
    return this;
  }

  public BigDecimal getStatementFee() {
    return statementFee;
  }

  public IdAccountEntity setStatementFee(BigDecimal statementFee) {
    this.statementFee = statementFee;
    return this;
  }

  public BigDecimal getMinimumBalance() {
    return minimumBalance;
  }

  public IdAccountEntity setMinimumBalance(BigDecimal minimumBalance) {
    this.minimumBalance = minimumBalance;
    return this;
  }

  public Long getBusinessDays() {
    return businessDays;
  }

  public IdAccountEntity setBusinessDays(Long businessDays) {
    this.businessDays = businessDays;
    return this;
  }

  public Long getOperatingHours() {
    return operatingHours;
  }

  public IdAccountEntity setOperatingHours(Long operatingHours) {
    this.operatingHours = operatingHours;
    return this;
  }

  public BigDecimal getBalanceLimits() {
    return balanceLimits;
  }

  public IdAccountEntity setBalanceLimits(BigDecimal balanceLimits) {
    this.balanceLimits = balanceLimits;
    return this;
  }

  public String getAdvanceWarning() {
    return advanceWarning;
  }

  public IdAccountEntity setAdvanceWarning(String advanceWarning) {
    this.advanceWarning = advanceWarning;
    return this;
  }

  public String getRecStatus() {
    return recStatus;
  }

  public IdAccountEntity setRecStatus(String recStatus) {
    this.recStatus = recStatus;
    return this;
  }

  public BigDecimal getBalanceCcy() {
    return balanceCcy;
  }

  public IdAccountEntity setBalanceCcy(BigDecimal balanceCcy) {
    this.balanceCcy = balanceCcy;
    return this;
  }

  public BigDecimal getBalanceLcy() {
    return balanceLcy;
  }

  public IdAccountEntity setBalanceLcy(BigDecimal balanceLcy) {
    this.balanceLcy = balanceLcy;
    return this;
  }

  public LocalDate getLastOpDate() {
    return lastOpDate;
  }

  public IdAccountEntity setLastOpDate(LocalDate lastOpDate) {
    this.lastOpDate = lastOpDate;
    return this;
  }

  public BigDecimal getBlockAmount() {
    return blockAmount;
  }

  public IdAccountEntity setBlockAmount(BigDecimal blockAmount) {
    this.blockAmount = blockAmount;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public IdAccountEntity setStatus(String status) {
    this.status = status;
    return this;
  }

  public BigDecimal getClearingAmount() {
    return clearingAmount;
  }

  public IdAccountEntity setClearingAmount(BigDecimal clearingAmount) {
    this.clearingAmount = clearingAmount;
    return this;
  }

  public String getNostroAcc() {
    return nostroAcc;
  }

  public IdAccountEntity setNostroAcc(String nostroAcc) {
    this.nostroAcc = nostroAcc;
    return this;
  }

  public String getIdAccNm() {
    return idAccNm;
  }

  public IdAccountEntity setIdAccNm(String idAccNm) {
    this.idAccNm = idAccNm;
    return this;
  }

  public long getBrId() {
    return brId;
  }

  public IdAccountEntity setBrId(long brId) {
    this.brId = brId;
    return this;
  }

  public long getAccBrId() {
    return accBrId;
  }

  public IdAccountEntity setAccBrId(long accBrId) {
    this.accBrId = accBrId;
    return this;
  }

  public long getNostroAccId() {
    return nostroAccId;
  }

  public IdAccountEntity setNostroAccId(long nostroAccId) {
    this.nostroAccId = nostroAccId;
    return this;
  }

  //endregion
}
