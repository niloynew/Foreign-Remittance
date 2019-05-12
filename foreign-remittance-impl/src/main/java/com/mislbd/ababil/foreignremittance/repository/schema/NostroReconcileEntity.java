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
  private String referenceToAccount;

  @Column(name = "REF_TO_SERVICING_ACCOUNT")
  private String referenceToServicingAccount;

  @Column(name = "TXNTYPE")
  private String txnType;

  @Column(name = "AMOUNT")
  private BigDecimal amount;

  @Column(name = "ADV_BRANCH")
  private String advisingBranch;

  @Column(name = "BENEF_CUSTOMER")
  private String beneficiaryCustomer;

  @Column(name = "BENEF_INSTITUTE")
  private String beneficiaryInstitute;

  @Column(name = "BENE_NAME_AND_ADDRESS")
  private String beneficiaryNameAndAddress;

  @Column(name = "SUPP_DETAILS")
  private String suppDetails;

  @Column(name = "SUPP_DETAILS")
  private LocalDate valueDate;

  @Column(name = "REMARK")
  private String remark;

  @Column(name = "SELECTED")
  private boolean selected;

  @Column(name = "DC_MARK")
  private String dcMark;

  @Column(name = "MESSAGE_TYPE")
  private String messageType;

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

  public String getReferenceToAccount() {
    return referenceToAccount;
  }

  public NostroReconcileEntity setReferenceToAccount(String referenceToAccount) {
    this.referenceToAccount = referenceToAccount;
    return this;
  }

  public String getReferenceToServicingAccount() {
    return referenceToServicingAccount;
  }

  public NostroReconcileEntity setReferenceToServicingAccount(String referenceToServicingAccount) {
    this.referenceToServicingAccount = referenceToServicingAccount;
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

  public String getAdvisingBranch() {
    return advisingBranch;
  }

  public NostroReconcileEntity setAdvisingBranch(String advisingBranch) {
    this.advisingBranch = advisingBranch;
    return this;
  }

  public String getBeneficiaryCustomer() {
    return beneficiaryCustomer;
  }

  public NostroReconcileEntity setBeneficiaryCustomer(String beneficiaryCustomer) {
    this.beneficiaryCustomer = beneficiaryCustomer;
    return this;
  }

  public String getBeneficiaryInstitute() {
    return beneficiaryInstitute;
  }

  public NostroReconcileEntity setBeneficiaryInstitute(String beneficiaryInstitute) {
    this.beneficiaryInstitute = beneficiaryInstitute;
    return this;
  }

  public String getBeneficiaryNameAndAddress() {
    return beneficiaryNameAndAddress;
  }

  public NostroReconcileEntity setBeneficiaryNameAndAddress(String beneficiaryNameAndAddress) {
    this.beneficiaryNameAndAddress = beneficiaryNameAndAddress;
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
}
