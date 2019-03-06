package com.mislbd.swift.service.mt103;

import com.mislbd.asset.commons.data.domain.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TimeIndication implements Model {

  // private TimeIndicationCode code;
  @Id @GeneratedValue private long id;

  private String code;
  private String timeIndication;
  // private TimeIndicationSign sign;
  private String sign;
  private String timeOffset;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getTimeIndication() {
    return timeIndication;
  }

  public void setTimeIndication(String timeIndication) {
    this.timeIndication = timeIndication;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getTimeOffset() {
    return timeOffset;
  }

  public void setTimeOffset(String timeOffset) {
    this.timeOffset = timeOffset;
  }

  public String toSwiftString() {
    // return code+ new
    // SimpleDateFormat("HHMM").format(timeIndication)+sign+new
    // SimpleDateFormat("HHMM").format(timeOffset);
    return "/" + code + "/" + timeIndication + sign + timeOffset;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
