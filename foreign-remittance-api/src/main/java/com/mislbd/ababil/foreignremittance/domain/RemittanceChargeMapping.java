package com.mislbd.ababil.foreignremittance.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class RemittanceChargeMapping {

  private long id;
  @Enumerated(EnumType.STRING)
  private RemittanceType remittanceType;
  @NotNull private Long chargeId;
  private String chargeName;
  private String typeName;
  @NotNull private Long typeId;
  private Boolean chargeModifiable;
  private Boolean vatModifiable;

}
