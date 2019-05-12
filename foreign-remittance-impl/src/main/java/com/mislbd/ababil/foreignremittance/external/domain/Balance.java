package com.mislbd.ababil.foreignremittance.external.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Balance implements Serializable {

  private BigDecimal currentBalance;
  private BigDecimal blockAmount;
  private BigDecimal minimumBalance;
  private BigDecimal availableBalance;

  public BigDecimal getCurrentBalance() {
    return currentBalance;
  }

  public Balance setCurrentBalance(BigDecimal currentBalance) {
    this.currentBalance = currentBalance;
    return this;
  }

  public BigDecimal getBlockAmount() {
    return blockAmount;
  }

  public Balance setBlockAmount(BigDecimal blockAmount) {
    this.blockAmount = blockAmount;
    return this;
  }

  public BigDecimal getMinimumBalance() {
    return minimumBalance;
  }

  public Balance setMinimumBalance(BigDecimal minimumBalance) {
    this.minimumBalance = minimumBalance;
    return this;
  }

  public BigDecimal getAvailableBalance() {
    return availableBalance;
  }

  public Balance setAvailableBalance(BigDecimal availableBalance) {
    this.availableBalance = availableBalance;
    return this;
  }
}
