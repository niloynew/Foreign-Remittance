package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@Accessors(chain = true)
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

}
