package com.mislbd.ababil.foreignremittance.domain;


import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;

public class NostroTransactionRecord {
    private long id;

    private Long nostroTxnid;

    private String nostroDrCr;

    private String nostroRefAccno;

    private String nostroTrparticulars;

    private BigDecimal nostroDebitAmtCcy;

    private String nostroCurCode;

    private Long nostroTrnInitBrid;

    private String nostroInitModule;

    private String nostroTrnIsvalidYn;

    private Long nostroTrCode;

    private String nostroSetupUserid;



    private String nostroVerifyUserid;


    private LocalDate nostroTrnDate;

    private LocalDate nostroValueDate;
    private String nostroCreatedterminal;
    private String nostroModifiedterminal;
        private String nostroTrRefNo;
    private Long nostroOwnerBrid;

    private Long nostroTrpostbalance;

    private BigDecimal nostroCreditAmtCcy;
    private Long nostroGlobaltrnNo;

    private String nostroReconciled;

    public Long getNostroTxnid() {
        return nostroTxnid;
    }

    public NostroTransactionRecord setNostroTxnid(Long nostroTxnid) {
        this.nostroTxnid = nostroTxnid;
        return this;
    }

    public String getNostroDrCr() {
        return nostroDrCr;
    }

    public NostroTransactionRecord setNostroDrCr(String nostroDrCr) {
        this.nostroDrCr = nostroDrCr;
        return this;
    }

    public String getNostroRefAccno() {
        return nostroRefAccno;
    }

    public NostroTransactionRecord setNostroRefAccno(String nostroRefAccno) {
        this.nostroRefAccno = nostroRefAccno;
        return this;
    }

    public String getNostroTrparticulars() {
        return nostroTrparticulars;
    }

    public NostroTransactionRecord setNostroTrparticulars(String nostroTrparticulars) {
        this.nostroTrparticulars = nostroTrparticulars;
        return this;
    }

    public BigDecimal getNostroDebitAmtCcy() {
        return nostroDebitAmtCcy;
    }

    public NostroTransactionRecord setNostroDebitAmtCcy(BigDecimal nostroDebitAmtCcy) {
        this.nostroDebitAmtCcy = nostroDebitAmtCcy;
        return this;
    }

    public String getNostroCurCode() {
        return nostroCurCode;
    }

    public NostroTransactionRecord setNostroCurCode(String nostroCurCode) {
        this.nostroCurCode = nostroCurCode;
        return this;
    }

    public Long getNostroTrnInitBrid() {
        return nostroTrnInitBrid;
    }

    public NostroTransactionRecord setNostroTrnInitBrid(Long nostroTrnInitBrid) {
        this.nostroTrnInitBrid = nostroTrnInitBrid;
        return this;
    }

    public String getNostroInitModule() {
        return nostroInitModule;
    }

    public NostroTransactionRecord setNostroInitModule(String nostroInitModule) {
        this.nostroInitModule = nostroInitModule;
        return this;
    }

    public String getNostroTrnIsvalidYn() {
        return nostroTrnIsvalidYn;
    }

    public NostroTransactionRecord setNostroTrnIsvalidYn(String nostroTrnIsvalidYn) {
        this.nostroTrnIsvalidYn = nostroTrnIsvalidYn;
        return this;
    }

    public Long getNostroTrCode() {
        return nostroTrCode;
    }

    public NostroTransactionRecord setNostroTrCode(Long nostroTrCode) {
        this.nostroTrCode = nostroTrCode;
        return this;
    }

    public String getNostroSetupUserid() {
        return nostroSetupUserid;
    }

    public NostroTransactionRecord setNostroSetupUserid(String nostroSetupUserid) {
        this.nostroSetupUserid = nostroSetupUserid;
        return this;
    }

    public String getNostroVerifyUserid() {
        return nostroVerifyUserid;
    }

    public NostroTransactionRecord setNostroVerifyUserid(String nostroVerifyUserid) {
        this.nostroVerifyUserid = nostroVerifyUserid;
        return this;
    }

    public LocalDate getNostroTrnDate() {
        return nostroTrnDate;
    }

    public NostroTransactionRecord setNostroTrnDate(LocalDate nostroTrnDate) {
        this.nostroTrnDate = nostroTrnDate;
        return this;
    }

    public LocalDate getNostroValueDate() {
        return nostroValueDate;
    }

    public NostroTransactionRecord setNostroValueDate(LocalDate nostroValueDate) {
        this.nostroValueDate = nostroValueDate;
        return this;
    }

    public String getNostroCreatedterminal() {
        return nostroCreatedterminal;
    }

    public NostroTransactionRecord setNostroCreatedterminal(String nostroCreatedterminal) {
        this.nostroCreatedterminal = nostroCreatedterminal;
        return this;
    }

    public String getNostroModifiedterminal() {
        return nostroModifiedterminal;
    }

    public NostroTransactionRecord setNostroModifiedterminal(String nostroModifiedterminal) {
        this.nostroModifiedterminal = nostroModifiedterminal;
        return this;
    }

    public String getNostroTrRefNo() {
        return nostroTrRefNo;
    }

    public NostroTransactionRecord setNostroTrRefNo(String nostroTrRefNo) {
        this.nostroTrRefNo = nostroTrRefNo;
        return this;
    }

    public Long getNostroOwnerBrid() {
        return nostroOwnerBrid;
    }

    public NostroTransactionRecord setNostroOwnerBrid(Long nostroOwnerBrid) {
        this.nostroOwnerBrid = nostroOwnerBrid;
        return this;
    }

    public Long getNostroTrpostbalance() {
        return nostroTrpostbalance;
    }

    public NostroTransactionRecord setNostroTrpostbalance(Long nostroTrpostbalance) {
        this.nostroTrpostbalance = nostroTrpostbalance;
        return this;
    }

    public BigDecimal getNostroCreditAmtCcy() {
        return nostroCreditAmtCcy;
    }

    public NostroTransactionRecord setNostroCreditAmtCcy(BigDecimal nostroCreditAmtCcy) {
        this.nostroCreditAmtCcy = nostroCreditAmtCcy;
        return this;
    }

    public Long getNostroGlobaltrnNo() {
        return nostroGlobaltrnNo;
    }

    public NostroTransactionRecord setNostroGlobaltrnNo(Long nostroGlobaltrnNo) {
        this.nostroGlobaltrnNo = nostroGlobaltrnNo;
        return this;
    }

    public String getNostroReconciled() {
        return nostroReconciled;
    }

    public NostroTransactionRecord setNostroReconciled(String nostroReconciled) {
        this.nostroReconciled = nostroReconciled;
        return this;
    }

    public long getId() {
        return id;
    }

    public NostroTransactionRecord setId(long id) {
        this.id = id;
        return this;
    }
}
