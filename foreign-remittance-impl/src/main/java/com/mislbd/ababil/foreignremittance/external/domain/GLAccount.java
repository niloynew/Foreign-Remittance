package com.mislbd.ababil.foreignremittance.external.domain;

import java.io.Serializable;
import java.util.List;

public class GLAccount implements Serializable {
  private long id;
  private String code;
  private String name;
  private boolean parent;
  //    private GeneralLedgerSubType subType;
  private boolean ibtaRequired;
  private String status;
  //    private CurrencyRestriction currencyRestriction;
  private List<String> currencies;
  private Long parentGeneralLedgerAccountId;
  //    private BranchRestriction branchRestriction;
  private List<Integer> branches;

  public long getId() {
    return id;
  }

  public GLAccount setId(long id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public GLAccount setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public GLAccount setName(String name) {
    this.name = name;
    return this;
  }

  public boolean isParent() {
    return parent;
  }

  public GLAccount setParent(boolean parent) {
    this.parent = parent;
    return this;
  }

  public boolean isIbtaRequired() {
    return ibtaRequired;
  }

  public GLAccount setIbtaRequired(boolean ibtaRequired) {
    this.ibtaRequired = ibtaRequired;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public GLAccount setStatus(String status) {
    this.status = status;
    return this;
  }

  public List<String> getCurrencies() {
    return currencies;
  }

  public GLAccount setCurrencies(List<String> currencies) {
    this.currencies = currencies;
    return this;
  }

  public Long getParentGeneralLedgerAccountId() {
    return parentGeneralLedgerAccountId;
  }

  public GLAccount setParentGeneralLedgerAccountId(Long parentGeneralLedgerAccountId) {
    this.parentGeneralLedgerAccountId = parentGeneralLedgerAccountId;
    return this;
  }

  public List<Integer> getBranches() {
    return branches;
  }

  public GLAccount setBranches(List<Integer> branches) {
    this.branches = branches;
    return this;
  }
}
