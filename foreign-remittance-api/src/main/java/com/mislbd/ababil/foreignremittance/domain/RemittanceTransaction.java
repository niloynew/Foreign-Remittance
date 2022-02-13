package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.transaction.api.transaction.model.AccountType;
import com.mislbd.transaction.api.transaction.model.ChargeInformation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RemittanceTransaction {

  // General Information
  private long id;
  private RemittanceType remittanceType;
  private Long categoryId;
  private Long transactionTypeId;
  private String transactionReferenceNumber;
  private String ppCode;
  private String instrumentNumber;
  private Long cbFundSourceId;
  private Long applicantId;
  private String applicantAccountNumber;
  private Long beneficiaryId;
  private String beneficiaryAccountNumber;
  private String senderBIC;
  private String receiverBIC;

  // Transactional Information
  private AccountType shadowAccountType;
  private String shadowAccountNumber;
  private String shadowAccountCurrency;

  private AccountType operatingAccountType;
  private String operatingAccountNumber;
  private String operatingAccountCurrency;

  private Long operatingRateTypeId;
  private BigDecimal operatingRate;
  private BigDecimal amountFcy;
  private BigDecimal amountRcy;
  private BigDecimal amountLcy;

  private LocalDate valueDate;

  // Bank information
  private List<BankInformation> bankInformations;

  // Other information
  private AdditionalInformation additionalInformation;

  private Long initiatorBranchId;

  // Transaction build data
  private ChargeInformation chargeInformation;
  private List<CbsTemplateTransaction> cbsTransactions;
}
