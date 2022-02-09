package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.swift.broker.model.raw.SelectOptions;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BankInformation {

  private long id;
  private SelectOptions option;
  private BankType bankType;
  private String partyIdentifier;
  private String identifierCode;
  private String location;
  private String nameAndAddress;
}
