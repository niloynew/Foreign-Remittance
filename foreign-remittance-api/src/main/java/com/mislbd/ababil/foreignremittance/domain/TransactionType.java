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
public class TransactionType {

  private long id;
  @NotNull private String name;
  private String description;
  @NotNull
  @Enumerated(EnumType.STRING)
  private RemittanceType remittanceType;

}
