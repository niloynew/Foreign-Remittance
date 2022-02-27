package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.swift.broker.model.BankOperationCode;
import com.mislbd.swift.broker.model.DetailsOfCharges;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
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
  @NotNull(message = "Details of charges can't be empty")
  private DetailsOfCharges detailsOfCharges;

  private String sendersChargeCurrency;
  private BigDecimal sendersChargeAmount;
  private String receiversChargeCurrency;
  private BigDecimal receiversChargeAmount;

  // Bank operation code
  @NotNull(message = "Bank operation code can't be empty")
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
