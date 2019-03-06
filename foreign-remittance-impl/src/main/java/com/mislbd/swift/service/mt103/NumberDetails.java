package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** Created by user on 1/3/2016. */
@Entity
public class NumberDetails implements Model {

  @Id @GeneratedValue private long id;

  @Column(name = "field50_number")
  private CodeListField50F number;

  @Column(name = "field59_number")
  private CodeListField59F number59;

  private String details;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public CodeListField50F getNumber() {
    return number;
  }

  public void setNumber(CodeListField50F number) {
    this.number = number;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public CodeListField59F getNumber59() {
    return number59;
  }

  public void setNumber59(CodeListField59F number59) {
    this.number59 = number59;
  }
}
