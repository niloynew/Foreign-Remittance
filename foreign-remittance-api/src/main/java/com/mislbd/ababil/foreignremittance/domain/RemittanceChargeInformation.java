package com.mislbd.ababil.foreignremittance.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class RemittanceChargeInformation {

  private long chargeId;
  private String chargeName;
  private Boolean canModifyCharge;
  private boolean chargeModifiable;
  private boolean vatModifiable;
  private ChargeAccountType chargeAccountType;
  private String chargeAccountCode;
  private BigDecimal chargeAmount;
  private BigDecimal chargeAmountAfterWaived;
  private BigDecimal vatAmountAfterWaived;
  private ChargeAccountType vatAccountType;
  private String vatAccountCode;
  private BigDecimal vatAmount;
  private String currency;
  private BigDecimal exchangeRate;

}
