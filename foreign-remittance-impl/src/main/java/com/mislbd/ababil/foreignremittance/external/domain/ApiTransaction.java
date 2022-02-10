package com.mislbd.ababil.foreignremittance.external.domain;

import com.mislbd.transaction.api.transaction.model.AmountType;
import com.mislbd.transaction.api.transaction.model.IbtaInformation;
import com.mislbd.transaction.api.transaction.model.InstrumentType;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ApiTransaction {
  private Integer setNumber;
  @NotNull private TransactionJournalType transactionJournalType;
  @NotNull private String accountNumber;
  @NotNull private String accountType;
  @NotNull private BigDecimal amountCCY;
  @NotNull private BigDecimal amountRCY;
  @NotNull private AmountType amountType;
  @NotNull private String refCurrencyCode;
  @NotNull private String accountCurrency;
  private BigDecimal exchangeRate;
  private Integer rateType;
  @NotNull private String narration;
  private String instrumentNumber;
  private InstrumentType instrumentType;
  private LocalDate instrumentDate;
  private Long accountBranchId;
  private Long adjustmentRefId;
  private IbtaInformation ibtaInformation;
}
