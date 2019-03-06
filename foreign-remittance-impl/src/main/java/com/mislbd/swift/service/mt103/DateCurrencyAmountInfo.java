package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DateCurrencyAmountInfo implements Model {
  @Id @GeneratedValue private long id;

  @Column(name = "sdate")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  private String currency;

  @Column(precision = 15, scale = 4, columnDefinition = "DECIMAL(15,4)")
  private BigDecimal amount;

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  /* public String toSwiftString() {
    return new SimpleDateFormat("yyMMdd").format(date) + currency + Format.formatAmount(amount);
  }*/
}
