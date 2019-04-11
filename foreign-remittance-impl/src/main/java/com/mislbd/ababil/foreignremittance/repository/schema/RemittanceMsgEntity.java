package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
public class RemittanceMsgEntity extends BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_PRODUCT_ID_GEN")
  @SequenceGenerator(
      name = "SWIFT_MSG_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.SWIFT_MSG_SEQUENCE_NAME)
  private Long id;

  private String lcNumber;
  private String relatedReference;
  private LocalDate valueDate;
  private String currency;
  private BigDecimal amount;
  private String instructedCurrency;
  private BigDecimal instructedAmount;
  private String messageType;
  private String orderingCustomerAcc;

  @Column(name = "orderingCustDetails")
  private String orderingCustomerNameAndAddress;

  private String orderingCustomerBic;
  private String orderingInsAcc;

  @Column(name = "orderingInsDetails")
  private String orderingInsNameAndAddress;

  private String orderingInsBic;
  private String SendingInsAcc;
  private String sendingInsNameAndAddress;
  private String sendingInsBic;
  private String sendersCorrespondentAcc;

  @Column(name = "sendercorrnameandaddress")
  private String sendersCorrespondentNameAndAddress;

  private String sendersCorrespondentBic;
  private String receiversCorrespondentAcc;

  @Column(name = "receivercorrnameandaddress")
  private String receiversCorrespondentNameAndAddress;

  private String receiversCorrespondentBic;
  private String intermediaryAcc;
  private String intermediaryNameAndAddress;
  private String intermediaryBic;
  private String remittanceInfo;
  private String msg;
  private String accountWithInstitutionAcc;
  private boolean processed;

  @Column(name = "accwithinsnameandaddress")
  private String accountWithInstitutionNameAndAddress;

  private String accountWithInstitutionBic;

  public Long getId() {
    return id;
  }

  public RemittanceMsgEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public String getLcNumber() {
    return lcNumber;
  }

  public RemittanceMsgEntity setLcNumber(String lcNumber) {
    this.lcNumber = lcNumber;
    return this;
  }

  public String getRelatedReference() {
    return relatedReference;
  }

  public RemittanceMsgEntity setRelatedReference(String relatedReference) {
    this.relatedReference = relatedReference;
    return this;
  }

  public LocalDate getValueDate() {
    return valueDate;
  }

  public RemittanceMsgEntity setValueDate(LocalDate valueDate) {
    this.valueDate = valueDate;
    return this;
  }

  public String getCurrency() {
    return currency;
  }

  public RemittanceMsgEntity setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public RemittanceMsgEntity setAmount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  public String getInstructedCurrency() {
    return instructedCurrency;
  }

  public RemittanceMsgEntity setInstructedCurrency(String instructedCurrency) {
    this.instructedCurrency = instructedCurrency;
    return this;
  }

  public BigDecimal getInstructedAmount() {
    return instructedAmount;
  }

  public RemittanceMsgEntity setInstructedAmount(BigDecimal instructedAmount) {
    this.instructedAmount = instructedAmount;
    return this;
  }

  public String getMessageType() {
    return messageType;
  }

  public RemittanceMsgEntity setMessageType(String messageType) {
    this.messageType = messageType;
    return this;
  }

  public String getOrderingCustomerAcc() {
    return orderingCustomerAcc;
  }

  public RemittanceMsgEntity setOrderingCustomerAcc(String orderingCustomerAcc) {
    this.orderingCustomerAcc = orderingCustomerAcc;
    return this;
  }

  public String getOrderingCustomerNameAndAddress() {
    return orderingCustomerNameAndAddress;
  }

  public RemittanceMsgEntity setOrderingCustomerNameAndAddress(
      String orderingCustomerNameAndAddress) {
    this.orderingCustomerNameAndAddress = orderingCustomerNameAndAddress;
    return this;
  }

  public String getOrderingCustomerBic() {
    return orderingCustomerBic;
  }

  public RemittanceMsgEntity setOrderingCustomerBic(String orderingCustomerBic) {
    this.orderingCustomerBic = orderingCustomerBic;
    return this;
  }

  public String getOrderingInsAcc() {
    return orderingInsAcc;
  }

  public RemittanceMsgEntity setOrderingInsAcc(String orderingInsAcc) {
    this.orderingInsAcc = orderingInsAcc;
    return this;
  }

  public String getOrderingInsNameAndAddress() {
    return orderingInsNameAndAddress;
  }

  public RemittanceMsgEntity setOrderingInsNameAndAddress(String orderingInsNameAndAddress) {
    this.orderingInsNameAndAddress = orderingInsNameAndAddress;
    return this;
  }

  public String getOrderingInsBic() {
    return orderingInsBic;
  }

  public RemittanceMsgEntity setOrderingInsBic(String orderingInsBic) {
    this.orderingInsBic = orderingInsBic;
    return this;
  }

  public String getSendingInsAcc() {
    return SendingInsAcc;
  }

  public RemittanceMsgEntity setSendingInsAcc(String sendingInsAcc) {
    SendingInsAcc = sendingInsAcc;
    return this;
  }

  public String getSendingInsNameAndAddress() {
    return sendingInsNameAndAddress;
  }

  public RemittanceMsgEntity setSendingInsNameAndAddress(String sendingInsNameAndAddress) {
    this.sendingInsNameAndAddress = sendingInsNameAndAddress;
    return this;
  }

  public String getSendingInsBic() {
    return sendingInsBic;
  }

  public RemittanceMsgEntity setSendingInsBic(String sendingInsBic) {
    this.sendingInsBic = sendingInsBic;
    return this;
  }

  public String getSendersCorrespondentAcc() {
    return sendersCorrespondentAcc;
  }

  public RemittanceMsgEntity setSendersCorrespondentAcc(String sendersCorrespondentAcc) {
    this.sendersCorrespondentAcc = sendersCorrespondentAcc;
    return this;
  }

  public String getSendersCorrespondentNameAndAddress() {
    return sendersCorrespondentNameAndAddress;
  }

  public RemittanceMsgEntity setSendersCorrespondentNameAndAddress(
      String sendersCorrespondentNameAndAddress) {
    this.sendersCorrespondentNameAndAddress = sendersCorrespondentNameAndAddress;
    return this;
  }

  public String getSendersCorrespondentBic() {
    return sendersCorrespondentBic;
  }

  public RemittanceMsgEntity setSendersCorrespondentBic(String sendersCorrespondentBic) {
    this.sendersCorrespondentBic = sendersCorrespondentBic;
    return this;
  }

  public String getReceiversCorrespondentAcc() {
    return receiversCorrespondentAcc;
  }

  public RemittanceMsgEntity setReceiversCorrespondentAcc(String receiversCorrespondentAcc) {
    this.receiversCorrespondentAcc = receiversCorrespondentAcc;
    return this;
  }

  public String getReceiversCorrespondentNameAndAddress() {
    return receiversCorrespondentNameAndAddress;
  }

  public RemittanceMsgEntity setReceiversCorrespondentNameAndAddress(
      String receiversCorrespondentNameAndAddress) {
    this.receiversCorrespondentNameAndAddress = receiversCorrespondentNameAndAddress;
    return this;
  }

  public String getReceiversCorrespondentBic() {
    return receiversCorrespondentBic;
  }

  public RemittanceMsgEntity setReceiversCorrespondentBic(String receiversCorrespondentBic) {
    this.receiversCorrespondentBic = receiversCorrespondentBic;
    return this;
  }

  public String getIntermediaryAcc() {
    return intermediaryAcc;
  }

  public RemittanceMsgEntity setIntermediaryAcc(String intermediaryAcc) {
    this.intermediaryAcc = intermediaryAcc;
    return this;
  }

  public String getIntermediaryNameAndAddress() {
    return intermediaryNameAndAddress;
  }

  public RemittanceMsgEntity setIntermediaryNameAndAddress(String intermediaryNameAndAddress) {
    this.intermediaryNameAndAddress = intermediaryNameAndAddress;
    return this;
  }

  public String getIntermediaryBic() {
    return intermediaryBic;
  }

  public RemittanceMsgEntity setIntermediaryBic(String intermediaryBic) {
    this.intermediaryBic = intermediaryBic;
    return this;
  }

  public String getRemittanceInfo() {
    return remittanceInfo;
  }

  public RemittanceMsgEntity setRemittanceInfo(String remittanceInfo) {
    this.remittanceInfo = remittanceInfo;
    return this;
  }

  public String getMsg() {
    return msg;
  }

  public RemittanceMsgEntity setMsg(String msg) {
    this.msg = msg;
    return this;
  }

  public String getAccountWithInstitutionAcc() {
    return accountWithInstitutionAcc;
  }

  public RemittanceMsgEntity setAccountWithInstitutionAcc(String accountWithInstitutionAcc) {
    this.accountWithInstitutionAcc = accountWithInstitutionAcc;
    return this;
  }

  public String getAccountWithInstitutionNameAndAddress() {
    return accountWithInstitutionNameAndAddress;
  }

  public RemittanceMsgEntity setAccountWithInstitutionNameAndAddress(
      String accountWithInstitutionNameAndAddress) {
    this.accountWithInstitutionNameAndAddress = accountWithInstitutionNameAndAddress;
    return this;
  }

  public String getAccountWithInstitutionBic() {
    return accountWithInstitutionBic;
  }

  public RemittanceMsgEntity setAccountWithInstitutionBic(String accountWithInstitutionBic) {
    this.accountWithInstitutionBic = accountWithInstitutionBic;
    return this;
  }

  public boolean isProcessed() {
    return processed;
  }

  public RemittanceMsgEntity setProcessed(boolean processed) {
    this.processed = processed;
    return this;
  }
}
