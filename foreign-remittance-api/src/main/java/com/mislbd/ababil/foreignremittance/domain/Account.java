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

  private String bankId;

  private String branchId;

  private LocalDate accountOpenDate;

  private BigDecimal balanceCcy;

  private BigDecimal balanceLcy;

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

  public String getAccountTitle() {
    return accountTitle;
  }

  public Account setAccountTitle(String accountTitle) {
    this.accountTitle = accountTitle;
    return this;
  }

  public String getShadowAccountNumber() {
    return shadowAccountNumber;
  }

  public Account setShadowAccountNumber(String shadowAccountNumber) {
    this.shadowAccountNumber = shadowAccountNumber;
    return this;
  }

  public String getNostroAccountNumber() {
    return nostroAccountNumber;
  }

  public Account setNostroAccountNumber(String nostroAccountNumber) {
    this.nostroAccountNumber = nostroAccountNumber;
    return this;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public Account setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public String getBankId() {
    return bankId;
  }

  public Account setBankId(String bankId) {
    this.bankId = bankId;
    return this;
  }

  public String getBranchId() {
    return branchId;
  }

  public Account setBranchId(String branchId) {
    this.branchId = branchId;
    return this;
  }

  public LocalDate getAccountOpenDate() {
    return accountOpenDate;
  }

  public Account setAccountOpenDate(LocalDate accountOpenDate) {
    this.accountOpenDate = accountOpenDate;
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
}
