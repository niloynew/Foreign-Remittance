package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;

public class RemittanceChargeInformation {

  private long chargeId;
  private String chargeName;
  private Boolean canModifyCharge;
  private ChargeAccountType chargeAccountType;
  private String chargeAccountCode;
  private BigDecimal chargeAmount;
  private BigDecimal chargeAmountAfterWaived;
  private ChargeAccountType vatAccountType;
  private String vatAccountCode;
  private BigDecimal vatAmount;
  private String currency;
  private BigDecimal exchangeRate;

  public long getChargeId() {
    return chargeId;
  }

  public RemittanceChargeInformation setChargeId(long chargeId) {
    this.chargeId = chargeId;
    return this;
  }

  public String getChargeName() {
    return chargeName;
  }

  public RemittanceChargeInformation setChargeName(String chargeName) {
    this.chargeName = chargeName;
    return this;
  }

  public Boolean getCanModifyCharge() {
    return canModifyCharge;
  }

  public RemittanceChargeInformation setCanModifyCharge(Boolean canModifyCharge) {
    this.canModifyCharge = canModifyCharge;
    return this;
  }

  public ChargeAccountType getChargeAccountType() {
    return chargeAccountType;
  }

  public RemittanceChargeInformation setChargeAccountType(ChargeAccountType chargeAccountType) {
    this.chargeAccountType = chargeAccountType;
    return this;
  }

  public String getChargeAccountCode() {
    return chargeAccountCode;
  }

  public RemittanceChargeInformation setChargeAccountCode(String chargeAccountCode) {
    this.chargeAccountCode = chargeAccountCode;
    return this;
  }

  public BigDecimal getChargeAmount() {
    return chargeAmount;
  }

  public RemittanceChargeInformation setChargeAmount(BigDecimal chargeAmount) {
    this.chargeAmount = chargeAmount;
    return this;
  }

  public BigDecimal getChargeAmountAfterWaived() {
    return chargeAmountAfterWaived;
  }

  public RemittanceChargeInformation setChargeAmountAfterWaived(
      BigDecimal chargeAmountAfterWaived) {
    this.chargeAmountAfterWaived = chargeAmountAfterWaived;
    return this;
  }

  public ChargeAccountType getVatAccountType() {
    return vatAccountType;
  }

  public RemittanceChargeInformation setVatAccountType(ChargeAccountType vatAccountType) {
    this.vatAccountType = vatAccountType;
    return this;
  }

  public String getVatAccountCode() {
    return vatAccountCode;
  }

  public RemittanceChargeInformation setVatAccountCode(String vatAccountCode) {
    this.vatAccountCode = vatAccountCode;
    return this;
  }

  public BigDecimal getVatAmount() {
    return vatAmount;
  }

  public RemittanceChargeInformation setVatAmount(BigDecimal vatAmount) {
    this.vatAmount = vatAmount;
    return this;
  }

  public String getCurrency() {
    return currency;
  }

  public RemittanceChargeInformation setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public BigDecimal getExchangeRate() {
    return exchangeRate;
  }

  public RemittanceChargeInformation setExchangeRate(BigDecimal exchangeRate) {
    this.exchangeRate = exchangeRate;
    return this;
  }
}
