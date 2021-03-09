package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.foreignremittance.domain.AccountType;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
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
}
