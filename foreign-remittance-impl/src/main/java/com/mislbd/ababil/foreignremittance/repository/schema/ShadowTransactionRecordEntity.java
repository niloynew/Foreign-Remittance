package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.foreignremittance.domain.OtherCbsSystemSettlementStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.SHADOW_TRANSACTION_RECORD_TABLE_NAME)
public class ShadowTransactionRecordEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "SHADOW_TRANSACTION_RECORD_ID_GEN")
  @SequenceGenerator(
      name = "SHADOW_TRANSACTION_RECORD_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.SHADOW_TRANSACTION_RECORD_SEQUENCE_NAME)
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
  private Long globalTxnNo;

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

  @Column(name = "RECONCILED_STATUS")
  private OtherCbsSystemSettlementStatus reconcileStatus = OtherCbsSystemSettlementStatus.Pending;
}
