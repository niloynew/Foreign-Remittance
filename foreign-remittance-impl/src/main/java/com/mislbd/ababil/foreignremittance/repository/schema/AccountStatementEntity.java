package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.foreignremittance.domain.NostroReconcileStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.SHADOW_TRANSACTION_RECORD)
public class AccountStatementEntity {

  @Id
  @Column(name = "ID")
  private long id;

  @Column(name = "ACCOUNT_ID")
  private Long accountId;

  @Column(name = "DEBIT")
  private BigDecimal debit;

  @Column(name = "CREDIT")
  private BigDecimal credit;

  @Column(name = "DEBIT_LCY")
  private BigDecimal debitLcy;

  @Column(name = "CREDIT_LCY")
  private BigDecimal creditLcy;

  @Column(name = "EXCHANGE_RATE")
  private BigDecimal exchangeRate;

  @Column(name = "TRDEFID")
  private BigDecimal txnDefinitionId;

  @Column(name = "TRNARRATION")
  private String txnNarration;

  @Column(name = "TXNDATE")
  private LocalDate txnDate;

  @Column(name = "GLDTID")
  private BigDecimal glDetailId;

  @Column(name = "ISVALID")
  private Boolean valid;

  @Column(name = "GLOBALTXNNO")
  private BigDecimal globalTxnNo;

  @Column(name = "OWNERBRANCHID")
  private BigDecimal ownerBranchId;

  @Column(name = "INITIATORBRANCHID")
  private BigDecimal initiatorBranchId;

  @Column(name = "EVENT_ID")
  private BigDecimal eventId;

  @Column(name = "BATCHNO")
  private String batchNumber;

  @Column(name = "VALUEDATE")
  private LocalDate valueDate;

  @Column(name = "POST_BALANCE")
  private BigDecimal postBalance;

  @Column(name = "RECONCILED_STATUS", columnDefinition = "default 0")
  private NostroReconcileStatus reconcileStatus;
}
