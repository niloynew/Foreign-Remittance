package com.mislbd.ababil.foreignremittance.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RemittanceTransactionCorrectionRequest {
  private Long remittanceTransactionId;
  private Long globalTxnNumber;
}
