package com.mislbd.ababil.foreignremittance.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RemittanceTransactionUpdateDto {
  private Long remittanceId;
  private String arvNumber;
  private String salesContractNumber;
}
