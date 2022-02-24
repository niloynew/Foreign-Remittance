package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.swift.broker.model.BankOperationCode;
import com.mislbd.swift.broker.model.DetailsOfCharges;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_ADDITIONAL_INFORMATION_TABLE_NAME)
public class AdditionalInformationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "REMITTANCE_INFO_ID_GEN")
  @SequenceGenerator(
      name = "REMITTANCE_INFO_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_ADDITIONAL_INFORMATION_SEQUENCE_NAME)
  private long id;

  @Column(name = "TIME_INDICATION")
  private String timeIndication;

  @Column(name = "CODE")
  private String Code;

  @Column(name = "SIGN")
  private String Sign;

  @Column(name = "OFFSET")
  private String Offset;

  @Column(name = "INSTR_CURRENCY")
  private String instructedCurrency;

  @Column(name = "INSTR_AMOUNT")
  private BigDecimal instructedAmount;

  @Column(name = "EXCHANGE_RATE")
  private BigDecimal exchangeRate;

  @Column(name = "DETAILS_OF_CHARGES")
  private DetailsOfCharges detailsOfCharges;

  @Column(name = "SEND_CHARGE_CURRENCY")
  private String sendersChargeCurrency;

  @Column(name = "SEND_CHARGE_AMOUNT")
  private BigDecimal sendersChargeAmount;

  @Column(name = "RECEIVE_CHARGE_CURRENCY")
  private String receiversChargeCurrency;

  @Column(name = "RECEIVE_CHARGE_AMOUNT")
  private BigDecimal receiversChargeAmount;

  @Column(name = "BANK_OPERATION_CODE")
  private BankOperationCode bankOperationCode;

  @Column(name = "TRAN_TYPE_CODE")
  private String transactionTypeCode;

  @Column(name = "INSTR_CODE")
  private String instructionCode;

  @Column(name = "INSTR_CODE_ADD_INFO")
  private String instructionCodeAdditionalInformation;

  @Column(name = "REMIT_INFO")
  private String remittanceInformation;

  @Column(name = "SEND_RECEIVE_INFO")
  private String senderToReceiverInformation;

  @Column(name = "REG_CODE")
  private String regulatoryReportingCode;

  @Column(name = "REG_COUNTRY_CODE")
  private String regulatoryReportingCountryCode;

  @Column(name = "REG_NARRATIVE")
  private String regulatoryReportingCNarrative;

  @Column(name = "ENV_CONTENTS")
  private String envelopContents;

  @OneToOne
  @JoinColumn(name = "REMITTANCE_TNX_ID", referencedColumnName = "ID")
  private RemittanceTransactionEntity remittanceTransactionEntity;
}
