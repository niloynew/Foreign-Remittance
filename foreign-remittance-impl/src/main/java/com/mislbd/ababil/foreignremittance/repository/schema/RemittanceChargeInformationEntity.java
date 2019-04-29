package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.foreignremittance.domain.ChargeAccountType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = SchemaConstant.ID_REMITTANCE_CHARGE_INFO_TABLE_NAME)
public class RemittanceChargeInformationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_REMITTANCE_CHARGE_INFO_ID_GEN")
    @SequenceGenerator(
            name = "ID_REMITTANCE_CHARGE_INFO_ID_GEN",
            allocationSize = 1,
            sequenceName = SchemaConstant.ID_REMITTANCE_CHARGE_INFO_SEQUENCE_NAME)
    @Column(name = "ID")
    private long id;

    @Column(name = "CHARGE_ID")
    private long chargeId;

    @Column(name = "CHARGE_NAME")
    private String chargeName;

    @Column(name = "CHARGE_ACCOUNT_TYPE")
    private ChargeAccountType chargeAccountType;

    @Column(name = "CHARGE_ACCOUNT_CODE")
    private String chargeAccountCode;

    @Column(name = "CHARGE_AMOUNT")
    private BigDecimal chargeAmount;

    @Column(name = "VAT_ACCOUNT_TYPE")
    private ChargeAccountType vatAccountType;

    @Column(name = "VAT_ACCOUNT_CODE")
    private String vatAccountCode;

    @Column(name = "VAT_AMOUNT")
    private BigDecimal vatAmount;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "EXCHANGE_RATE")
    private BigDecimal exchangeRate;

    @ManyToOne
    @JoinColumn(name = "Remittance_Tnx_Id")
    private RemittanceTransactionEntity remittanceTransaction;

    public long getId() {
        return id;
    }

    public RemittanceChargeInformationEntity setId(long id) {
        this.id = id;
        return this;
    }

    public long getChargeId() {
        return chargeId;
    }

    public RemittanceChargeInformationEntity setChargeId(long chargeId) {
        this.chargeId = chargeId;
        return this;
    }

    public String getChargeName() {
        return chargeName;
    }

    public RemittanceChargeInformationEntity setChargeName(String chargeName) {
        this.chargeName = chargeName;
        return this;
    }

    public ChargeAccountType getChargeAccountType() {
        return chargeAccountType;
    }

    public RemittanceChargeInformationEntity setChargeAccountType(ChargeAccountType chargeAccountType) {
        this.chargeAccountType = chargeAccountType;
        return this;
    }

    public String getChargeAccountCode() {
        return chargeAccountCode;
    }

    public RemittanceChargeInformationEntity setChargeAccountCode(String chargeAccountCode) {
        this.chargeAccountCode = chargeAccountCode;
        return this;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public RemittanceChargeInformationEntity setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
        return this;
    }

    public ChargeAccountType getVatAccountType() {
        return vatAccountType;
    }

    public RemittanceChargeInformationEntity setVatAccountType(ChargeAccountType vatAccountType) {
        this.vatAccountType = vatAccountType;
        return this;
    }

    public String getVatAccountCode() {
        return vatAccountCode;
    }

    public RemittanceChargeInformationEntity setVatAccountCode(String vatAccountCode) {
        this.vatAccountCode = vatAccountCode;
        return this;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public RemittanceChargeInformationEntity setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public RemittanceChargeInformationEntity setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public RemittanceChargeInformationEntity setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
        return this;
    }

    public RemittanceTransactionEntity getRemittanceTransaction() {
        return remittanceTransaction;
    }

    public RemittanceChargeInformationEntity setRemittanceTransaction(RemittanceTransactionEntity remittanceTransaction) {
        this.remittanceTransaction = remittanceTransaction;
        return this;
    }
}
