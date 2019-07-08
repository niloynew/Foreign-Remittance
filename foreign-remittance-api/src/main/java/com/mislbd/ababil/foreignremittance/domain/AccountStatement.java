package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountStatement {

  private long id;
  private Long accountId;
  private BigDecimal debit;
  private BigDecimal credit;
  private BigDecimal debitLcy;
  private BigDecimal creditLcy;
  private BigDecimal exchangeRate;
  private BigDecimal txnDefinitionId;
  private String txnNarration;
  private LocalDate txnDate;
  private BigDecimal glDetailId;
  private Boolean valid;
  private BigDecimal globalTxnNo;
  private BigDecimal ownerBranchId;
  private BigDecimal initiatorBranchId;
  private BigDecimal eventId;
  private String batchNumber;
  private LocalDate valueDate;
  private BigDecimal postBalance;

  public long getId() {
    return id;
  }

  public AccountStatement setId(long id) {
    this.id = id;
    return this;
  }

  public Long getAccountId() {
    return accountId;
  }

  public AccountStatement setAccountId(Long accountId) {
    this.accountId = accountId;
    return this;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public AccountStatement setDebit(BigDecimal debit) {
    this.debit = debit;
    return this;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public AccountStatement setCredit(BigDecimal credit) {
    this.credit = credit;
    return this;
  }

  public BigDecimal getDebitLcy() {
    return debitLcy;
  }

  public AccountStatement setDebitLcy(BigDecimal debitLcy) {
    this.debitLcy = debitLcy;
    return this;
  }

  public BigDecimal getCreditLcy() {
    return creditLcy;
  }

  public AccountStatement setCreditLcy(BigDecimal creditLcy) {
    this.creditLcy = creditLcy;
    return this;
  }

  public BigDecimal getExchangeRate() {
    return exchangeRate;
  }

  public AccountStatement setExchangeRate(BigDecimal exchangeRate) {
    this.exchangeRate = exchangeRate;
    return this;
  }

  public BigDecimal getTxnDefinitionId() {
    return txnDefinitionId;
  }

  public AccountStatement setTxnDefinitionId(BigDecimal txnDefinitionId) {
    this.txnDefinitionId = txnDefinitionId;
    return this;
  }

  public String getTxnNarration() {
    return txnNarration;
  }

  public AccountStatement setTxnNarration(String txnNarration) {
    this.txnNarration = txnNarration;
    return this;
  }

  public LocalDate getTxnDate() {
    return txnDate;
  }

  public AccountStatement setTxnDate(LocalDate txnDate) {
    this.txnDate = txnDate;
    return this;
  }

  public BigDecimal getGlDetailId() {
    return glDetailId;
  }

  public AccountStatement setGlDetailId(BigDecimal glDetailId) {
    this.glDetailId = glDetailId;
    return this;
  }

  public Boolean getValid() {
    return valid;
  }

  public AccountStatement setValid(Boolean valid) {
    this.valid = valid;
    return this;
  }

  public BigDecimal getGlobalTxnNo() {
    return globalTxnNo;
  }

  public AccountStatement setGlobalTxnNo(BigDecimal globalTxnNo) {
    this.globalTxnNo = globalTxnNo;
    return this;
  }

  public BigDecimal getOwnerBranchId() {
    return ownerBranchId;
  }

  public AccountStatement setOwnerBranchId(BigDecimal ownerBranchId) {
    this.ownerBranchId = ownerBranchId;
    return this;
  }

  public BigDecimal getInitiatorBranchId() {
    return initiatorBranchId;
  }

  public AccountStatement setInitiatorBranchId(BigDecimal initiatorBranchId) {
    this.initiatorBranchId = initiatorBranchId;
    return this;
  }

  public BigDecimal getEventId() {
    return eventId;
  }

  public AccountStatement setEventId(BigDecimal eventId) {
    this.eventId = eventId;
    return this;
  }

  public String getBatchNumber() {
    return batchNumber;
  }

  public AccountStatement setBatchNumber(String batchNumber) {
    this.batchNumber = batchNumber;
    return this;
  }

  public LocalDate getValueDate() {
    return valueDate;
  }

  public AccountStatement setValueDate(LocalDate valueDate) {
    this.valueDate = valueDate;
    return this;
  }

  public BigDecimal getPostBalance() {
    return postBalance;
  }

  public AccountStatement setPostBalance(BigDecimal postBalance) {
    this.postBalance = postBalance;
    return this;
  }
}
