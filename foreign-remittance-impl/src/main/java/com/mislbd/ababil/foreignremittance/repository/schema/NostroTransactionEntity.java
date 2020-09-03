package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.NOSTRO_TRANSACTION)
public class NostroTransactionEntity extends BaseEntity {

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
  private String beneficiaryCustomer;

  @Column(name = "BENEF_INSTITUTE")
  private String beneficiaryInstitute;

  @Column(name = "BENE_NAME_AND_ADDRESS")
  private String beneficiaryNameAndAddress;

  @Column(name = "SUPP_DETAILS")
  private String supplementaryDetails;

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

  @Lob
  @Column(name = "MSG")
  private String messageText;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getRefToAccount() {
    return refToAccount;
  }

  public void setRefToAccount(String refToAccount) {
    this.refToAccount = refToAccount;
  }

  public String getRefOfServicingAccount() {
    return refOfServicingAccount;
  }

  public void setRefOfServicingAccount(String refOfServicingAccount) {
    this.refOfServicingAccount = refOfServicingAccount;
  }

  public String getTxnType() {
    return txnType;
  }

  public void setTxnType(String txnType) {
    this.txnType = txnType;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getAdvBranch() {
    return advBranch;
  }

  public void setAdvBranch(String advBranch) {
    this.advBranch = advBranch;
  }

  public String getBeneficiaryCustomer() {
    return beneficiaryCustomer;
  }

  public void setBeneficiaryCustomer(String beneficiaryCustomer) {
    this.beneficiaryCustomer = beneficiaryCustomer;
  }

  public String getBeneficiaryInstitute() {
    return beneficiaryInstitute;
  }

  public void setBeneficiaryInstitute(String beneficiaryInstitute) {
    this.beneficiaryInstitute = beneficiaryInstitute;
  }

  public String getBeneficiaryNameAndAddress() {
    return beneficiaryNameAndAddress;
  }

  public void setBeneficiaryNameAndAddress(String beneficiaryNameAndAddress) {
    this.beneficiaryNameAndAddress = beneficiaryNameAndAddress;
  }

  public String getSupplementaryDetails() {
    return supplementaryDetails;
  }

  public void setSupplementaryDetails(String supplementaryDetails) {
    this.supplementaryDetails = supplementaryDetails;
  }

  public LocalDate getValueDate() {
    return valueDate;
  }

  public void setValueDate(LocalDate valueDate) {
    this.valueDate = valueDate;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public String getDcMark() {
    return dcMark;
  }

  public void setDcMark(String dcMark) {
    this.dcMark = dcMark;
  }

  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  public String getOrderInstitute() {
    return orderInstitute;
  }

  public void setOrderInstitute(String orderInstitute) {
    this.orderInstitute = orderInstitute;
  }

  public String getMessageText() {
    return messageText;
  }

  public void setMessageText(String messageText) {
    this.messageText = messageText;
  }
}
