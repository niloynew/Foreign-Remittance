package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

  @Column(name = "BENEF_NAME")
  private String beneficiaryName;

  @Column(name = "BENEF_ADDRESS")
  private String beneficiaryAddress;

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

  @Lob
  @Column(name = "MSG")
  private String messageText;

  @Column(name = "APPLICANT")
  private String applicantName;

  @Column(name = "APPLICANT_ADDRESS")
  private String applicantAddress;

  @Column(name = "APPLICANT_ACCOUNT")
  private String applicantAccount;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "nostroTransaction")
  @Fetch(FetchMode.SUBSELECT)
  private List<BankConfiguration> bankInformation;

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

  public String getBeneficiaryName() {
    return beneficiaryName;
  }

  public void setBeneficiaryName(String beneficiaryName) {
    this.beneficiaryName = beneficiaryName;
  }

  public String getBeneficiaryAddress() {
    return beneficiaryAddress;
  }

  public void setBeneficiaryAddress(String beneficiaryAddress) {
    this.beneficiaryAddress = beneficiaryAddress;
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

  public String getMessageText() {
    return messageText;
  }

  public void setMessageText(String messageText) {
    this.messageText = messageText;
  }

  public String getApplicantName() {
    return applicantName;
  }

  public void setApplicantName(String applicantName) {
    this.applicantName = applicantName;
  }

  public String getApplicantAddress() {
    return applicantAddress;
  }

  public void setApplicantAddress(String applicantAddress) {
    this.applicantAddress = applicantAddress;
  }

  public String getApplicantAccount() {
    return applicantAccount;
  }

  public void setApplicantAccount(String applicantAccount) {
    this.applicantAccount = applicantAccount;
  }

  public List<BankConfiguration> getBankInformation() {
    return bankInformation;
  }

  public void setBankInformation(List<BankConfiguration> bankInformation) {
    this.bankInformation = bankInformation;
  }
}
