package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class RemittanceTransaction {

  private long id;

  @Enumerated(EnumType.STRING)
  private RemittanceType remittanceType;

  @NotNull private Long transactionTypeId;

  private Long paymentPurposeId;

  private String commodityDescription;

  private String transactionReferenceNumber;

  private String instrumentNumber;

  private Long cbFundSourceId;

  private String deliveryTerm;

  @NotNull private Long applicantId;

  @NotNull private String applicantAccountNumber;

  private Boolean isBeneficiaryBankCustomer;

  private String beneficiaryRelationWithApplicant;

  @NotNull private String beneficiaryName;

  @NotNull private String beneficiaryAddress;

  @NotNull private String beneficiaryAccountNumber;

  private String b2bInformation;

  private List<BankInformation> bankInformation;

  @Enumerated(EnumType.STRING)
  @NotNull
  private AccountType debitAccountType;

  @NotNull private String debitAccountNumber;

  @Enumerated(EnumType.STRING)
  @NotNull
  private AccountType creditAccountType;

  @NotNull private String creditAccountNumber;

  @Enumerated(EnumType.STRING)
  @NotNull
  private AccountType chargeAccountType;

  @NotNull private String chargeAccountNumber;

  @NotNull private String currencyCode;

  private Long clientRateTypeId;

  private BigDecimal clientRate;

  private Long hoRateTypeId;

  private BigDecimal hoRate;

  @NotNull private BigDecimal amountFcy;

  @NotNull private BigDecimal amountLcy;

  private BigDecimal exchangeGainLoss;

  private LocalDate valueDate;

  private List<RemittanceChargeInformation> remittanceChargeInformationList;

  private BigDecimal totalChargeAmount;

  private BigDecimal totalVatAmount;

  public long getId() {
    return id;
  }

  public RemittanceTransaction setId(long id) {
    this.id = id;
    return this;
  }

  public RemittanceType getRemittanceType() {
    return remittanceType;
  }

  public RemittanceTransaction setRemittanceType(RemittanceType remittanceType) {
    this.remittanceType = remittanceType;
    return this;
  }

  public Long getTransactionTypeId() {
    return transactionTypeId;
  }

  public RemittanceTransaction setTransactionTypeId(Long transactionTypeId) {
    this.transactionTypeId = transactionTypeId;
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

  public String getTransactionReferenceNumber() {
    return transactionReferenceNumber;
  }

  public RemittanceTransaction setTransactionReferenceNumber(String transactionReferenceNumber) {
    this.transactionReferenceNumber = transactionReferenceNumber;
    return this;
  }

  public String getInstrumentNumber() {
    return instrumentNumber;
  }

  public RemittanceTransaction setInstrumentNumber(String instrumentNumber) {
    this.instrumentNumber = instrumentNumber;
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

  public Boolean getBeneficiaryBankCustomer() {
    return isBeneficiaryBankCustomer;
  }

  public RemittanceTransaction setBeneficiaryBankCustomer(Boolean beneficiaryBankCustomer) {
    isBeneficiaryBankCustomer = beneficiaryBankCustomer;
    return this;
  }

  public String getBeneficiaryRelationWithApplicant() {
    return beneficiaryRelationWithApplicant;
  }

  public RemittanceTransaction setBeneficiaryRelationWithApplicant(
      String beneficiaryRelationWithApplicant) {
    this.beneficiaryRelationWithApplicant = beneficiaryRelationWithApplicant;
    return this;
  }

  public String getBeneficiaryName() {
    return beneficiaryName;
  }

  public RemittanceTransaction setBeneficiaryName(String beneficiaryName) {
    this.beneficiaryName = beneficiaryName;
    return this;
  }

  public String getBeneficiaryAddress() {
    return beneficiaryAddress;
  }

  public RemittanceTransaction setBeneficiaryAddress(String beneficiaryAddress) {
    this.beneficiaryAddress = beneficiaryAddress;
    return this;
  }

  public String getBeneficiaryAccountNumber() {
    return beneficiaryAccountNumber;
  }

  public RemittanceTransaction setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
    this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    return this;
  }

  public String getB2bInformation() {
    return b2bInformation;
  }

  public RemittanceTransaction setB2bInformation(String b2bInformation) {
    this.b2bInformation = b2bInformation;
    return this;
  }

  public List<BankInformation> getBankInformation() {
    return bankInformation;
  }

  public RemittanceTransaction setBankInformation(List<BankInformation> bankInformation) {
    this.bankInformation = bankInformation;
    return this;
  }

  public AccountType getDebitAccountType() {
    return debitAccountType;
  }

  public RemittanceTransaction setDebitAccountType(AccountType debitAccountType) {
    this.debitAccountType = debitAccountType;
    return this;
  }

  public String getDebitAccountNumber() {
    return debitAccountNumber;
  }

  public RemittanceTransaction setDebitAccountNumber(String debitAccountNumber) {
    this.debitAccountNumber = debitAccountNumber;
    return this;
  }

  public AccountType getCreditAccountType() {
    return creditAccountType;
  }

  public RemittanceTransaction setCreditAccountType(AccountType creditAccountType) {
    this.creditAccountType = creditAccountType;
    return this;
  }

  public String getCreditAccountNumber() {
    return creditAccountNumber;
  }

  public RemittanceTransaction setCreditAccountNumber(String creditAccountNumber) {
    this.creditAccountNumber = creditAccountNumber;
    return this;
  }

  public AccountType getChargeAccountType() {
    return chargeAccountType;
  }

  public RemittanceTransaction setChargeAccountType(AccountType chargeAccountType) {
    this.chargeAccountType = chargeAccountType;
    return this;
  }

  public String getChargeAccountNumber() {
    return chargeAccountNumber;
  }

  public RemittanceTransaction setChargeAccountNumber(String chargeAccountNumber) {
    this.chargeAccountNumber = chargeAccountNumber;
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

  public BigDecimal getClientRate() {
    return clientRate;
  }

  public RemittanceTransaction setClientRate(BigDecimal clientRate) {
    this.clientRate = clientRate;
    return this;
  }

  public Long getHoRateTypeId() {
    return hoRateTypeId;
  }

  public RemittanceTransaction setHoRateTypeId(Long hoRateTypeId) {
    this.hoRateTypeId = hoRateTypeId;
    return this;
  }

  public BigDecimal getHoRate() {
    return hoRate;
  }

  public RemittanceTransaction setHoRate(BigDecimal hoRate) {
    this.hoRate = hoRate;
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

  public BigDecimal getExchangeGainLoss() {
    return exchangeGainLoss;
  }

  public RemittanceTransaction setExchangeGainLoss(BigDecimal exchangeGainLoss) {
    this.exchangeGainLoss = exchangeGainLoss;
    return this;
  }

  public List<RemittanceChargeInformation> getRemittanceChargeInformationList() {
    return remittanceChargeInformationList;
  }

  public RemittanceTransaction setRemittanceChargeInformationList(
      List<RemittanceChargeInformation> remittanceChargeInformationList) {
    this.remittanceChargeInformationList = remittanceChargeInformationList;
    return this;
  }

  public LocalDate getValueDate() {
    return valueDate;
  }

  public RemittanceTransaction setValueDate(LocalDate valueDate) {
    this.valueDate = valueDate;
    return this;
  }

  public BigDecimal getTotalChargeAmount() {
    return totalChargeAmount;
  }

  public RemittanceTransaction setTotalChargeAmount(BigDecimal totalChargeAmount) {
    this.totalChargeAmount = totalChargeAmount;
    return this;
  }

  public BigDecimal getTotalVatAmount() {
    return totalVatAmount;
  }

  public RemittanceTransaction setTotalVatAmount(BigDecimal totalVatAmount) {
    this.totalVatAmount = totalVatAmount;
    return this;
  }
}
