package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name ="AccAndDetailWithNumber")
public class AccountAndDetailsWithNumber implements Model {

  private static final long serialVersionUID = 1L;
  @Id @GeneratedValue private long id;
  private String account;
  // private int number;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Fetch(FetchMode.SUBSELECT)
  @JoinColumn
  private List<NumberDetails> details;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }
  /*public int getSwiftNumber() {
  	return number;
  }
  public void setSwiftNumber(int number) {
  	this.number = number;
  }*/

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public List<NumberDetails> getDetails() {
    return details;
  }

  public void setDetails(List<NumberDetails> details) {
    this.details = details;
  }

  /*public String toSwiftString() {
   */
  /*return "/" + account + Constants.SWIFT_NEWLINE + number + "/" + details;*/
  /*
    return "/" + account + Constants.SWIFT_NEWLINE + details;
  }*/
}
