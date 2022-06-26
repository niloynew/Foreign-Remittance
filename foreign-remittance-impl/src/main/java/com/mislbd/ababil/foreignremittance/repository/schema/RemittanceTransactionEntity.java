package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransactionStatus;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.transaction.api.transaction.model.AccountType;
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

  @Column(name = "TRANSACTION_REFERENCE_NUMBER")
  private String transactionReferenceNumber;

  @Column(name = "INTERNAL_REFERENCE_NUMBER")
  private String internalReferenceNumber;

  @Column(name = "INSTRUMENT_NUMBER")
  private String instrumentNumber;

  @Column(name = "CB_FUND_SOURCE")
  private Long cbFundSourceId;

  // ##### General Information #####//
  @Column(name = "APPLICANT_ID")
  private Long applicantId;

  @Column(name = "BENEFICIARY_ID")
  private Long beneficiaryId;

  @Column(name = "APPLICANT_NAME")
  private String applicant;

  @Column(name = "APPLICANT_ACC_NUMBER")
  private String applicantAccountNumber;

  @Column(name = "BENEFICIARY_NAME")
  private String beneficiaryName;

  @Column(name = "BENEFICIARY_ACC_NUMBER")
  private String beneficiaryAccountNumber;

  @Column(name = "SENDER_BIC")
  private String senderBIC;

  @Column(name = "RECEIVER_BIC")
  private String receiverBIC;

  // ##### Banks Information #####//
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "remittanceTransactionEntity")
  private List<BankMappingEntity> bankMappingEntity;

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

  @Deprecated
  @Column(name = "OPERATING_RATE_TYPE")
  private Long operatingRateTypeId;

  @Deprecated
  @Column(name = "OPERATING_RATE")
  private BigDecimal operatingRate;

  @Column(name = "OPERATING_ACC_NUMBER")
  private String operatingAccountNumber;

  @Column(name = "OPERATING_ACC_CURRENCY")
  private String operatingAccountCurrency;

  @Column(name = "AMOUNT_FCY")
  private BigDecimal amountFcy;

  @Deprecated
  @Column(name = "AMOUNT_LCY")
  private BigDecimal amountLcy;

  @Deprecated
  @Column(name = "AMOUNT_RCY")
  private BigDecimal amountRcy;

  @Column(name = "VALUE_DATE")
  private LocalDate valueDate;

  @Column(name = "CATEGORY_ID")
  private Long categoryId;

  @OneToOne(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.ALL},
      mappedBy = "remittanceTransactionEntity")
  private AdditionalInformationEntity additionalInformationEntity;

  private RemittanceTransactionStatus transactionStatus;

  // import related free field
  @Column(name = "SALES_CONTRACT")
  private String salesContractNumber;

  @Column(name = "ARV_NUMBER")
  private String arvNumber;
}
