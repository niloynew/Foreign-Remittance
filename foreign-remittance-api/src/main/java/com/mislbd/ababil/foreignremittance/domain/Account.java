package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

    private Long id;

    private String productId;

    private String accountTitle;

    private String shadowAccountNumber;

    private String nostroAccountNumber;

    private String currencyCode;

    private String bankid;

    private String branchid;

    private LocalDate accountOpenDate;

    private BigDecimal balanceCcy;

    private BigDecimal balanceLcy;

// region <Getter and Setter>


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

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public String getShadowAccountNumber() {
        return shadowAccountNumber;
    }

    public void setShadowAccountNumber(String shadowAccountNumber) {
        this.shadowAccountNumber = shadowAccountNumber;
    }

    public String getNostroAccountNumber() {
        return nostroAccountNumber;
    }

    public void setNostroAccountNumber(String nostroAccountNumber) {
        this.nostroAccountNumber = nostroAccountNumber;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    public LocalDate getAccountOpenDate() {
        return accountOpenDate;
    }

    public void setAccountOpenDate(LocalDate accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
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
}
