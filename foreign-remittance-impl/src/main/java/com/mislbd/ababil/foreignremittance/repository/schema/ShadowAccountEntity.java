package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.SHADOW_ACCOUNT_TABLE_NAME)
public class ShadowAccountEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private long id;

  @Column(name = "Number")
  private String number;

  @Column(name = "AccountName")
  private String name;

  @Column(name = "Bank")
  private Long bankId;

  @Column(name = "Branch")
  private Long branchId;

  @Column(name = "accountOpenDate")
  private LocalDate accountOpenDate;

  @Column(name = "Currency")
  private String currencyCode;

  @Column(name = "BalanceCcy")
  private BigDecimal balanceCcy;

  @Column(name = "BalanceLcy")
  private BigDecimal balanceLcy;

  @Column(name = "IsActive")
  private boolean isActive;

  @ManyToOne
  @JoinColumn(name = "product")
  private IDProductEntity product;

  public long getId() {
    return id;
  }

  public ShadowAccountEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getNumber() {
    return number;
  }

  public ShadowAccountEntity setNumber(String number) {
    this.number = number;
    return this;
  }

  public Long getBankId() {
    return bankId;
  }

  public ShadowAccountEntity setBankId(Long bankId) {
    this.bankId = bankId;
    return this;
  }

  public Long getBranchId() {
    return branchId;
  }

  public ShadowAccountEntity setBranchId(Long branchId) {
    this.branchId = branchId;
    return this;
  }

  public LocalDate getAccountOpenDate() {
    return accountOpenDate;
  }

  public ShadowAccountEntity setAccountOpenDate(LocalDate accountOpenDate) {
    this.accountOpenDate = accountOpenDate;
    return this;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public ShadowAccountEntity setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public BigDecimal getBalanceCcy() {
    return balanceCcy;
  }

  public ShadowAccountEntity setBalanceCcy(BigDecimal balanceCcy) {
    this.balanceCcy = balanceCcy;
    return this;
  }

  public BigDecimal getBalanceLcy() {
    return balanceLcy;
  }

  public ShadowAccountEntity setBalanceLcy(BigDecimal balanceLcy) {
    this.balanceLcy = balanceLcy;
    return this;
  }

  public IDProductEntity getProduct() {
    return product;
  }

  public ShadowAccountEntity setProduct(IDProductEntity product) {
    this.product = product;
    return this;
  }

  public String getName() {
    return name;
  }

  public ShadowAccountEntity setName(String name) {
    this.name = name;
    return this;
  }

  public boolean isActive() {
    return isActive;
  }

  public ShadowAccountEntity setActive(boolean active) {
    isActive = active;
    return this;
  }
}
