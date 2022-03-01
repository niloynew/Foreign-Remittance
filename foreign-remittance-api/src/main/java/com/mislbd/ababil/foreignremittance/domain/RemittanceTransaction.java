package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.transaction.api.transaction.model.AccountType;
import com.mislbd.transaction.api.transaction.model.ChargeInformation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RemittanceTransaction {

  // General Information
  private long id;

  @NotNull(message = "Remittance type can't be empty")
  private RemittanceType remittanceType;

  @NotNull(message = "Category type can't be empty")
  private Long categoryId;

  @NotNull(message = "Transaction type can't be empty")
  private Long transactionTypeId;

  @NotNull(message = "Transaction reference number can't be empty")
  private String transactionReferenceNumber;

  private String ppCode;
  private String instrumentNumber;
  private Long cbFundSourceId;

  @NotNull(message = "Applicant can't be empty")
  private Long applicantId;

  private String applicantAccountNumber;

  @NotNull(message = "Beneficiary can't be empty")
  private Long beneficiaryId;

  private String beneficiaryAccountNumber;
  private String senderBIC;

  @NotNull(message = "Receiver BIC can't be empty")
  private String receiverBIC;

  // Transactional Information
  @NotNull(message = "Nostro account type can't be empty")
  private AccountType shadowAccountType;

  @NotNull(message = "Nostro account number can't be empty")
  private String shadowAccountNumber;

  @NotNull(message = "Nostro account currency can't be empty")
  private String shadowAccountCurrency;

  @NotNull(message = "Operating account type type can't be empty")
  private AccountType operatingAccountType;

  @NotNull(message = "Operating account type can't be empty")
  private String operatingAccountNumber;

  @NotNull(message = "Operating account currency can't be empty")
  private String operatingAccountCurrency;

  private Long operatingRateTypeId;
  private BigDecimal operatingRate;

  @NotNull(message = "Transaction amount can't be empty")
  private BigDecimal amountFcy;

  private BigDecimal amountRcy;
  private BigDecimal amountLcy;

  @NotNull(message = "Value date can't be empty")
  private LocalDate valueDate;

  // Bank information
  private List<BankInformation> bankInformations;

  // Other information
  @Valid private AdditionalInformation additionalInformation;

  private Long initiatorBranchId;

  // Transaction build data
  private ChargeInformation chargeInformation;
  private List<CbsTemplateTransaction> cbsTransactions;

  private RemittanceTransactionStatus transactionStatus;
  private Long globalTransactionNo;
}
