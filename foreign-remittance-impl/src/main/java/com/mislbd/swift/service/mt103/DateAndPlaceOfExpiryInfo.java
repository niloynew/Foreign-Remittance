package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DateAndPlaceOfExpiryInfo implements Model {

  @Id @GeneratedValue private long id;

  @Column(name = "sdate")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  private String place;

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String toSwiftString() {
    return (new SimpleDateFormat("YYMMdd")).format(date) + place;
  }
}
