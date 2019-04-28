package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import javax.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Range;

public class RemittanceChargeSlab {

  private long id;

  @PositiveOrZero private BigDecimal fromAmount;

  private BigDecimal toAmount;

  private boolean fixedCharge;

  @PositiveOrZero private BigDecimal chargeAmount;

  @Range(min = 0, max = 100, message = "Charge percentage should be within 100")
  private BigDecimal percentage;

  @PositiveOrZero private BigDecimal minimumChargeAmount;

  private BigDecimal maximumChargeAmount;

  private long financingChargeId;

  public long getId() {
    return id;
  }

  public RemittanceChargeSlab setId(long id) {
    this.id = id;
    return this;
  }

  public BigDecimal getFromAmount() {
    return fromAmount;
  }

  public RemittanceChargeSlab setFromAmount(BigDecimal fromAmount) {
    this.fromAmount = fromAmount;
    return this;
  }

  public BigDecimal getToAmount() {
    return toAmount;
  }

  public RemittanceChargeSlab setToAmount(BigDecimal toAmount) {
    this.toAmount = toAmount;
    return this;
  }

  public boolean isFixedCharge() {
    return fixedCharge;
  }

  public RemittanceChargeSlab setFixedCharge(boolean fixedCharge) {
    this.fixedCharge = fixedCharge;
    return this;
  }

  public BigDecimal getChargeAmount() {
    return chargeAmount;
  }

  public RemittanceChargeSlab setChargeAmount(BigDecimal chargeAmount) {
    this.chargeAmount = chargeAmount;
    return this;
  }

  public BigDecimal getPercentage() {
    return percentage;
  }

  public RemittanceChargeSlab setPercentage(BigDecimal percentage) {
    this.percentage = percentage;
    return this;
  }

  public BigDecimal getMinimumChargeAmount() {
    return minimumChargeAmount;
  }

  public RemittanceChargeSlab setMinimumChargeAmount(BigDecimal minimumChargeAmount) {
    this.minimumChargeAmount = minimumChargeAmount;
    return this;
  }

  public BigDecimal getMaximumChargeAmount() {
    return maximumChargeAmount;
  }

  public RemittanceChargeSlab setMaximumChargeAmount(BigDecimal maximumChargeAmount) {
    this.maximumChargeAmount = maximumChargeAmount;
    return this;
  }

  public long getFinancingChargeId() {
    return financingChargeId;
  }

  public RemittanceChargeSlab setFinancingChargeId(long financingChargeId) {
    this.financingChargeId = financingChargeId;
    return this;
  }
}
