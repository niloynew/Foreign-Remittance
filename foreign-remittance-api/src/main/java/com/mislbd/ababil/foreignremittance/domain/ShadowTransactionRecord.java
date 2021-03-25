package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ShadowTransactionRecord {

  private long id;
  private Long accountId;
  private String accountNumber;
  private BigDecimal debit;
  private BigDecimal credit;
  private BigDecimal debitLcy;
  private BigDecimal creditLcy;
  private String currency;
  private BigDecimal exchangeRate;
  private BigDecimal txnDefinitionId;
  private String txnNarration;
  private LocalDate txnDate;
  private BigDecimal glDetailId;
  private Boolean valid;
  private BigDecimal globalTxnNo;
  private BigDecimal ownerBranchId;
  private BigDecimal initiatorBranchId;
  private BigDecimal eventId;
  private String batchNumber;
  private LocalDate valueDate;
  private BigDecimal postBalance;
  private OtherCbsSystemSettlementStatus reconcileStatus;
}
