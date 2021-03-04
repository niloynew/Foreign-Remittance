package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class RemittanceCharge {

  private long id;

  @NotEmpty private String chargeName;

  private String currencyCode;

  private ChargeAccountType chargeAccountType;

  private String chargeAccountCode;

  private ChargeAccountType vatAccountType;

  private String vatAccountCode;

  private boolean chargeModifiable;

  private boolean vatModifiable;

  private boolean slabBased;

  private boolean fixedCharge;

  private BigDecimal chargeAmount;

  @Range(min = 0, max = 100, message = "Charge percentage should be within 100")
  private BigDecimal chargePercentage;

  private boolean fixedVat;

  private BigDecimal vatAmount;

  @Range(min = 0, max = 100, message = "VAT percentage should be within 100")
  private BigDecimal vatPercentage;

  private BigDecimal minimumChargeAmount;

  private BigDecimal maximumChargeAmount;

  private List<RemittanceChargeSlab> remittanceChargeSlabs;

  private boolean active;

  public long getId() {
    return id;
  }

  public RemittanceCharge setId(long id) {
    this.id = id;
    return this;
  }

  public String getChargeName() {
    return chargeName;
  }

  public RemittanceCharge setChargeName(String chargeName) {
    this.chargeName = chargeName;
    return this;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public RemittanceCharge setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public ChargeAccountType getChargeAccountType() {
    return chargeAccountType;
  }

  public RemittanceCharge setChargeAccountType(ChargeAccountType chargeAccountType) {
    this.chargeAccountType = chargeAccountType;
    return this;
  }

  public String getChargeAccountCode() {
    return chargeAccountCode;
  }

  public RemittanceCharge setChargeAccountCode(String chargeAccountCode) {
    this.chargeAccountCode = chargeAccountCode;
    return this;
  }

  public ChargeAccountType getVatAccountType() {
    return vatAccountType;
  }

  public RemittanceCharge setVatAccountType(ChargeAccountType vatAccountType) {
    this.vatAccountType = vatAccountType;
    return this;
  }

  public String getVatAccountCode() {
    return vatAccountCode;
  }

  public RemittanceCharge setVatAccountCode(String vatAccountCode) {
    this.vatAccountCode = vatAccountCode;
    return this;
  }

  public boolean isSlabBased() {
    return slabBased;
  }

  public RemittanceCharge setSlabBased(boolean slabBased) {
    this.slabBased = slabBased;
    return this;
  }

  public boolean isFixedCharge() {
    return fixedCharge;
  }

  public RemittanceCharge setFixedCharge(boolean fixedCharge) {
    this.fixedCharge = fixedCharge;
    return this;
  }

  public boolean isChargeModifiable() {
    return chargeModifiable;
  }

  public RemittanceCharge setChargeModifiable(boolean chargeModifiable) {
    this.chargeModifiable = chargeModifiable;
    return this;
  }

  public boolean isVatModifiable() {
    return vatModifiable;
  }

  public RemittanceCharge setVatModifiable(boolean vatModifiable) {
    this.vatModifiable = vatModifiable;
    return this;
  }

  public BigDecimal getChargeAmount() {
    return chargeAmount;
  }

  public RemittanceCharge setChargeAmount(BigDecimal chargeAmount) {
    this.chargeAmount = chargeAmount;
    return this;
  }

  public BigDecimal getChargePercentage() {
    return chargePercentage;
  }

  public RemittanceCharge setChargePercentage(BigDecimal chargePercentage) {
    this.chargePercentage = chargePercentage;
    return this;
  }

  public boolean isFixedVat() {
    return fixedVat;
  }

  public RemittanceCharge setFixedVat(boolean fixedVat) {
    this.fixedVat = fixedVat;
    return this;
  }

  public BigDecimal getVatAmount() {
    return vatAmount;
  }

  public RemittanceCharge setVatAmount(BigDecimal vatAmount) {
    this.vatAmount = vatAmount;
    return this;
  }

  public BigDecimal getVatPercentage() {
    return vatPercentage;
  }

  public RemittanceCharge setVatPercentage(BigDecimal vatPercentage) {
    this.vatPercentage = vatPercentage;
    return this;
  }

  public BigDecimal getMinimumChargeAmount() {
    return minimumChargeAmount;
  }

  public RemittanceCharge setMinimumChargeAmount(BigDecimal minimumChargeAmount) {
    this.minimumChargeAmount = minimumChargeAmount;
    return this;
  }

  public BigDecimal getMaximumChargeAmount() {
    return maximumChargeAmount;
  }

  public RemittanceCharge setMaximumChargeAmount(BigDecimal maximumChargeAmount) {
    this.maximumChargeAmount = maximumChargeAmount;
    return this;
  }

  public List<RemittanceChargeSlab> getRemittanceChargeSlabs() {
    return remittanceChargeSlabs;
  }

  public RemittanceCharge setRemittanceChargeSlabs(
      List<RemittanceChargeSlab> remittanceChargeSlabs) {
    this.remittanceChargeSlabs = remittanceChargeSlabs;
    return this;
  }

  public boolean isActive() {
    return active;
  }

  public RemittanceCharge setActive(boolean active) {
    this.active = active;
    return this;
  }
}
