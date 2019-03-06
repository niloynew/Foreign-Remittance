package com.mislbd.swift.service;

import com.mislbd.asset.commons.data.domain.Model;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BicList implements Model {
  @Id @GeneratedValue private long id;

  private String city;
  private String country;
  private String department;
  private String firstName;
  private String lastName;

  @Column(unique = true)
  private String institution; // BIC

  private String type;
  private String status;
  private boolean authToReceive;
  private boolean authToSend;
  private String institutionName; // Institution name
  private Date creationDate;
  private String createdBy;
  private String brInfo;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getInstitution() {
    return institution;
  }

  public void setInstitution(String institution) {
    this.institution = institution;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getInstitutionName() {
    return institutionName;
  }

  public void setInstitutionName(String institutionName) {
    this.institutionName = institutionName;
  }

  public boolean isAuthToReceive() {
    return authToReceive;
  }

  public void setAuthToReceive(boolean authToReceive) {
    this.authToReceive = authToReceive;
  }

  public boolean isAuthToSend() {
    return authToSend;
  }

  public void setAuthToSend(boolean authToSend) {
    this.authToSend = authToSend;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getBrInfo() {
    return brInfo;
  }

  public void setBrInfo(String brInfo) {
    this.brInfo = brInfo;
  }
}
