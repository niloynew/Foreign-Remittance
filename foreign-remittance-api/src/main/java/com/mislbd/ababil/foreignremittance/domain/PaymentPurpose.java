package com.mislbd.ababil.foreignremittance.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PaymentPurpose {

  private long id;
  private String level;
  private String code;
  private String description;
}
