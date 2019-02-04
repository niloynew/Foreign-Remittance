package com.mislbd.ababil.foreignremittance.schema;o

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "NOSTRO_ACCOUNT")
public class NostroAccountEntity {
    private Long nostroAccId;
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
    @Column(name = "nostroBankAddress2")
    private String addressLine3;
    @Column(name="nostroPostBox")
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
    @Column(name = "nostroEMail")
    private String url;
    @Column(name="nostroRoutingUidNo")
    private String routingUidNo;
    @Column(name="nostroTelephoneNo")
    private String telephoneNo;
    @Column(name="nostroMobileNo")
    private String mobileNo;
    @Column(name = "nostroFaxNo;")
    private String faxNo;
    @Column(name = "nostroContactPerson")
    private String contactPerson;
    @Column(name = "nostroMaintenenceFee")
    private BigDecimal mintenenceFee;
    @Column(name = "nostroOperatingHours")
    private Long operatingHours;
    @Column(name = "nostroBalanceLimits")
    private BigDecimal nostroBalanceLimits;
    @Column(name = "nostroAdvanceWarning")
    private String addvanceWarning;
    private String recStatus;
    /*private String setupUserid;
    private Time setupDatetime;
    private String verifyUserid;
    private Time verigyDatetime;
    private String updateUserid;
    private Time updateDatetime;*/
    @Column(name = "nostroBalanceCcy")
    private BigDecimal balanceCcy;
    @Column(name="nostroLastOpDate")
    private LocalDate lastOpDate;
    @Column (name="nostroBlockAmount")
    private BigDecimal blockAmount;
    @Column(name="nostroStatus ")
    private String status;
    @Column(name = "nostroAccClearingAmount")
    private BigDecimal clearingAmount;
    @Column(name = "nostroBrId")
    private long brId;


    public Long getNostroAccId() {
        return nostroAccId;
    }

    public void setNostroAccId(Long nostroAccId) {
        this.nostroAccId = nostroAccId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCorrespondenceBrId() {
        return correspondenceBrId;
    }

    public void setCorrespondenceBrId(String correspondenceBrId) {
        this.correspondenceBrId = correspondenceBrId;
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

    public String getNostroPostBox() {
        return nostroPostBox;
    }

    public void setNostroPostBox(String nostroPostBox) {
        this.nostroPostBox = nostroPostBox;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClearHouse() {
        return clearHouse;
    }

    public void setClearHouse(String clearHouse) {
        this.clearHouse = clearHouse;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public LocalDate getAccOpenDate() {
        return accOpenDate;
    }

    public void setAccOpenDate(LocalDate accOpenDate) {
        this.accOpenDate = accOpenDate;
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

    public String getRoutingUidNo() {
        return routingUidNo;
    }

    public void setRoutingUidNo(String routingUidNo) {
        this.routingUidNo = routingUidNo;
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

    public BigDecimal getMintenenceFee() {
        return mintenenceFee;
    }

    public void setMintenenceFee(BigDecimal mintenenceFee) {
        this.mintenenceFee = mintenenceFee;
    }

    public Long getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(Long operatingHours) {
        this.operatingHours = operatingHours;
    }

    public BigDecimal getNostroBalanceLimits() {
        return nostroBalanceLimits;
    }

    public void setNostroBalanceLimits(BigDecimal nostroBalanceLimits) {
        this.nostroBalanceLimits = nostroBalanceLimits;
    }

    public String getAddvanceWarning() {
        return addvanceWarning;
    }

    public void setAddvanceWarning(String addvanceWarning) {
        this.addvanceWarning = addvanceWarning;
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

    public long getBrId() {
        return brId;
    }

    public void setBrId(long brId) {
        this.brId = brId;
    }
}
