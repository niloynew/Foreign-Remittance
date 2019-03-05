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
    private Long id;

    @Column(name = "Number")
    private Long number;

    @Column(name = "Bank")
    private Long bank;

    @Column(name = "Branch")
    private Long branch;

    @Column(name = "BalanceCcy")
    private BigDecimal balanceCcy;

    @Column(name = "BalanceLcy")
    private BigDecimal balanceLcy;

    @ManyToOne
    @JoinColumn(name = "product")
    private IDProductEntity product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getBank() {
        return bank;
    }

    public void setBank(Long bank) {
        this.bank = bank;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
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
}
