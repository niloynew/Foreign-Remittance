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

  @Column(name = "NOSTRO_TXNID")
  private Long nostroTxnid;

  @Column(name = "NOSTRO_DR_CR")
  private String nostroDrCr;

  @Column(name = "NOSTRO_REF_ACCNO")
  private String nostroRefAccno;

  @Column(name = "NOSTRO_TRPARTICULARS")
  private String nostroTrparticulars;

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

  public Long getId() {
    return id;
  }

  public NostroTransactionRecordEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getNostroTxnid() {
    return nostroTxnid;
  }

  public NostroTransactionRecordEntity setNostroTxnid(Long nostroTxnid) {
    this.nostroTxnid = nostroTxnid;
    return this;
  }

  public String getNostroDrCr() {
    return nostroDrCr;
  }

  public NostroTransactionRecordEntity setNostroDrCr(String nostroDrCr) {
    this.nostroDrCr = nostroDrCr;
    return this;
  }

  public String getNostroRefAccno() {
    return nostroRefAccno;
  }

  public NostroTransactionRecordEntity setNostroRefAccno(String nostroRefAccno) {
    this.nostroRefAccno = nostroRefAccno;
    return this;
  }

  public String getNostroTrparticulars() {
    return nostroTrparticulars;
  }

  public NostroTransactionRecordEntity setNostroTrparticulars(String nostroTrparticulars) {
    this.nostroTrparticulars = nostroTrparticulars;
    return this;
  }

  public BigDecimal getNostroDebitAmtCcy() {
    return nostroDebitAmtCcy;
  }

  public NostroTransactionRecordEntity setNostroDebitAmtCcy(BigDecimal nostroDebitAmtCcy) {
    this.nostroDebitAmtCcy = nostroDebitAmtCcy;
    return this;
  }

  public String getNostroCurCode() {
    return nostroCurCode;
  }

  public NostroTransactionRecordEntity setNostroCurCode(String nostroCurCode) {
    this.nostroCurCode = nostroCurCode;
    return this;
  }

  public Long getNostroTrnInitBrid() {
    return nostroTrnInitBrid;
  }

  public NostroTransactionRecordEntity setNostroTrnInitBrid(Long nostroTrnInitBrid) {
    this.nostroTrnInitBrid = nostroTrnInitBrid;
    return this;
  }

  public String getNostroInitModule() {
    return nostroInitModule;
  }

  public NostroTransactionRecordEntity setNostroInitModule(String nostroInitModule) {
    this.nostroInitModule = nostroInitModule;
    return this;
  }

  public String getNostroTrnIsvalidYn() {
    return nostroTrnIsvalidYn;
  }

  public NostroTransactionRecordEntity setNostroTrnIsvalidYn(String nostroTrnIsvalidYn) {
    this.nostroTrnIsvalidYn = nostroTrnIsvalidYn;
    return this;
  }

  public Long getNostroTrCode() {
    return nostroTrCode;
  }

  public NostroTransactionRecordEntity setNostroTrCode(Long nostroTrCode) {
    this.nostroTrCode = nostroTrCode;
    return this;
  }

  public LocalDate getNostroTrnDate() {
    return nostroTrnDate;
  }

  public NostroTransactionRecordEntity setNostroTrnDate(LocalDate nostroTrnDate) {
    this.nostroTrnDate = nostroTrnDate;
    return this;
  }

  public LocalDate getNostroValueDate() {
    return nostroValueDate;
  }

  public NostroTransactionRecordEntity setNostroValueDate(LocalDate nostroValueDate) {
    this.nostroValueDate = nostroValueDate;
    return this;
  }

  public String getNostroCreatedterminal() {
    return nostroCreatedterminal;
  }

  public NostroTransactionRecordEntity setNostroCreatedterminal(String nostroCreatedterminal) {
    this.nostroCreatedterminal = nostroCreatedterminal;
    return this;
  }

  public String getNostroModifiedterminal() {
    return nostroModifiedterminal;
  }

  public NostroTransactionRecordEntity setNostroModifiedterminal(String nostroModifiedterminal) {
    this.nostroModifiedterminal = nostroModifiedterminal;
    return this;
  }

  public String getNostroTrRefNo() {
    return nostroTrRefNo;
  }

  public NostroTransactionRecordEntity setNostroTrRefNo(String nostroTrRefNo) {
    this.nostroTrRefNo = nostroTrRefNo;
    return this;
  }

  public Long getNostroOwnerBrid() {
    return nostroOwnerBrid;
  }

  public NostroTransactionRecordEntity setNostroOwnerBrid(Long nostroOwnerBrid) {
    this.nostroOwnerBrid = nostroOwnerBrid;
    return this;
  }

  public Long getNostroTrpostbalance() {
    return nostroTrpostbalance;
  }

  public NostroTransactionRecordEntity setNostroTrpostbalance(Long nostroTrpostbalance) {
    this.nostroTrpostbalance = nostroTrpostbalance;
    return this;
  }

  public BigDecimal getNostroCreditAmtCcy() {
    return nostroCreditAmtCcy;
  }

  public NostroTransactionRecordEntity setNostroCreditAmtCcy(BigDecimal nostroCreditAmtCcy) {
    this.nostroCreditAmtCcy = nostroCreditAmtCcy;
    return this;
  }

  public Long getNostroGlobaltrnNo() {
    return nostroGlobaltrnNo;
  }

  public NostroTransactionRecordEntity setNostroGlobaltrnNo(Long nostroGlobaltrnNo) {
    this.nostroGlobaltrnNo = nostroGlobaltrnNo;
    return this;
  }

  public String getNostroReconciled() {
    return nostroReconciled;
  }

  public NostroTransactionRecordEntity setNostroReconciled(String nostroReconciled) {
    this.nostroReconciled = nostroReconciled;
    return this;
  }
}
