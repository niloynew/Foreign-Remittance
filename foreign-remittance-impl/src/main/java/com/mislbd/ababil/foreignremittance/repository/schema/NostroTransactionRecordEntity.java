package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "NOSTRO_TRANSACTION_RECORD")
public class NostroTransactionRecordEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "NOSTRO_TXN_GEN")
  @SequenceGenerator(
      name = "NOSTRO_TXN_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.NOSTRO_TXN_RECORD_SEQUENCE_NAME)
  private Long id;

  @Column(name = "NOSTRO_TXN_ID")
  private Long nostroTxnId;

  @Column(name = "NOSTRO_DR_CR")
  private String nostroDrCr;

  @Column(name = "NOSTRO_REF_ACCNO")
  private String nostroRefAccountNo;

  @Column(name = "NOSTRO_TXN_PARTICULARS")
  private String nostroTxnParticulars;

  @Column(name = "NOSTRO_DEBIT_AMT_CCY")
  private BigDecimal nostroDebitAmtCcy;

  @Column(name = "NOSTRO_CUR_CODE")
  private String nostroCurCode;

  @Column(name = "NOSTRO_TRN_INIT_BRID")
  private Long nostroTrnInitBrid;

  @Column(name = "NOSTRO_INIT_MODULE")
  private String nostroInitModule;

  @Column(name = "NOSTRO_TRN_ISVALID_YN")
  private String nostroTrnIsvalidYn;

  @Column(name = "NOSTRO_TR_CODE")
  private Long nostroTrCode;

  @Column(name = "NOSTRO_TRN_DATE")
  private LocalDate nostroTrnDate;

  @Column(name = "NOSTRO_VALUE_DATE")
  private LocalDate nostroValueDate;

  private String nostroCreatedterminal;
  private String nostroModifiedterminal;

  @Column(name = "NOSTRO_TR_REF_NO")
  private String nostroTrRefNo;

  @Column(name = "NOSTRO_OWNER_BRID")
  private Long nostroOwnerBrid;

  @Column(name = "NOSTRO_TRPOSTBALANCE")
  private Long nostroTrpostbalance;

  @Column(name = "NOSTRO_CREDIT_AMT_CCY")
  private BigDecimal nostroCreditAmtCcy;

  @Column(name = "NOSTRO_GLOBALTRN_NO")
  private Long nostroGlobaltrnNo;

  @Column(name = "NOSTRO_RECONCILED")
  private String nostroReconciled;


}
