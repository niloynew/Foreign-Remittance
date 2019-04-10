package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.NOSTRO_ACCOUNT_TABLE_NAME)
public class NostroAccountEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "NOSTRO_ACCOUNT_ID_GEN")
  @SequenceGenerator(
      name = "NOSTRO_ACCOUNT_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.NOSTRO_ACCOUNT_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "NostroAccountNumber")
  private String nostroAccountNumber;

  @Column(name = "ShadowAccountNumber")
  private String shadowAccountNumber;

  @Column(name = "AccountName")
  private String name;

  @Column(name = "Currency")
  private String currencyCode;

  @Column(name = "AccountOpenDate")
  private LocalDate accOpenDate;

  @Column(name = "BalanceCcy")
  private BigDecimal balanceCcy;

  @Column(name = "BalanceLcy")
  private BigDecimal balanceLcy;

  @Column(name = "Bank")
  private Long bankId;

  @Column(name = "Branch")
  private Long branchId;

  @Column(name = "IsActive")
  private boolean isActive;

  @ManyToOne
  @JoinColumn(name = "product")
  private IDProductEntity product;

  public long getId() {
    return id;
  }

  public NostroAccountEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getNostroAccountNumber() {
    return nostroAccountNumber;
  }

  public NostroAccountEntity setNostroAccountNumber(String nostroAccountNumber) {
    this.nostroAccountNumber = nostroAccountNumber;
    return this;
  }

  public String getShadowAccountNumber() {
    return shadowAccountNumber;
  }

  public NostroAccountEntity setShadowAccountNumber(String shadowAccountNumber) {
    this.shadowAccountNumber = shadowAccountNumber;
    return this;
  }

  public String getName() {
    return name;
  }

  public NostroAccountEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public NostroAccountEntity setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public LocalDate getAccOpenDate() {
    return accOpenDate;
  }

  public NostroAccountEntity setAccOpenDate(LocalDate accOpenDate) {
    this.accOpenDate = accOpenDate;
    return this;
  }

  public BigDecimal getBalanceCcy() {
    return balanceCcy;
  }

  public NostroAccountEntity setBalanceCcy(BigDecimal balanceCcy) {
    this.balanceCcy = balanceCcy;
    return this;
  }

  public BigDecimal getBalanceLcy() {
    return balanceLcy;
  }

  public NostroAccountEntity setBalanceLcy(BigDecimal balanceLcy) {
    this.balanceLcy = balanceLcy;
    return this;
  }

  public Long getBankId() {
    return bankId;
  }

  public NostroAccountEntity setBankId(Long bankId) {
    this.bankId = bankId;
    return this;
  }

  public Long getBranchId() {
    return branchId;
  }

  public NostroAccountEntity setBranchId(Long branchId) {
    this.branchId = branchId;
    return this;
  }

  public boolean isActive() {
    return isActive;
  }

  public NostroAccountEntity setActive(boolean active) {
    isActive = active;
    return this;
  }

  public IDProductEntity getProduct() {
    return product;
  }

  public NostroAccountEntity setProduct(IDProductEntity product) {
    this.product = product;
    return this;
  }
}
