package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "CodeCountryIdentifierDetails")
public class CodeCountrycodeIdentifierWithDetails implements Model {

  private static final long serialVersionUID = 1L;

  @Id @GeneratedValue private long id;

  private OrderingCustomerFCode code;
  private String countryCode;
  private String identifier;
  // private OrderingCustomerFNumber number;
  // @OneToMany(cascade = CascadeType.PERSIST)
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Fetch(FetchMode.SUBSELECT)
  @JoinColumn(name = "codecountryidentifier_id")
  private List<NumberDetails> details;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public OrderingCustomerFCode getCode() {
    return code;
  }

  public void setCode(OrderingCustomerFCode code) {
    this.code = code;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }
  /*public OrderingCustomerFNumber getSwiftNumber() {
  	return number;
  }
  public void setSwiftNumber(OrderingCustomerFNumber number) {
  	this.number = number;
  }*/
  public List<NumberDetails> getDetails() {
    return details;
  }

  public void setDetails(List<NumberDetails> details) {
    this.details = details;
  }

  /* public String toSwiftString() {
    // return code + "/" + countryCode + "/" + identifier + Constants.SWIFT_NEWLINE +
    // number.getNumVal() + "/" + details;
    return code + "/" + countryCode + "/" + identifier + Constants.SWIFT_NEWLINE + details;
  }*/
}
