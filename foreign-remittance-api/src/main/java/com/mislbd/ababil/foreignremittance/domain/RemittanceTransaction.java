package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RemittanceTransaction {

  private long id;

  @Enumerated(EnumType.STRING)
  private RemittanceType remittanceType;

  @NotNull(message = "Transaction type can't be null")
  private Long transactionTypeId;

  private String ppCode;
  private String commodityDescription;
  private String transactionReferenceNumber;
  private String instrumentNumber;
  private Long cbFundSourceId;
  private String deliveryTerm;

  @NotNull(message = "Applicant can't be null")
  private Long applicantId;

  private Long customerId;

  private String applicant;
  private String applicantAddress;
  private String applicantAccountNumber;

  @NotNull(message = "Beneficiary can't be null")
  private String beneficiaryName;

  @NotNull(message = "Beneficiary address can't be null")
  private String beneficiaryAddress;

  private String beneficiaryAccountNumber;
  private String b2bInformation;
  private List<BankInformation> bankInformation;

  @Enumerated(EnumType.STRING)
  @NotNull
  private AccountType shadowAccountType;

  @NotNull private String shadowAccountNumber;
  @NotNull private String shadowAccountCurrency;

  @Enumerated(EnumType.STRING)
  @NotNull
  private AccountType operatingAccountType;

  @NotNull private String operatingAccountNumber;
  @NotNull private String operatingAccountCurrency;
  private Long adjustmentRefIdForOperation;
  private Long operatingRateTypeId;
  private BigDecimal operatingRate;

  @Enumerated(EnumType.STRING)
  @NotNull
  private AccountType chargeAccountType;

  @NotNull private String chargeAccountNumber;
  private Long adjustmentRefIdForCharge;

  private Long clientRateTypeId;
  private BigDecimal clientRate;

  @NotNull(message = "Transaction amount can't be null or zero")
  private BigDecimal amountFcy;

  private boolean valid = true;
  private boolean publishedToXmm = false;
  private boolean authorizedToXmm = false;

  @NotNull private BigDecimal amountLcy;

  @NotNull(message = "Reference amount can't be null or zero")
  private BigDecimal amountRcy;

  private LocalDate valueDate;
  private Long globalTransactionNo;
  private String batchNumber;
  private RemittanceCategory remittanceCategory;

  private List<RemittanceChargeInformation> remittanceChargeInformationList;
  private RemittanceAdditionalInformation remittanceAdditionalInformation;
  private BigDecimal totalChargeAmount = BigDecimal.ZERO;
  private BigDecimal totalChargeAmountAfterWaived = BigDecimal.ZERO;
  private BigDecimal totalVatAmount = BigDecimal.ZERO;
  private BigDecimal totalVatAmountAfterWaived = BigDecimal.ZERO;

  private boolean doPublishMT103;
}
