package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.swift.broker.model.raw.SelectOptions;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_ADDITIONAL_INFORMATION_TABLE_NAME)
public class RemittanceAdditionalInformationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "REMITTANCE_INFO_ID_GEN")
  @SequenceGenerator(
      name = "REMITTANCE_INFO_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_ADDITIONAL_INFORMATION_SEQUENCE_NAME)
  private long id;

  @Column(name = "INSTR_CURRENCY")
  private String instructedCurrency;

  @Column(name = "INSTR_AMOUNT")
  private BigDecimal instructedAmount;



  @Column(name = "EXCHANGE_RATE")
  private BigDecimal exchangeRate;

  @Column(name = "SEND_CHARGE_CURRENCY")
  private String sendersChargeCurrency;

  @Column(name = "SEND_CHARGE_AMOUNT")
  private BigDecimal sendersChargeAmount;

  @Column(name = "RECEIVE_CHARGE_CURRENCY")
  private String receiversChargeCurrency;

  @Column(name = "RECEIVE_CHARGE_AMOUNT")
  private BigDecimal receiversChargeAmount;

  @Column(name = "TRAN_TYPE_CODE")
  private String transactionTypeCode;

  @Column(name = "INSTR_CODE")
  private String instructionCode;

  @Column(name = "INSTR_CODE_ADD_INFO")
  private String instructionCodeAdditionalInformation;

  @Column(name = "REG_CODE")
  private String regulatoryReportingCode;

  @Column(name = "REG_COUNTRY_CODE")
  private String regulatoryReportingCountryCode;

  @Column(name = "REG_NARRATIVE")
  private String regulatoryReportingCNarrative;

  @Column(name = "ENV_CONTENTS")
  private String envelopContents;

  @Column(name = "REMIT_INFO")
  private String remittanceInformation;

  @Column(name = "SEND_RECEIVE_INFO")
  private String senderToReceiverInformation;

  @Column(name = "TIME_INDICATION")
  private String timeIndication;

  @Column(name = "CODE")
  private String Code;

  @Column(name = "SIGN")
  private String Sign;

  @Column(name = "OFFSET")
  private String Offset;

  @Column(name = "SEND_IDENTIFIER")
  private String sendingInstitutePartyIdentifier;

  @Column(name = "SEND_IDENTIFIER_CODE")
  private String sendingInstituteIdentifierCode;

  @Enumerated(EnumType.STRING)
  @Column(name = "ORDER_OPTION")
  private SelectOptions selectedOrderingInstitutionOption;

  @Column(name = "ORDER_IDENTIFIER")
  private String orderingInstitutionPartyIdentifier;

  @Column(name = "ORDER_IDENTIFIER_CODE")
  private String orderingInstitutionIdentifierCode;

  @Column(name = "ORDER_NAME_ADDRESS")
  private String orderingInstitutionPartyNameAndAddress;

  @Enumerated(EnumType.STRING)
  @Column(name = "SEND_CORRES_OPT")
  private SelectOptions selectedSendersCorrespondentOption;

  @Column(name = "SEND_CORRES_IDENTIFIER")
  private String sendersCorrespondentPartyIdentifier;

  @Column(name = "SEND_CORRES_CODE")
  private String sendersCorrespondentIdentifierCode;

  @Column(name = "SEND_CORRES_LOCATION")
  private String sendersCorrespondentLocation;

  @Column(name = "SEND_CORRES_NAME")
  private String sendersCorrespondentNameAndAddress;

  @Column(name = "RECEIVE_CORRES_OPT")
  private SelectOptions selectedReceiversCorrespondentOption;

  @Column(name = "RECEIVE_CORRES_IDENTIFIER")
  private String receiversCorrespondentPartyIdentifier;

  @Column(name = "RECEIVE_CORRES_CODE")
  private String receiversCorrespondentIdentifierCode;

  @Column(name = "RECEIVE_CORRES_LOCATION")
  private String receiversCorrespondentLocation;

  @Column(name = "RECEIVE_CORRES_ADDRESS")
  private String receiversCorrespondentNameAndAddress;

  @Enumerated(EnumType.STRING)
  @Column(name = "REIMBURSE_INS_OPT")
  private SelectOptions selectedThirdReimbursementInstitutionOption;

  @Column(name = "REIMBURSE_INS_IDENTIFIER")
  private String thirdReimbursementInstitutionPartyIdentifier;

  @Column(name = "REIMBURSE_INS_CODE")
  private String thirdReimbursementInstitutionIdentifierCode;

  @Column(name = "REIMBURSE_INS_LOCATION")
  private String thirdReimbursementInstitutionLocation;

  @Column(name = "REIMBURSE_INS_ADDRESS")
  private String thirdReimbursementInstitutionNameAndAddress;

  @Enumerated(EnumType.STRING)
  @Column(name = "INTERMEDIARY_INS_OPT")
  private SelectOptions selectedIntermediaryInstitutionOption;

  @Column(name = "INTERMEDIARY_IDENTIFIER")
  private String intermediaryInstitutionPartyIdentifier;

  @Column(name = "INTERMEDIARY_CODE")
  private String intermediaryInstitutionIdentifierCode;

  @Column(name = "INTERMEDIARY_ADDRESS")
  private String intermediaryInstitutionIdentifierNameAndAddress;

  @Enumerated(EnumType.STRING)
  @Column(name = "ACC_WITH_INS_OPT")
  private SelectOptions selectedAccountWithInstitutionOption;

  @Column(name = "ACC_WITH_INS_IDENTIFIER")
  private String accountWithInstitutionPartyIdentifier;

  @Column(name = "ACC_WITH_INS_CODE")
  private String accountWithInstitutionIdentifierCode;

  @Column(name = "ACC_WITH_INS_LOCATION")
  private String accountWithInstitutionPartyLocation;

  @Column(name = "ACC_WITH_INS_ADDRESS")
  private String accountWithInstitutionPartyNameAndAddress;

  @OneToOne
  @JoinColumn(name = "Remittance_Tnx_Id")
  private RemittanceTransactionEntity remittanceTransactionEntity;
}
