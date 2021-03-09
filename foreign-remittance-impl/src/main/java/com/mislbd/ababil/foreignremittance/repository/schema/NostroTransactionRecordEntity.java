package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.NOSTRO_TRANSACTION_RECORD_TABLE_NAME)
public class NostroTransactionRecordEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "NOSTRO_TRANSACTION_RECORD_ID_GEN")
  @SequenceGenerator(
      name = "NOSTRO_TRANSACTION_RECORD_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.NOSTRO_TRANSACTION_RECORD_SEQUENCE_NAME)
  private Long id;

  @Column(name = "NOSTRO_TXNID")
  private Long nostroTxnId;

  @Column(name = "NOSTRO_DR_CR")
  private String nostroDrCr;

  @Column(name = "NOSTRO_REF_ACCNO")
  private String nostroRefAccountNo;

  @Column(name = "NOSTRO_TRPARTICULARS")
  private String nostroTxnParticulars;

  @Column(name = "NOSTRO_DEBIT_AMT_CCY")
  private BigDecimal nostroDebitAmtCcy;

  @Column(name = "NOSTRO_CUR_CODE")
  private String nostroCurCode;

  @Column(name = "NOSTRO_TRN_INIT_BRID")
  private Long nostroTxnInitBrid;

  @Column(name = "NOSTRO_INIT_MODULE")
  private String nostroInitModule;

  @Column(name = "NOSTRO_TRN_ISVALID_YN")
  private String nostroTxnIsValidYn;

  @Column(name = "NOSTRO_TR_CODE")
  private Long nostroTxnCode;

  @Column(name = "NOSTRO_TRN_DATE")
  private LocalDate nostroTxnDate;

  @Column(name = "NOSTRO_VALUE_DATE")
  private LocalDate nostroValueDate;

  @Column(name = "NOSTRO_CREATEDTERMINAL")
  private String nostroCreatedTerminal;

  @Column(name = "NOSTRO_MODIFIEDTERMINAL")
  private String nostroModifiedTerminal;

  @Column(name = "NOSTRO_TR_REF_NO")
  private String nostroTxnReferenceNo;

  @Column(name = "NOSTRO_OWNER_BRID")
  private Long nostroOwnerBrid;

  @Column(name = "NOSTRO_TRPOSTBALANCE")
  private Long nostroTxnPostBalance;

  @Column(name = "NOSTRO_CREDIT_AMT_CCY")
  private BigDecimal nostroCreditAmtCcy;

  @Column(name = "NOSTRO_GLOBALTRN_NO")
  private Long nostroGlobalTxnNo;

  @Column(name = "NOSTRO_RECONCILED")
  private String nostroReconciled;

  public Long getId() {
    return id;
  }

  public NostroTransactionRecordEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getNostroTxnId() {
    return nostroTxnId;
  }

  public NostroTransactionRecordEntity setNostroTxnId(Long nostroTxnId) {
    this.nostroTxnId = nostroTxnId;
    return this;
  }

  public String getNostroDrCr() {
    return nostroDrCr;
  }

  public NostroTransactionRecordEntity setNostroDrCr(String nostroDrCr) {
    this.nostroDrCr = nostroDrCr;
    return this;
  }

  public String getNostroRefAccountNo() {
    return nostroRefAccountNo;
  }

  public NostroTransactionRecordEntity setNostroRefAccountNo(String nostroRefAccountNo) {
    this.nostroRefAccountNo = nostroRefAccountNo;
    return this;
  }

  public String getNostroTxnParticulars() {
    return nostroTxnParticulars;
  }

  public NostroTransactionRecordEntity setNostroTxnParticulars(String nostroTxnParticulars) {
    this.nostroTxnParticulars = nostroTxnParticulars;
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

  public Long getNostroTxnInitBrid() {
    return nostroTxnInitBrid;
  }

  public NostroTransactionRecordEntity setNostroTxnInitBrid(Long nostroTxnInitBrid) {
    this.nostroTxnInitBrid = nostroTxnInitBrid;
    return this;
  }

  public String getNostroInitModule() {
    return nostroInitModule;
  }

  public NostroTransactionRecordEntity setNostroInitModule(String nostroInitModule) {
    this.nostroInitModule = nostroInitModule;
    return this;
  }

  public String getNostroTxnIsValidYn() {
    return nostroTxnIsValidYn;
  }

  public NostroTransactionRecordEntity setNostroTxnIsValidYn(String nostroTxnIsValidYn) {
    this.nostroTxnIsValidYn = nostroTxnIsValidYn;
    return this;
  }

  public Long getNostroTxnCode() {
    return nostroTxnCode;
  }

  public NostroTransactionRecordEntity setNostroTxnCode(Long nostroTxnCode) {
    this.nostroTxnCode = nostroTxnCode;
    return this;
  }

  public LocalDate getNostroTxnDate() {
    return nostroTxnDate;
  }

  public NostroTransactionRecordEntity setNostroTxnDate(LocalDate nostroTxnDate) {
    this.nostroTxnDate = nostroTxnDate;
    return this;
  }

  public LocalDate getNostroValueDate() {
    return nostroValueDate;
  }

  public NostroTransactionRecordEntity setNostroValueDate(LocalDate nostroValueDate) {
    this.nostroValueDate = nostroValueDate;
    return this;
  }

  public String getNostroCreatedTerminal() {
    return nostroCreatedTerminal;
  }

  public NostroTransactionRecordEntity setNostroCreatedTerminal(String nostroCreatedTerminal) {
    this.nostroCreatedTerminal = nostroCreatedTerminal;
    return this;
  }

  public String getNostroModifiedTerminal() {
    return nostroModifiedTerminal;
  }

  public NostroTransactionRecordEntity setNostroModifiedTerminal(String nostroModifiedTerminal) {
    this.nostroModifiedTerminal = nostroModifiedTerminal;
    return this;
  }

  public String getNostroTxnReferenceNo() {
    return nostroTxnReferenceNo;
  }

  public NostroTransactionRecordEntity setNostroTxnReferenceNo(String nostroTxnReferenceNo) {
    this.nostroTxnReferenceNo = nostroTxnReferenceNo;
    return this;
  }

  public Long getNostroOwnerBrid() {
    return nostroOwnerBrid;
  }

  public NostroTransactionRecordEntity setNostroOwnerBrid(Long nostroOwnerBrid) {
    this.nostroOwnerBrid = nostroOwnerBrid;
    return this;
  }

  public Long getNostroTxnPostBalance() {
    return nostroTxnPostBalance;
  }

  public NostroTransactionRecordEntity setNostroTxnPostBalance(Long nostroTxnPostBalance) {
    this.nostroTxnPostBalance = nostroTxnPostBalance;
    return this;
  }

  public BigDecimal getNostroCreditAmtCcy() {
    return nostroCreditAmtCcy;
  }

  public NostroTransactionRecordEntity setNostroCreditAmtCcy(BigDecimal nostroCreditAmtCcy) {
    this.nostroCreditAmtCcy = nostroCreditAmtCcy;
    return this;
  }

  public Long getNostroGlobalTxnNo() {
    return nostroGlobalTxnNo;
  }

  public NostroTransactionRecordEntity setNostroGlobalTxnNo(Long nostroGlobalTxnNo) {
    this.nostroGlobalTxnNo = nostroGlobalTxnNo;
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
