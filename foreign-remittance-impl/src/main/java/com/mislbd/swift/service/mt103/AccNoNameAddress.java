package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/** Created by user on 1/2/2016. */
@Entity
public class AccNoNameAddress implements Model {
  @Id @GeneratedValue private long id;
  private String account;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Fetch(FetchMode.SUBSELECT)
  @JoinColumn
  private List<NoNameAddress> noNameAddressList;

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

  public List<NoNameAddress> getNoNameAddressList() {
    return noNameAddressList;
  }

  public void setNoNameAddressList(List<NoNameAddress> noNameAddressList) {
    this.noNameAddressList = noNameAddressList;
  }
}
