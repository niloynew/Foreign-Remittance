package com.mislbd.ababil.foreignremittance.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ID_ACCOUNT")
public class IdAccountEntity  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_ACCOUNT_ID_GEN")
    @SequenceGenerator(
            name = "ID_ACCOUNT_ID_GEN",
            allocationSize = 1,
            sequenceName = SchemaConstant.ID_ACCOUNT_SEQUENCE_NAME)

    @Column(name="idAccId")
    private Long id;
    @Column(name = "prodId")
    private String productId;
    @Column(name = "idAccNo")
    private String number;
    @Column(name = "idCurrId")
    private String currencyCode;
    @Column(name = " idCorrespondenceBrId")
    private  long correspondenceBranchId;
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
    @Column(name="idEMail")
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
    @Column(name = "idMaintenenceFee")
    private BigDecimal maintenenceFee;
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
    private LocalDate  lastOpDate;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public long getCorrespondenceBranchId() {
        return correspondenceBranchId;
    }

    public void setCorrespondenceBranchId(long correspondenceBranchId) {
        this.correspondenceBranchId = correspondenceBranchId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getPostBox() {
        return postBox;
    }

    public void setPostBox(String postBox) {
        this.postBox = postBox;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClearingHouse() {
        return clearingHouse;
    }

    public void setClearingHouse(String clearingHouse) {
        this.clearingHouse = clearingHouse;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoutingNo() {
        return routingNo;
    }

    public void setRoutingNo(String routingNo) {
        this.routingNo = routingNo;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public BigDecimal getMaintenenceFee() {
        return maintenenceFee;
    }

    public void setMaintenenceFee(BigDecimal maintenenceFee) {
        this.maintenenceFee = maintenenceFee;
    }

    public BigDecimal getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(BigDecimal paymentFee) {
        this.paymentFee = paymentFee;
    }

    public BigDecimal getStatementFee() {
        return statementFee;
    }

    public void setStatementFee(BigDecimal statementFee) {
        this.statementFee = statementFee;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Long getBusinessDays() {
        return businessDays;
    }

    public void setBusinessDays(Long businessDays) {
        this.businessDays = businessDays;
    }

    public Long getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(Long operatingHours) {
        this.operatingHours = operatingHours;
    }

    public BigDecimal getBalanceLimits() {
        return balanceLimits;
    }

    public void setBalanceLimits(BigDecimal balanceLimits) {
        this.balanceLimits = balanceLimits;
    }

    public String getAdvanceWarning() {
        return advanceWarning;
    }

    public void setAdvanceWarning(String advanceWarning) {
        this.advanceWarning = advanceWarning;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

    public BigDecimal getBalanceCcy() {
        return balanceCcy;
    }

    public void setBalanceCcy(BigDecimal balanceCcy) {
        this.balanceCcy = balanceCcy;
    }

    public BigDecimal getBalanceLcy() {
        return balanceLcy;
    }

    public void setBalanceLcy(BigDecimal balanceLcy) {
        this.balanceLcy = balanceLcy;
    }

    public LocalDate getLastOpDate() {
        return lastOpDate;
    }

    public void setLastOpDate(LocalDate lastOpDate) {
        this.lastOpDate = lastOpDate;
    }

    public BigDecimal getBlockAmount() {
        return blockAmount;
    }

    public void setBlockAmount(BigDecimal blockAmount) {
        this.blockAmount = blockAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getClearingAmount() {
        return clearingAmount;
    }

    public void setClearingAmount(BigDecimal clearingAmount) {
        this.clearingAmount = clearingAmount;
    }

    public String getNostroAcc() {
        return nostroAcc;
    }

    public void setNostroAcc(String nostroAcc) {
        this.nostroAcc = nostroAcc;
    }

    public String getIdAccNm() {
        return idAccNm;
    }

    public void setIdAccNm(String idAccNm) {
        this.idAccNm = idAccNm;
    }

    public long getBrId() {
        return brId;
    }

    public void setBrId(long brId) {
        this.brId = brId;
    }

    public long getAccBrId() {
        return accBrId;
    }

    public void setAccBrId(long accBrId) {
        this.accBrId = accBrId;
    }

    public long getNostroAccId() {
        return nostroAccId;
    }

    public void setNostroAccId(long nostroAccId) {
        this.nostroAccId = nostroAccId;
    }
}
