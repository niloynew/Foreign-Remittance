package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ReconcileTxnLog {
  private long id;
  private String executedBy;
  private LocalDateTime initiatingTime;
  private String initiator;
  private Long initiatorBranch;
  private String accountNumber;
  private String txnNarration;
  private BigDecimal globalTxnNo;
  private LocalDate txnDate;
}
