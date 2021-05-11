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
  @Column(name = "SHADOW_ACC_TYPE")
  private AccountType shadowAccountType;

  @Column(name = "SHADOW_ACC_NUMBER")
  private String shadowAccountNumber;

  @Column(name = "SHADOW_ACC_CURRENCY")
  private String shadowAccountCurrency;

  @Enumerated(EnumType.STRING)
  @Column(name = "OPERATING_ACC_TYPE")
  private AccountType operatingAccountType;

  @Column(name = "OPERATING_RATE_TYPE")
  private Long operatingRateTypeId;

  @Column(name = "OPERATING_RATE")
  private BigDecimal operatingRate;

  @Column(name = "OPERATING_ACC_NUMBER")
  private String operatingAccountNumber;

  @Column(name = "OPERATING_ACC_CURRENCY")
  private String operatingAccountCurrency;

  @Column(name = "ADJ_REF_OPERATION")
  private Long adjustmentRefIdForOperation;

  @Enumerated(EnumType.STRING)
  @Column(name = "CHARGE_ACC_TYPE")
  private AccountType chargeAccountType;

  @Column(name = "CHARGE_ACC_NUMBER")
  private String chargeAccountNumber;

  @Column(name = "ADJ_REF_CHARGE")
  private Long adjustmentRefIdForCharge;

  @Column(name = "CLIENT_RATE_TYPE")
  private Long clientRateTypeId;

  @Column(name = "CLIENT_RATE")
  private BigDecimal clientRate;

  @Column(name = "IS_VALID")
  private boolean valid = true;

  @Column(name = "IS_PUBLISHED")
  private boolean publishedToXmm = false;

  @Column(name = "AMOUNT_FCY")
  private BigDecimal amountFcy;

  @Column(name = "AMOUNT_LCY")
  private BigDecimal amountLcy;

  @Column(name = "AMOUNT_RCY")
  private BigDecimal amountRcy;

  @Column(name = "VALUE_DATE")
  private LocalDate valueDate;

  @Column(name = "GLOBALTXNNO")
  private Long globalTransactionNo;

  @Column(name = "BATCH_NUMBER")
  private String batchNumber;

  @Column(name = "TOTAL_CHARGE_AMOUNT")
  private BigDecimal totalChargeAmount;

  @Column(name = "CHARGE_AFTER_WAIVED")
  private BigDecimal totalChargeAmountAfterWaived;

  @Column(name = "TOTAL_VAT_AMOUNT")
  private BigDecimal totalVatAmount;

  @Column(name = "VAT_AFTER_WAIVED")
  private BigDecimal totalVatAmountAfterWaived;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "remittanceTransaction")
  private List<RemittanceChargeInformationEntity> chargeInformationEntities;

  @OneToOne(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.ALL},
      mappedBy = "remittanceTransactionEntity")
  private RemittanceAdditionalInformationEntity remittanceAdditionalInformation;
}
