package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

  private long id;

  private Long productId;

  private String productName;

  private String accountTitle;

  private String shadowAccountNumber;

  private String nostroAccountNumber;

  private String currencyCode;

  private Long bankId;

  private Long branchId;

  private LocalDate accountOpenDate;

  private BigDecimal balance;

  private BigDecimal blockAmount;

  private boolean active;

  public long getId() {
    return id;
  }

  public Account setId(long id) {
    this.id = id;
    return this;
  }

  public Long getProductId() {
    return productId;
  }

  public Account setProductId(Long productId) {
    this.productId = productId;
    return this;
  }

  public String getProductName() {
    return productName;
  }

  public Account setProductName(String productName) {
    this.productName = productName;
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

  public Long getBankId() {
    return bankId;
  }

  public Account setBankId(Long bankId) {
    this.bankId = bankId;
    return this;
  }

  public Long getBranchId() {
    return branchId;
  }

  public Account setBranchId(Long branchId) {
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

  public BigDecimal getBalance() {
    return balance;
  }

  public Account setBalance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  public BigDecimal getBlockAmount() {
    return blockAmount;
  }

  public Account setBlockAmount(BigDecimal blockAmount) {
    this.blockAmount = blockAmount;
    return this;
  }

  public boolean isActive() {
    return active;
  }

  public Account setActive(boolean active) {
    this.active = active;
    return this;
  }
}
