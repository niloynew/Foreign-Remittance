package com.mislbd.ababil.foreignremittance.repository.schema;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.REMITTANCE_CHARGE_SLAB_INFO_TABLE_NAME)
public class RemittanceChargeSlabEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "REMITTANCE_CHARGE_SLAB_INFO_ID_GEN")
  @SequenceGenerator(
      name = "REMITTANCE_CHARGE_SLAB_INFO_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.REMITTANCE_CHARGE_SLAB_INFO_SEQUENCE_NAME)
  private long id;

  @Column(name = "AMOUNT_FROM")
  private BigDecimal fromAmount;

  @Column(name = "AMOUNT_TO")
  private BigDecimal toAmount;

  @Column(name = "IS_FIXED_CHARGE")
  private boolean fixedCharge;

  @Column(name = "CHARGE_AMOUNT")
  private BigDecimal chargeAmount;

  @Column(name = "PERCENTAGE")
  private BigDecimal percentage;

  @Column(name = "MIN_CHARGE_AMOUNT")
  private BigDecimal minimumChargeAmount;

  @Column(name = "MAX_CHARGE_AMOUNT")
  private BigDecimal maximumChargeAmount;

  @ManyToOne
  @JoinColumn(name = "CHARGE_ID")
  private RemittanceChargeEntity remittanceCharge;

  public long getId() {
    return id;
  }

  public RemittanceChargeSlabEntity setId(long id) {
    this.id = id;
    return this;
  }

  public BigDecimal getFromAmount() {
    return fromAmount;
  }

  public RemittanceChargeSlabEntity setFromAmount(BigDecimal fromAmount) {
    this.fromAmount = fromAmount;
    return this;
  }

  public BigDecimal getToAmount() {
    return toAmount;
  }

  public RemittanceChargeSlabEntity setToAmount(BigDecimal toAmount) {
    this.toAmount = toAmount;
    return this;
  }

  public boolean isFixedCharge() {
    return fixedCharge;
  }

  public RemittanceChargeSlabEntity setFixedCharge(boolean fixedCharge) {
    this.fixedCharge = fixedCharge;
    return this;
  }

  public BigDecimal getChargeAmount() {
    return chargeAmount;
  }

  public RemittanceChargeSlabEntity setChargeAmount(BigDecimal chargeAmount) {
    this.chargeAmount = chargeAmount;
    return this;
  }

  public BigDecimal getPercentage() {
    return percentage;
  }

  public RemittanceChargeSlabEntity setPercentage(BigDecimal percentage) {
    this.percentage = percentage;
    return this;
  }

  public BigDecimal getMinimumChargeAmount() {
    return minimumChargeAmount;
  }

  public RemittanceChargeSlabEntity setMinimumChargeAmount(BigDecimal minimumChargeAmount) {
    this.minimumChargeAmount = minimumChargeAmount;
    return this;
  }

  public BigDecimal getMaximumChargeAmount() {
    return maximumChargeAmount;
  }

  public RemittanceChargeSlabEntity setMaximumChargeAmount(BigDecimal maximumChargeAmount) {
    this.maximumChargeAmount = maximumChargeAmount;
    return this;
  }

  public RemittanceChargeEntity getRemittanceCharge() {
    return remittanceCharge;
  }

  public RemittanceChargeSlabEntity setRemittanceCharge(RemittanceChargeEntity remittanceCharge) {
    this.remittanceCharge = remittanceCharge;
    return this;
  }
}
