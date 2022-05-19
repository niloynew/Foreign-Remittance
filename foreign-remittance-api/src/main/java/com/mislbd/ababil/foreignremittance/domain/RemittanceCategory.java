package com.mislbd.ababil.foreignremittance.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RemittanceCategory {
  private long id;
  private String name;
  private String description;
  private RemittanceType remittanceType;
}
