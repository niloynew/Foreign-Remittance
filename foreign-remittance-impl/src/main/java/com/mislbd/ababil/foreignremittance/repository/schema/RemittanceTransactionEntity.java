package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.foreignremittance.domain.AccountType;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.REMITTANCE_TRANSACTION_TABLE_NAME)
public class RemittanceTransactionEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "REMITTANCE_TRANSACTION_SEQUENCE_GEN")
  @SequenceGenerator(
      name = "REMITTANCE_TRANSACTION_SEQUENCE_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.REMITTANCE_TRANSACTION_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "TXN_TYPE_ID")
  private TransactionTypeEntity transactionType;

  @Column(name = "PAYMENT_PURPOSE_ID")
  private Long paymentPurposeId;

  @Column(name = "COMMODITY_DESCRIPTION")
  private String commodityDescription;

  @Column(name = "TRANSACTION_REFERENCE_NUMBER")
  private String transactionReferenceNumber;

  @Column(name = "INSTRUCTION_NUMBER")
  private String instructionNumber;

  @Column(name = "CB_FUND_SOURCE")
  private Long cbFundSourceId;

  @Column(name = "DELIVERY_TERM")
  private String deliveryTerm;

  // ##### General Information #####//
  @Column(name = "APPLICANT_ID")
  private Long applicantId;

  @Column(name = "APPLICANT_ACC_NUMBER")
  private String applicantAccountNumber;

  @Column(name = "BENEFICIARY_NAME")
  private String beneficiaryName;

  @Column(name = "BENEFICIARY_ADDRESS")
  private String beneficiaryAddress;

  @Column(name = "BENEFICIARY_ACC_NUMBER")
  private String beneficiaryAccountNumber;

  @Column(name = "B2B_INFORMATION")
  private String b2bInformation;

  // ##### Banks Information #####//
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "remittanceTransaction")
  private List<BankInformationEntity> bankInformationEntity;

  // ##### Financial Information #####//
  @Enumerated(EnumType.STRING)
  @Column(name = "DEBIT_ACC_TYPE")
  private AccountType debitAccountType;

  @Column(name = "DEBIT_ACC_NUMBER")
  private String debitAccountNumber;

  @Column(name = "CREDIT_ACC_TYPE")
  private AccountType creditAccountType;

  @Column(name = "CREDIT_ACC_NUMBER")
  private String creditAccountNumber;

  @Column(name = "CHARGE_ACC_TYPE")
  private AccountType chargeAccountType;

  @Column(name = "CHARGE_ACC_NUMBER")
  private String chargeAccountNumber;

  @Column(name = "CURRENCY_CODE")
  private String currencyCode;

  @Column(name = "CLIENT_RATE_TYPE")
  private Long clientRateTypeId;

  @Column(name = "CLIENT_RATE")
  private BigDecimal clientRate;

  @Column(name = "HO_RATE_TYPE")
  private Long hoRateTypeId;

  @Column(name = "HO_RATE")
  private BigDecimal hoRate;

  @Column(name = "AMOUNT_FCY")
  private BigDecimal amountFcy;

  @Column(name = "AMOUNT_LCY")
  private BigDecimal amountLcy;

  @Column(name = "EXCHANGE_GAIN_LOSS")
  private BigDecimal exchangeGainLoss;

  public long getId() {
    return id;
  }

  public RemittanceTransactionEntity setId(long id) {
    this.id = id;
    return this;
  }

  public TransactionTypeEntity getTransactionType() {
    return transactionType;
  }

  public RemittanceTransactionEntity setTransactionType(TransactionTypeEntity transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  public Long getPaymentPurposeId() {
    return paymentPurposeId;
  }

  public RemittanceTransactionEntity setPaymentPurposeId(Long paymentPurposeId) {
    this.paymentPurposeId = paymentPurposeId;
    return this;
  }

  public String getCommodityDescription() {
    return commodityDescription;
  }

  public RemittanceTransactionEntity setCommodityDescription(String commodityDescription) {
    this.commodityDescription = commodityDescription;
    return this;
  }

  public String getTransactionReferenceNumber() {
    return transactionReferenceNumber;
  }

  public RemittanceTransactionEntity setTransactionReferenceNumber(
      String transactionReferenceNumber) {
    this.transactionReferenceNumber = transactionReferenceNumber;
    return this;
  }

  public String getInstructionNumber() {
    return instructionNumber;
  }

  public RemittanceTransactionEntity setInstructionNumber(String instructionNumber) {
    this.instructionNumber = instructionNumber;
    return this;
  }

  public Long getCbFundSourceId() {
    return cbFundSourceId;
  }

  public RemittanceTransactionEntity setCbFundSourceId(Long cbFundSourceId) {
    this.cbFundSourceId = cbFundSourceId;
    return this;
  }

  public String getDeliveryTerm() {
    return deliveryTerm;
  }

  public RemittanceTransactionEntity setDeliveryTerm(String deliveryTerm) {
    this.deliveryTerm = deliveryTerm;
    return this;
  }

  public Long getApplicantId() {
    return applicantId;
  }

  public RemittanceTransactionEntity setApplicantId(Long applicantId) {
    this.applicantId = applicantId;
    return this;
  }

  public String getApplicantAccountNumber() {
    return applicantAccountNumber;
  }

  public RemittanceTransactionEntity setApplicantAccountNumber(String applicantAccountNumber) {
    this.applicantAccountNumber = applicantAccountNumber;
    return this;
  }

  public String getBeneficiaryName() {
    return beneficiaryName;
  }

  public RemittanceTransactionEntity setBeneficiaryName(String beneficiaryName) {
    this.beneficiaryName = beneficiaryName;
    return this;
  }

  public String getBeneficiaryAddress() {
    return beneficiaryAddress;
  }

  public RemittanceTransactionEntity setBeneficiaryAddress(String beneficiaryAddress) {
    this.beneficiaryAddress = beneficiaryAddress;
    return this;
  }

  public String getBeneficiaryAccountNumber() {
    return beneficiaryAccountNumber;
  }

  public RemittanceTransactionEntity setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
    this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    return this;
  }

  public String getB2bInformation() {
    return b2bInformation;
  }

  public RemittanceTransactionEntity setB2bInformation(String b2bInformation) {
    this.b2bInformation = b2bInformation;
    return this;
  }

  public List<BankInformationEntity> getBankInformationEntity() {
    return bankInformationEntity;
  }

  public RemittanceTransactionEntity setBankInformationEntity(
      List<BankInformationEntity> bankInformationEntity) {
    this.bankInformationEntity = bankInformationEntity;
    return this;
  }

  public AccountType getDebitAccountType() {
    return debitAccountType;
  }

  public RemittanceTransactionEntity setDebitAccountType(AccountType debitAccountType) {
    this.debitAccountType = debitAccountType;
    return this;
  }

  public String getDebitAccountNumber() {
    return debitAccountNumber;
  }

  public RemittanceTransactionEntity setDebitAccountNumber(String debitAccountNumber) {
    this.debitAccountNumber = debitAccountNumber;
    return this;
  }

  public AccountType getCreditAccountType() {
    return creditAccountType;
  }

  public RemittanceTransactionEntity setCreditAccountType(AccountType creditAccountType) {
    this.creditAccountType = creditAccountType;
    return this;
  }

  public String getCreditAccountNumber() {
    return creditAccountNumber;
  }

  public RemittanceTransactionEntity setCreditAccountNumber(String creditAccountNumber) {
    this.creditAccountNumber = creditAccountNumber;
    return this;
  }

  public AccountType getChargeAccountType() {
    return chargeAccountType;
  }

  public RemittanceTransactionEntity setChargeAccountType(AccountType chargeAccountType) {
    this.chargeAccountType = chargeAccountType;
    return this;
  }

  public String getChargeAccountNumber() {
    return chargeAccountNumber;
  }

  public RemittanceTransactionEntity setChargeAccountNumber(String chargeAccountNumber) {
    this.chargeAccountNumber = chargeAccountNumber;
    return this;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public RemittanceTransactionEntity setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public Long getClientRateTypeId() {
    return clientRateTypeId;
  }

  public RemittanceTransactionEntity setClientRateTypeId(Long clientRateTypeId) {
    this.clientRateTypeId = clientRateTypeId;
    return this;
  }

  public BigDecimal getClientRate() {
    return clientRate;
  }

  public RemittanceTransactionEntity setClientRate(BigDecimal clientRate) {
    this.clientRate = clientRate;
    return this;
  }

  public Long getHoRateTypeId() {
    return hoRateTypeId;
  }

  public RemittanceTransactionEntity setHoRateTypeId(Long hoRateTypeId) {
    this.hoRateTypeId = hoRateTypeId;
    return this;
  }

  public BigDecimal getHoRate() {
    return hoRate;
  }

  public RemittanceTransactionEntity setHoRate(BigDecimal hoRate) {
    this.hoRate = hoRate;
    return this;
  }

  public BigDecimal getAmountFcy() {
    return amountFcy;
  }

  public RemittanceTransactionEntity setAmountFcy(BigDecimal amountFcy) {
    this.amountFcy = amountFcy;
    return this;
  }

  public BigDecimal getAmountLcy() {
    return amountLcy;
  }

  public RemittanceTransactionEntity setAmountLcy(BigDecimal amountLcy) {
    this.amountLcy = amountLcy;
    return this;
  }

  public BigDecimal getExchangeGainLoss() {
    return exchangeGainLoss;
  }

  public RemittanceTransactionEntity setExchangeGainLoss(BigDecimal exchangeGainLoss) {
    this.exchangeGainLoss = exchangeGainLoss;
    return this;
  }
}
