package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.NOSTRO_RECONCILE)
public class NostroReconcileEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "ACCOUNT_NO")
  private String accountNo;

  @Column(name = "CURRENCY")
  private String currency;

  @Column(name = "REF_TO_ACCOUNT")
  private String refToAccount;

  @Column(name = "REF_OF_SERVICING_ACCOUNT")
  private String refOfServicingAccount;

  @Column(name = "TXNTYPE")
  private String txnType;

  @Column(name = "AMOUNT")
  private BigDecimal amount;

  @Column(name = "ADV_BRANCH")
  private String advBranch;

  @Column(name = "BENEF_CUSTOMER")
  private String benefCustomer;

  @Column(name = "BENEF_INSTITUTE")
  private String benefInstitute;

  @Column(name = "BENE_NAME_AND_ADDRESS")
  private String beneNameAndAddress;

  @Column(name = "SUPP_DETAILS")
  private String suppDetails;

  @Column(name = "VALUE_DATE")
  private LocalDate valueDate;

  @Column(name = "REMARK")
  private String remark;

  @Column(name = "SELECTED")
  private boolean selected;

  @Column(name = "DC_MARK")
  private String dcMark;

  @Column(name = "MESSAGE_TYPE")
  private String messageType;

  @Column(name = "ORDER_INSTITUTE")
  private String orderInstitute;

  public long getId() {
    return id;
  }

  public NostroReconcileEntity setId(long id) {
    this.id = id;
    return this;
  }

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

  public String getTxnType() {
    return txnType;
  }

  public NostroReconcileEntity setTxnType(String txnType) {
    this.txnType = txnType;
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

  public String getMessageType() {
    return messageType;
  }

  public NostroReconcileEntity setMessageType(String messageType) {
    this.messageType = messageType;
    return this;
  }

  public String getOrderInstitute() {
    return orderInstitute;
  }

  public NostroReconcileEntity setOrderInstitute(String orderInstitute) {
    this.orderInstitute = orderInstitute;
    return this;
  }
}
