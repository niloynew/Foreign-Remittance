package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;

public class RemittanceTransaction {

  private long id;

  @NotNull private Long transactionTypeId;

  @NotNull private RemittanceType remittanceType;

  @NotNull private Long operationId;

  private Long paymentPurposeId;

  private String commodityDescription;

  private Long transactionReferenceNumber;

  private Long instructionNumber;

  private Long cbFundSourceId;

  private String deliveryTerm;

  @NotNull private Long applicantId;

  @NotNull private String applicantAccountNumber;

  @NotNull private Long beneficiaryId;

  @NotNull private String beneficiaryAccountNumber;

  private String b2bInformation;

  private List<BankInformation> bankInformation;

  @NotNull private Long debitAccountTypeId;

  @NotNull private String debitAccountNumber;

  @NotNull private Long creditAccountTypeId;

  @NotNull private String creditAccountNumber;

  @NotNull private String currencyCode;

  private Long clientRateTypeId;

  private Long hoRateTypeId;

  @NotNull private BigDecimal amountFcy;

  @NotNull private BigDecimal amountLcy;

  private BigDecimal exchangeGain;

  public long getId() {
    return id;
  }

  public RemittanceTransaction setId(long id) {
    this.id = id;
    return this;
  }

  public Long getTransactionTypeId() {
    return transactionTypeId;
  }

  public RemittanceTransaction setTransactionTypeId(Long transactionTypeId) {
    this.transactionTypeId = transactionTypeId;
    return this;
  }

  public RemittanceType getRemittanceType() {
    return remittanceType;
  }

  public RemittanceTransaction setRemittanceType(RemittanceType remittanceType) {
    this.remittanceType = remittanceType;
    return this;
  }

  public Long getOperationId() {
    return operationId;
  }

  public RemittanceTransaction setOperationId(Long operationId) {
    this.operationId = operationId;
    return this;
  }

  public Long getPaymentPurposeId() {
    return paymentPurposeId;
  }

  public RemittanceTransaction setPaymentPurposeId(Long paymentPurposeId) {
    this.paymentPurposeId = paymentPurposeId;
    return this;
  }

  public String getCommodityDescription() {
    return commodityDescription;
  }

  public RemittanceTransaction setCommodityDescription(String commodityDescription) {
    this.commodityDescription = commodityDescription;
    return this;
  }

  public Long getTransactionReferenceNumber() {
    return transactionReferenceNumber;
  }

  public RemittanceTransaction setTransactionReferenceNumber(Long transactionReferenceNumber) {
    this.transactionReferenceNumber = transactionReferenceNumber;
    return this;
  }

  public Long getInstructionNumber() {
    return instructionNumber;
  }

  public RemittanceTransaction setInstructionNumber(Long instructionNumber) {
    this.instructionNumber = instructionNumber;
    return this;
  }

  public Long getCbFundSourceId() {
    return cbFundSourceId;
  }

  public RemittanceTransaction setCbFundSourceId(Long cbFundSourceId) {
    this.cbFundSourceId = cbFundSourceId;
    return this;
  }

  public String getDeliveryTerm() {
    return deliveryTerm;
  }

  public RemittanceTransaction setDeliveryTerm(String deliveryTerm) {
    this.deliveryTerm = deliveryTerm;
    return this;
  }

  public Long getApplicantId() {
    return applicantId;
  }

  public RemittanceTransaction setApplicantId(Long applicantId) {
    this.applicantId = applicantId;
    return this;
  }

  public String getApplicantAccountNumber() {
    return applicantAccountNumber;
  }

  public RemittanceTransaction setApplicantAccountNumber(String applicantAccountNumber) {
    this.applicantAccountNumber = applicantAccountNumber;
    return this;
  }

  public Long getBeneficiaryId() {
    return beneficiaryId;
  }

  public RemittanceTransaction setBeneficiaryId(Long beneficiaryId) {
    this.beneficiaryId = beneficiaryId;
    return this;
  }

  public String getBeneficiaryAccountNumber() {
    return beneficiaryAccountNumber;
  }

  public RemittanceTransaction setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
    this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    return this;
  }

  public List<BankInformation> getBankInformation() {
    return bankInformation;
  }

  public RemittanceTransaction setBankInformation(List<BankInformation> bankInformation) {
    this.bankInformation = bankInformation;
    return this;
  }

  public Long getDebitAccountTypeId() {
    return debitAccountTypeId;
  }

  public RemittanceTransaction setDebitAccountTypeId(Long debitAccountTypeId) {
    this.debitAccountTypeId = debitAccountTypeId;
    return this;
  }

  public String getDebitAccountNumber() {
    return debitAccountNumber;
  }

  public RemittanceTransaction setDebitAccountNumber(String debitAccountNumber) {
    this.debitAccountNumber = debitAccountNumber;
    return this;
  }

  public Long getCreditAccountTypeId() {
    return creditAccountTypeId;
  }

  public RemittanceTransaction setCreditAccountTypeId(Long creditAccountTypeId) {
    this.creditAccountTypeId = creditAccountTypeId;
    return this;
  }

  public String getCreditAccountNumber() {
    return creditAccountNumber;
  }

  public RemittanceTransaction setCreditAccountNumber(String creditAccountNumber) {
    this.creditAccountNumber = creditAccountNumber;
    return this;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public RemittanceTransaction setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public Long getClientRateTypeId() {
    return clientRateTypeId;
  }

  public RemittanceTransaction setClientRateTypeId(Long clientRateTypeId) {
    this.clientRateTypeId = clientRateTypeId;
    return this;
  }

  public Long getHoRateTypeId() {
    return hoRateTypeId;
  }

  public RemittanceTransaction setHoRateTypeId(Long hoRateTypeId) {
    this.hoRateTypeId = hoRateTypeId;
    return this;
  }

  public BigDecimal getAmountFcy() {
    return amountFcy;
  }

  public RemittanceTransaction setAmountFcy(BigDecimal amountFcy) {
    this.amountFcy = amountFcy;
    return this;
  }

  public BigDecimal getAmountLcy() {
    return amountLcy;
  }

  public RemittanceTransaction setAmountLcy(BigDecimal amountLcy) {
    this.amountLcy = amountLcy;
    return this;
  }

  public BigDecimal getExchangeGain() {
    return exchangeGain;
  }

  public RemittanceTransaction setExchangeGain(BigDecimal exchangeGain) {
    this.exchangeGain = exchangeGain;
    return this;
  }

  public String getB2bInformation() {
    return b2bInformation;
  }

  public RemittanceTransaction setB2bInformation(String b2bInformation) {
    this.b2bInformation = b2bInformation;
    return this;
  }
}
