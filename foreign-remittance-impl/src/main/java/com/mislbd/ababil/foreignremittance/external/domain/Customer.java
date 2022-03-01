package com.mislbd.ababil.foreignremittance.external.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Customer {
  private Long id;
  private String name;
  private Address address;
}
