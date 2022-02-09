package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.swift.broker.model.BankOperationCode;
import com.mislbd.swift.broker.model.DetailsOfCharges;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AdditionalInformation {

  private long id;

  // Time Indication
  private String Code;
  private String Sign;
  private String timeIndication;
  private String Offset;

  // Currency/Instructed Amount
  private String instructedCurrency;
  private BigDecimal instructedAmount;
  private BigDecimal exchangeRate;

  // Details of charges
  private DetailsOfCharges detailsOfCharges;
  private String sendersChargeCurrency;
  private BigDecimal sendersChargeAmount;
  private String receiversChargeCurrency;
  private BigDecimal receiversChargeAmount;

  // Bank operation code
  private BankOperationCode bankOperationCode;
  private String transactionTypeCode;

  // Instruction code
  private String instructionCode;
  private String instructionCodeAdditionalInformation;

  private String senderToReceiverInformation;
  private String remittanceInformation;

  private String regulatoryReportingCode;
  private String regulatoryReportingCountryCode;
  private String regulatoryReportingCNarrative;
  private String envelopContents;
}
