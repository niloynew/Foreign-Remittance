package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@Accessors(chain = true)
public class RemittanceCharge {

  private long id;
  @NotEmpty private String chargeName;
  private String currencyCode;
  private ChargeAccountType chargeAccountType;
  private String chargeAccountCode;
  private String chargeAccountCurrency;
  private ChargeAccountType vatAccountType;
  private String vatAccountCode;
  private String vatAccountCurrency;
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
}
