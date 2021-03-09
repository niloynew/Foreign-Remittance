package com.mislbd.ababil.foreignremittance.repository.schema;

import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_CHARGE_SLAB_INFO_TABLE_NAME)
public class RemittanceChargeSlabEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "REMITTANCE_CHARGE_SLAB_INFO_ID_GEN")
  @SequenceGenerator(
      name = "REMITTANCE_CHARGE_SLAB_INFO_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_CHARGE_SLAB_INFO_SEQUENCE_NAME)
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
}
