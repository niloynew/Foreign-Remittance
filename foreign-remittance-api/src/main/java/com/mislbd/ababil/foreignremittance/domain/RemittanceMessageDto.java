package com.mislbd.ababil.foreignremittance.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
public class RemittanceMessageDto {

  private long id;
  private String lcNumber;
  private String relatedReference;
  private LocalDate valueDate;
  private String currency;
  private BigDecimal amount;
  private String instructedCurrency;
  private BigDecimal instructedAmount;
  private String messageType;
  private String orderingCustomerAcc;
  private String orderingCustomerNameAndAddress;
  private String orderingCustomerBic;
  private String orderingInsAcc;
  private String orderingInsNameAndAddress;
  private String orderingInsBic;
  private String SendingInsAcc;
  private String sendingInsNameAndAddress;
  private String sendingInsBic;
  private String sendersCorrespondentAcc;
  private String sendersCorrespondentNameAndAddress;
  private String sendersCorrespondentBic;
  private String receiversCorrespondentAcc;
  private String receiversCorrespondentNameAndAddress;
  private String receiversCorrespondentBic;
  private String intermediaryAcc;
  private String intermediaryNameAndAddress;
  private String intermediaryBic;
  private String remittanceInfo;
  private String msg;
  private String accountWithInstitutionAcc;
  private String accountWithInstitutionNameAndAddress;
  private String accountWithInstitutionBic;
  private String beneficiaryInfo;
  private LocalDate entryDate;

}
