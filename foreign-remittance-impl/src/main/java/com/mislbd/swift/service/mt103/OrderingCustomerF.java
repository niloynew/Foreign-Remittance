package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OrderingCustomerF implements Model {
  @Id @GeneratedValue private long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(name = "selectedOrderingCustFOption")
  private OrderingCustomerFOption selectedOrderingCustomerFOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "orderingCustomerF1_ID")
  private AccountAndDetailsWithNumber orderingCustomerF_1;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "orderingCustomerF2_ID")
  private CodeCountrycodeIdentifierWithDetails orderingCustomerF_2;

  public OrderingCustomerFOption getSelectedOrderingCustomerFOption() {
    return selectedOrderingCustomerFOption;
  }

  public void setSelectedOrderingCustomerFOption(
      OrderingCustomerFOption selectedOrderingCustomerFOption) {
    this.selectedOrderingCustomerFOption = selectedOrderingCustomerFOption;
  }

  public AccountAndDetailsWithNumber getOrderingCustomerF_1() {
    return orderingCustomerF_1;
  }

  public void setOrderingCustomerF_1(AccountAndDetailsWithNumber orderingCustomerF_1) {
    this.orderingCustomerF_1 = orderingCustomerF_1;
  }

  public CodeCountrycodeIdentifierWithDetails getOrderingCustomerF_2() {
    return orderingCustomerF_2;
  }

  public void setOrderingCustomerF_2(CodeCountrycodeIdentifierWithDetails orderingCustomerF_2) {
    this.orderingCustomerF_2 = orderingCustomerF_2;
  }

  /*public String toSwiftString() {
    switch (this.getSelectedOrderingCustomerFOption()) {
      case Option_1:
        return this.getOrderingCustomerF_1().toSwiftString();
      case Option_2:
        return this.getOrderingCustomerF_2().toSwiftString();
      default:
        return "";
    }
  }*/
}
