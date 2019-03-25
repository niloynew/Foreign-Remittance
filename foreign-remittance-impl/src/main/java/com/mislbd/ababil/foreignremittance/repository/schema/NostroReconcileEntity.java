package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "NostroReconcile")
public class NostroReconcileEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

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

  private String messageType;

  public String getAccountNo() {
    return accountNo;
  }

  public NostroReconcileEntity setAccountNo(String accountNo) {
    this.accountNo = accountNo;
    return this;
  }

  public String getCurrency() {
    return currency;
  }

  public NostroReconcileEntity setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public String getRefToAccount() {
    return refToAccount;
  }

  public NostroReconcileEntity setRefToAccount(String refToAccount) {
    this.refToAccount = refToAccount;
    return this;
  }

  public String getRefOfServicingAccount() {
    return refOfServicingAccount;
  }

  public NostroReconcileEntity setRefOfServicingAccount(String refOfServicingAccount) {
    this.refOfServicingAccount = refOfServicingAccount;
    return this;
  }

  public String getTxnTYpe() {
    return txnTYpe;
  }

  public NostroReconcileEntity setTxnTYpe(String txnTYpe) {
    this.txnTYpe = txnTYpe;
    return this;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public NostroReconcileEntity setAmount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  public String getAdvBranch() {
    return advBranch;
  }

  public NostroReconcileEntity setAdvBranch(String advBranch) {
    this.advBranch = advBranch;
    return this;
  }

  public String getBenefCustomer() {
    return benefCustomer;
  }

  public NostroReconcileEntity setBenefCustomer(String benefCustomer) {
    this.benefCustomer = benefCustomer;
    return this;
  }

  public String getBenefInstitute() {
    return benefInstitute;
  }

  public NostroReconcileEntity setBenefInstitute(String benefInstitute) {
    this.benefInstitute = benefInstitute;
    return this;
  }

  public String getBeneNameAndAddress() {
    return beneNameAndAddress;
  }

  public NostroReconcileEntity setBeneNameAndAddress(String beneNameAndAddress) {
    this.beneNameAndAddress = beneNameAndAddress;
    return this;
  }

  public String getSuppDetails() {
    return suppDetails;
  }

  public NostroReconcileEntity setSuppDetails(String suppDetails) {
    this.suppDetails = suppDetails;
    return this;
  }

  public LocalDate getValueDate() {
    return valueDate;
  }

  public NostroReconcileEntity setValueDate(LocalDate valueDate) {
    this.valueDate = valueDate;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public NostroReconcileEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public boolean isSelected() {
    return selected;
  }

  public NostroReconcileEntity setSelected(boolean selected) {
    this.selected = selected;
    return this;
  }

  public String getDcMark() {
    return dcMark;
  }

  public NostroReconcileEntity setDcMark(String dcMark) {
    this.dcMark = dcMark;
    return this;
  }

  public long getId() {
    return id;
  }

  public NostroReconcileEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getMessageType() {
    return messageType;
  }

  public NostroReconcileEntity setMessageType(String messageType) {
    this.messageType = messageType;
    return this;
  }
}
