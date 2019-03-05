package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.foreignremittance.domain.IDProduct;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.NOSTRO_ACCOUNT_TABLE_NAME)
public class NostroAccountEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Column(name = "AccountNumber")
  private String number;

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

  @ManyToOne
  @JoinColumn(name = "ID")
  private IDProductEntity product;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public LocalDate getAccOpenDate() {
    return accOpenDate;
  }

  public void setAccOpenDate(LocalDate accOpenDate) {
    this.accOpenDate = accOpenDate;
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
