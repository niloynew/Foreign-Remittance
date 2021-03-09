package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.foreignremittance.domain.AccountType;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.ID_REMITTANCE_TRANSACTION_TABLE_NAME)
public class RemittanceTransactionEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "REMITTANCE_TRANSACTION_SEQUENCE_GEN")
  @SequenceGenerator(
      name = "REMITTANCE_TRANSACTION_SEQUENCE_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_REMITTANCE_TRANSACTION_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "REMITTANCE_TYPE")
  private RemittanceType remittanceType;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "TXN_TYPE_ID")
  private TransactionTypeEntity transactionType;

  //  @Column(name = "PAYMENT_PURPOSE_ID")
  //  private Long paymentPurposeId;

  @Column(name = "PP_CODE")
  private String ppCode;

  @Column(name = "COMMODITY_DESCRIPTION")
  private String commodityDescription;

  @Column(name = "TRANSACTION_REFERENCE_NUMBER")
  private String transactionReferenceNumber;

  @Column(name = "INSTRUMENT_NUMBER")
  private String instrumentNumber;

  @Column(name = "CB_FUND_SOURCE")
  private Long cbFundSourceId;

  @Column(name = "DELIVERY_TERM")
  private String deliveryTerm;

  // ##### General Information #####//
  @Column(name = "APPLICANT_ID")
  private Long applicantId;

  @Column(name = "APPLICANT_NAME")
  private String applicant;

  @Column(name = "APPLICANT_ADDRESS")
  private String applicantAddress;

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
  private List<RemittanceTransactionBankMappingEntity> remittanceTransactionBankMappingEntity;

  // ##### Financial Information #####//
  @Enumerated(EnumType.STRING)
  @Column(name = "DEBIT_ACC_TYPE")
  private AccountType debitAccountType;

  @Column(name = "DEBIT_ACC_NUMBER")
  private String debitAccountNumber;

  @Enumerated(EnumType.STRING)
  @Column(name = "CREDIT_ACC_TYPE")
  private AccountType creditAccountType;

  @Column(name = "CREDIT_ACC_NUMBER")
  private String creditAccountNumber;

  @Enumerated(EnumType.STRING)
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

  @Column(name = "GLOBALTXNNO")
  private Long globalTransactionNo;

  @Column(name = "TOTAL_CHARGE_AMOUNT")
  private BigDecimal totalChargeAmount;

  @Column(name = "CHARGE_AFTER_WAIVED")
  private BigDecimal totalChargeAmountAfterWaived;

  @Column(name = "TOTAL_VAT_AMOUNT")
  private BigDecimal totalVatAmount;

  @Column(name = "VAT_AFTER_WAIVED")
  private BigDecimal totalVatAmountAfterWaived;

  @Column(name = "BATCH_NUMBER")
  private String batchNumber;

  @Column(name = "EXCHANGE_RATE_TYPE")
  private Long exchangeRateType;

  @Column(name = "EXCHANGE_RATE")
  private BigDecimal exchangeRate;

  @Column(name = "VALUE_DATE")
  private LocalDate valueDate;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "remittanceTransaction")
  private List<RemittanceChargeInformationEntity> chargeInformationEntities;

  public long getId() {
    return id;
  }

  public RemittanceTransactionEntity setId(long id) {
    this.id = id;
    return this;
  }

  public RemittanceType getRemittanceType() {
    return remittanceType;
  }

  public RemittanceTransactionEntity setRemittanceType(RemittanceType remittanceType) {
    this.remittanceType = remittanceType;
    return this;
  }

  public TransactionTypeEntity getTransactionType() {
    return transactionType;
  }

  public RemittanceTransactionEntity setTransactionType(TransactionTypeEntity transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  public String getPpCode() {
    return ppCode;
  }

  public RemittanceTransactionEntity setPpCode(String ppCode) {
    this.ppCode = ppCode;
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

  public String getInstrumentNumber() {
    return instrumentNumber;
  }

  public RemittanceTransactionEntity setInstrumentNumber(String instrumentNumber) {
    this.instrumentNumber = instrumentNumber;
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

  public String getApplicant() {
    return applicant;
  }

  public RemittanceTransactionEntity setApplicant(String applicant) {
    this.applicant = applicant;
    return this;
  }

  public String getApplicantAddress() {
    return applicantAddress;
  }

  public RemittanceTransactionEntity setApplicantAddress(String applicantAddress) {
    this.applicantAddress = applicantAddress;
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

  public List<RemittanceTransactionBankMappingEntity> getRemittanceTransactionBankMappingEntity() {
    return remittanceTransactionBankMappingEntity;
  }

  public RemittanceTransactionEntity setRemittanceTransactionBankMappingEntity(
      List<RemittanceTransactionBankMappingEntity> remittanceTransactionBankMappingEntity) {
    this.remittanceTransactionBankMappingEntity = remittanceTransactionBankMappingEntity;
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

  public Long getGlobalTransactionNo() {
    return globalTransactionNo;
  }

  public RemittanceTransactionEntity setGlobalTransactionNo(Long globalTransactionNo) {
    this.globalTransactionNo = globalTransactionNo;
    return this;
  }

  public BigDecimal getTotalChargeAmount() {
    return totalChargeAmount;
  }

  public RemittanceTransactionEntity setTotalChargeAmount(BigDecimal totalChargeAmount) {
    this.totalChargeAmount = totalChargeAmount;
    return this;
  }

  public BigDecimal getTotalChargeAmountAfterWaived() {
    return totalChargeAmountAfterWaived;
  }

  public RemittanceTransactionEntity setTotalChargeAmountAfterWaived(
      BigDecimal totalChargeAmountAfterWaived) {
    this.totalChargeAmountAfterWaived = totalChargeAmountAfterWaived;
    return this;
  }

  public BigDecimal getTotalVatAmount() {
    return totalVatAmount;
  }

  public RemittanceTransactionEntity setTotalVatAmount(BigDecimal totalVatAmount) {
    this.totalVatAmount = totalVatAmount;
    return this;
  }

  public BigDecimal getTotalVatAmountAfterWaived() {
    return totalVatAmountAfterWaived;
  }

  public RemittanceTransactionEntity setTotalVatAmountAfterWaived(
      BigDecimal totalVatAmountAfterWaived) {
    this.totalVatAmountAfterWaived = totalVatAmountAfterWaived;
    return this;
  }

  public String getBatchNumber() {
    return batchNumber;
  }

  public RemittanceTransactionEntity setBatchNumber(String batchNumber) {
    this.batchNumber = batchNumber;
    return this;
  }

  public Long getExchangeRateType() {
    return exchangeRateType;
  }

  public RemittanceTransactionEntity setExchangeRateType(Long exchangeRateType) {
    this.exchangeRateType = exchangeRateType;
    return this;
  }

  public BigDecimal getExchangeRate() {
    return exchangeRate;
  }

  public RemittanceTransactionEntity setExchangeRate(BigDecimal exchangeRate) {
    this.exchangeRate = exchangeRate;
    return this;
  }

  public LocalDate getValueDate() {
    return valueDate;
  }

  public RemittanceTransactionEntity setValueDate(LocalDate valueDate) {
    this.valueDate = valueDate;
    return this;
  }

  public List<RemittanceChargeInformationEntity> getChargeInformationEntities() {
    return chargeInformationEntities;
  }

  public RemittanceTransactionEntity setChargeInformationEntities(
      List<RemittanceChargeInformationEntity> chargeInformationEntities) {
    this.chargeInformationEntities = chargeInformationEntities;
    return this;
  }
}
