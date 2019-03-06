package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CurrencyAmountInfo implements Model {

  @Id @GeneratedValue private long id;

  private String currency;

  @Column(precision = 15, scale = 4, columnDefinition = "DECIMAL(15,4)")
  private BigDecimal amount;

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  /*public String toSwiftString()
  {
  	return currency + Format.formatAmount(amount);
  }*/
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
