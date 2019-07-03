package com.mislbd.ababil.foreignremittance.repository.schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = SchemaConstant.SHADOW_TRANSACTION_RECORD)
public class AccountStatementEntity {

  @Id
  @Column(name = "ID")
  private long id;

  @Column(name = "ACCOUNT_ID")
  private Long accountId;

  @Column(name = "DEBIT")
  private BigDecimal debit;

  @Column(name = "CREDIT")
  private BigDecimal credit;

  @Column(name = "DEBIT_LCY")
  private BigDecimal debitLcy;

  @Column(name = "CREDIT_LCY")
  private BigDecimal creditLcy;

  @Column(name = "EXCHANGE_RATE")
  private BigDecimal exchangeRate;

  @Column(name = "TRDEFID")
  private BigDecimal txnDefinitionId;

  @Column(name = "TRNARRATION")
  private String txnNarration;

  @Column(name = "TXNDATE")
  private LocalDate txnDate;

  @Column(name = "GLDTID")
  private BigDecimal glDetailId;

  @Column(name = "ISVALID")
  private Boolean valid;

  @Column(name = "GLOBALTXNNO")
  private BigDecimal globalTxnNo;

  @Column(name = "OWNERBRANCHID")
  private BigDecimal ownerBranchId;

  @Column(name = "INITIATORBRANCHID")
  private BigDecimal initiatorBranchId;

  @Column(name = "EVENT_ID")
  private BigDecimal eventId;

  @Column(name = "BATCHNO")
  private String batchNumber;

  @Column(name = "VALUEDATE")
  private LocalDate valueDate;

  public long getId() {
    return id;
  }

  public AccountStatementEntity setId(long id) {
    this.id = id;
    return this;
  }

  public Long getAccountId() {
    return accountId;
  }

  public AccountStatementEntity setAccountId(Long accountId) {
    this.accountId = accountId;
    return this;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public AccountStatementEntity setDebit(BigDecimal debit) {
    this.debit = debit;
    return this;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public AccountStatementEntity setCredit(BigDecimal credit) {
    this.credit = credit;
    return this;
  }

  public BigDecimal getDebitLcy() {
    return debitLcy;
  }

  public AccountStatementEntity setDebitLcy(BigDecimal debitLcy) {
    this.debitLcy = debitLcy;
    return this;
  }

  public BigDecimal getCreditLcy() {
    return creditLcy;
  }

  public AccountStatementEntity setCreditLcy(BigDecimal creditLcy) {
    this.creditLcy = creditLcy;
    return this;
  }

  public BigDecimal getExchangeRate() {
    return exchangeRate;
  }

  public AccountStatementEntity setExchangeRate(BigDecimal exchangeRate) {
    this.exchangeRate = exchangeRate;
    return this;
  }

  public BigDecimal getTxnDefinitionId() {
    return txnDefinitionId;
  }

  public AccountStatementEntity setTxnDefinitionId(BigDecimal txnDefinitionId) {
    this.txnDefinitionId = txnDefinitionId;
    return this;
  }

  public String getTxnNarration() {
    return txnNarration;
  }

  public AccountStatementEntity setTxnNarration(String txnNarration) {
    this.txnNarration = txnNarration;
    return this;
  }

  public LocalDate getTxnDate() {
    return txnDate;
  }

  public AccountStatementEntity setTxnDate(LocalDate txnDate) {
    this.txnDate = txnDate;
    return this;
  }

  public BigDecimal getGlDetailId() {
    return glDetailId;
  }

  public AccountStatementEntity setGlDetailId(BigDecimal glDetailId) {
    this.glDetailId = glDetailId;
    return this;
  }

  public Boolean getValid() {
    return valid;
  }

  public AccountStatementEntity setValid(Boolean valid) {
    this.valid = valid;
    return this;
  }

  public BigDecimal getGlobalTxnNo() {
    return globalTxnNo;
  }

  public AccountStatementEntity setGlobalTxnNo(BigDecimal globalTxnNo) {
    this.globalTxnNo = globalTxnNo;
    return this;
  }

  public BigDecimal getOwnerBranchId() {
    return ownerBranchId;
  }

  public AccountStatementEntity setOwnerBranchId(BigDecimal ownerBranchId) {
    this.ownerBranchId = ownerBranchId;
    return this;
  }

  public BigDecimal getInitiatorBranchId() {
    return initiatorBranchId;
  }

  public AccountStatementEntity setInitiatorBranchId(BigDecimal initiatorBranchId) {
    this.initiatorBranchId = initiatorBranchId;
    return this;
  }

  public BigDecimal getEventId() {
    return eventId;
  }

  public AccountStatementEntity setEventId(BigDecimal eventId) {
    this.eventId = eventId;
    return this;
  }

  public String getBatchNumber() {
    return batchNumber;
  }

  public AccountStatementEntity setBatchNumber(String batchNumber) {
    this.batchNumber = batchNumber;
    return this;
  }

  public LocalDate getValueDate() {
    return valueDate;
  }

  public AccountStatementEntity setValueDate(LocalDate valueDate) {
    this.valueDate = valueDate;
    return this;
  }
}
