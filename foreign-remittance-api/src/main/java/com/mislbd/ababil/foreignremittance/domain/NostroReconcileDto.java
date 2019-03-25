package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NostroReconcileDto {
  private String accountNo;
  private String currency;
  private String refToAccount;
  private String refOfServicingAccount;
  private String txnTYpe;
  private BigDecimal amount;
  private String advBranch;
  private String benefCustomer;
  private String benefInstitute;
  private String beneNameAndAddress;
  private String suppDetails;
  private LocalDate valueDate;
  private String remark;
  private boolean selected;
  private String dcMark;
  private long id;
  private String messageType;

  public String getAccountNo() {
    return accountNo;
  }

  public NostroReconcileDto setAccountNo(String accountNo) {
    this.accountNo = accountNo;
    return this;
  }

  public String getCurrency() {
    return currency;
  }

  public NostroReconcileDto setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public String getRefToAccount() {
    return refToAccount;
  }

  public NostroReconcileDto setRefToAccount(String refToAccount) {
    this.refToAccount = refToAccount;
    return this;
  }

  public String getRefOfServicingAccount() {
    return refOfServicingAccount;
  }

  public NostroReconcileDto setRefOfServicingAccount(String refOfServicingAccount) {
    this.refOfServicingAccount = refOfServicingAccount;
    return this;
  }

  public String getTxnTYpe() {
    return txnTYpe;
  }

  public NostroReconcileDto setTxnTYpe(String txnTYpe) {
    this.txnTYpe = txnTYpe;
    return this;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public NostroReconcileDto setAmount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  public String getAdvBranch() {
    return advBranch;
  }

  public NostroReconcileDto setAdvBranch(String advBranch) {
    this.advBranch = advBranch;
    return this;
  }

  public String getBenefCustomer() {
    return benefCustomer;
  }

  public NostroReconcileDto setBenefCustomer(String benefCustomer) {
    this.benefCustomer = benefCustomer;
    return this;
  }

  public String getBenefInstitute() {
    return benefInstitute;
  }

  public NostroReconcileDto setBenefInstitute(String benefInstitute) {
    this.benefInstitute = benefInstitute;
    return this;
  }

  public String getBeneNameAndAddress() {
    return beneNameAndAddress;
  }

  public NostroReconcileDto setBeneNameAndAddress(String beneNameAndAddress) {
    this.beneNameAndAddress = beneNameAndAddress;
    return this;
  }

  public String getSuppDetails() {
    return suppDetails;
  }

  public NostroReconcileDto setSuppDetails(String suppDetails) {
    this.suppDetails = suppDetails;
    return this;
  }

  public LocalDate getValueDate() {
    return valueDate;
  }

  public NostroReconcileDto setValueDate(LocalDate valueDate) {
    this.valueDate = valueDate;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public NostroReconcileDto setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public boolean isSelected() {
    return selected;
  }

  public NostroReconcileDto setSelected(boolean selected) {
    this.selected = selected;
    return this;
  }

  public String getDcMark() {
    return dcMark;
  }

  public NostroReconcileDto setDcMark(String dcMark) {
    this.dcMark = dcMark;
    return this;
  }

  public long getId() {
    return id;
  }

  public NostroReconcileDto setId(long id) {
    this.id = id;
    return this;
  }

  public String getMessageType() {
    return messageType;
  }

  public NostroReconcileDto setMessageType(String messageType) {
    this.messageType = messageType;
    return this;
  }
}
