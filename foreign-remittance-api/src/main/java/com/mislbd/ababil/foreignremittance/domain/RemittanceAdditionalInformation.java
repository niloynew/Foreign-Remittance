package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.swift.broker.model.raw.SelectOptions;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RemittanceAdditionalInformation {

  private long id;
  private String Code;
  private String timeIndication;
  private String Sign;
  private String Offset;
  private String instructedCurrency;
  private BigDecimal instructedAmount;

  private BigDecimal exchangeRate;

  private String sendersChargeCurrency;
  private BigDecimal sendersChargeAmount;

  private String receiversChargeCurrency;
  private BigDecimal receiversChargeAmount;

  private String transactionTypeCode;

  private String instructionCode;
  private String instructionCodeAdditionalInformation;

  private String regulatoryReportingCode;
  private String regulatoryReportingCountryCode;
  private String regulatoryReportingCNarrative;
  private String envelopContents;

  private String remittanceInformation;
  private String senderToReceiverInformation;

  private String sendingInstitutePartyIdentifier;
  private String sendingInstituteIdentifierCode;

  private SelectOptions selectedOrderingInstitutionOption;
  private String orderingInstitutionPartyIdentifier;
  private String orderingInstitutionIdentifierCode;
  private String orderingInstitutionPartyNameAndAddress;

  private SelectOptions selectedSendersCorrespondentOption;
  private String sendersCorrespondentPartyIdentifier;
  private String sendersCorrespondentIdentifierCode;
  private String sendersCorrespondentLocation;
  private String sendersCorrespondentNameAndAddress;

  private SelectOptions selectedReceiversCorrespondentOption;
  private String receiversCorrespondentPartyIdentifier;
  private String receiversCorrespondentIdentifierCode;
  private String receiversCorrespondentLocation;
  private String receiversCorrespondentNameAndAddress;

  private SelectOptions selectedThirdReimbursementInstitutionOption;
  private String thirdReimbursementInstitutionPartyIdentifier;
  private String thirdReimbursementInstitutionIdentifierCode;
  private String thirdReimbursementInstitutionLocation;
  private String thirdReimbursementInstitutionNameAndAddress;

  private SelectOptions selectedIntermediaryInstitutionOption;
  private String intermediaryInstitutionPartyIdentifier;
  private String intermediaryInstitutionIdentifierCode;
  private String intermediaryInstitutionIdentifierNameAndAddress;

  private SelectOptions selectedAccountWithInstitutionOption;
  private String accountWithInstitutionPartyIdentifier;
  private String accountWithInstitutionIdentifierCode;
  private String accountWithInstitutionPartyLocation;
  private String accountWithInstitutionPartyNameAndAddress;
}
