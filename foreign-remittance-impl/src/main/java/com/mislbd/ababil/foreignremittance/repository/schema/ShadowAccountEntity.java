package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = SchemaConstant.SHADOW_ACCOUNT_TABLE_NAME)
public class ShadowAccountEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "Number")
    private String number;

    @Column(name = "Bank")
    private Long bankId;

    @Column(name = "Branch")
    private Long branchId;

    @Column(name = "Currency")
    private String currencyCode;

    @Column(name = "BalanceCcy")
    private BigDecimal balanceCcy;

    @Column(name = "BalanceLcy")
    private BigDecimal balanceLcy;

    @ManyToOne
    @JoinColumn(name = "product")
    private IDProductEntity product;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public BigDecimal getBalanceCcy() {
        return balanceCcy;
    }

    public void setBalanceCcy(BigDecimal balanceCcy) {
        this.balanceCcy = balanceCcy;
    }

    public BigDecimal getBalanceLcy() {
        return balanceLcy;
    }

    public void setBalanceLcy(BigDecimal balanceLcy) {
        this.balanceLcy = balanceLcy;
    }

    public IDProductEntity getProduct() {
        return product;
    }

    public void setProduct(IDProductEntity product) {
        this.product = product;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
