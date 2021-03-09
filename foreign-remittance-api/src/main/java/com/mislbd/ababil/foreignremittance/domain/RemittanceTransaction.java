package com.mislbd.ababil.foreignremittance.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class RemittanceTransaction {

  private long id;
  @Enumerated(EnumType.STRING)
  private RemittanceType remittanceType;
  @NotNull private Long transactionTypeId;
  @NotNull private String ppCode;
  private String commodityDescription;
  private String transactionReferenceNumber;
  private String instrumentNumber;
  private Long cbFundSourceId;
  private String deliveryTerm;
  @NotNull private Long applicantId;
  private String applicant;
  private String applicantAddress;
  private String applicantAccountNumber;
  private Boolean isBeneficiaryBankCustomer;
  private String beneficiaryRelationWithApplicant;
  @NotNull private String beneficiaryName;
  @NotNull private String beneficiaryAddress;
  private String beneficiaryAccountNumber;
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
  private Long globalTransactionNo;
  private BigDecimal totalChargeAmount;
  private BigDecimal totalChargeAmountAfterWaived;
  private BigDecimal totalVatAmountAfterWaived;
  private BigDecimal totalVatAmount;
  private String batchNumber;

}
