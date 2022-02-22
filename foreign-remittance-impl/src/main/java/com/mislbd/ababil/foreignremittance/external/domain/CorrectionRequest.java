package com.mislbd.ababil.foreignremittance.external.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CorrectionRequest {
  private String referenceNumber;
  private String requestId;
  private String voucherNumber;
}
