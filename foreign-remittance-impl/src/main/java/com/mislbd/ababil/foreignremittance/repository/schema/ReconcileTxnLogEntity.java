package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_RECONCILE_TXN_LOG_TABLE_NAME)
public class ReconcileTxnLogEntity {

  @Id
  @GeneratedValue(
      strategy = GenerationType.AUTO,
      generator = "REMITTANCE_RECONCILE_LOG_SEQUENCE_GEN")
  @SequenceGenerator(
      name = "REMITTANCE_RECONCILE_LOG_SEQUENCE_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_RECONCILE_TXN_LOG_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "EXECUTED_BY")
  private String executedBy;

  @Column(name = "INITIATING_TIME")
  private LocalDateTime initiatingTime;

  @Column(name = "INITIATOR")
  private String initiator;

  @Column(name = "INITIATOR_BRANCH")
  private Long initiatorBranch;

  @Column(name = "ACCOUNT_NUMBER")
  private String accountNumber;

  @Column(name = "TXN_NARRATION")
  private String txnNarration;

  @Column(name = "GLOBAL_TXN_NO")
  private BigDecimal globalTxnNo;

  @Column(name = "TXN_DATE")
  private LocalDate txnDate;
}
