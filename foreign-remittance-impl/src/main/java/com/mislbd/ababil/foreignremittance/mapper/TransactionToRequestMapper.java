package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.AdditionalInformation;
import com.mislbd.ababil.foreignremittance.domain.BankInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.swift.broker.model.raw.SelectOptions;
import com.mislbd.swift.broker.model.raw.mt1xx.MT103MessageRequest;
import com.mislbd.swift.broker.model.raw.mt2xx.TimeIndication;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TransactionToRequestMapper {

  //  private final NgSession ngSession;
  //  private final BranchService branchService;
  //  private final BankTypeService bankTypeService;
  //
  //  public TransactionToRequestMapper(
  //      NgSession ngSession, BranchService branchService, BankTypeService bankTypeService) {
  //    this.ngSession = ngSession;
  //    this.branchService = branchService;
  //    this.bankTypeService = bankTypeService;
  //  }
  //
  public MT103MessageRequest mapTransactionToMessageRequest(RemittanceTransaction transaction) {
    MT103MessageRequest request = new MT103MessageRequest();
    request.setSenderAddress(transaction.getSenderBIC());
    request.setReceiverAddress(transaction.getReceiverBIC());
    List<BankInformation> bankInformationList = transaction.getBankInformations();

    for (BankInformation bankInformation : bankInformationList) {
      switch (bankInformation.getBankType()) {
        case SendingInstitution:
          request.setSendingInstituteIdentifierCode(bankInformation.getIdentifierCode());
          request.setSendingInstitutePartyIdentifier(bankInformation.getPartyIdentifier());
          break;
        case OrderingInstitution:
          request.setSelectedOrderingInstitutionOption(bankInformation.getOption());
          request.setOrderingInstitutionPartyIdentifier(bankInformation.getPartyIdentifier());
          if (bankInformation.getOption().equals(SelectOptions.OptionA)) {
            request.setOrderingInstitutionIdentifierCode(bankInformation.getIdentifierCode());
          }
          if (bankInformation.getOption().equals(SelectOptions.OptionD)) {
            request.setOrderingInstitutionPartyNameAndAddress(bankInformation.getNameAndAddress());
          }
          break;
        case SenderCorrespondent:
          request.setSelectedSendersCorrespondentOption(bankInformation.getOption());
          request.setSendersCorrespondentPartyIdentifier(bankInformation.getPartyIdentifier());
          if (bankInformation.getOption().equals(SelectOptions.OptionA)) {
            request.setSendersCorrespondentIdentifierCode(bankInformation.getIdentifierCode());
          }
          if (bankInformation.getOption().equals(SelectOptions.OptionB)) {
            request.setSendersCorrespondentLocation(bankInformation.getLocation());
          }
          if (bankInformation.getOption().equals(SelectOptions.OptionD)) {
            request.setSendersCorrespondentNameAndAddress(bankInformation.getNameAndAddress());
          }
          break;
        case ReceiverCorrespondent:
          request.setSelectedReceiversCorrespondentOption(bankInformation.getOption());
          request.setReceiversCorrespondentPartyIdentifier(bankInformation.getPartyIdentifier());
          if (bankInformation.getOption().equals(SelectOptions.OptionA)) {
            request.setReceiversCorrespondentIdentifierCode(bankInformation.getIdentifierCode());
          }
          if (bankInformation.getOption().equals(SelectOptions.OptionB)) {
            request.setReceiversCorrespondentLocation(bankInformation.getLocation());
          }
          if (bankInformation.getOption().equals(SelectOptions.OptionD)) {
            request.setReceiversCorrespondentNameAndAddress(bankInformation.getNameAndAddress());
          }
          break;
        case ThirdReimbursementInstitution:
          request.setSelectedThirdReimbursementInstitutionOption(bankInformation.getOption());
          request.setThirdReimbursementInstitutionPartyIdentifier(
              bankInformation.getPartyIdentifier());
          if (bankInformation.getOption().equals(SelectOptions.OptionA)) {
            request.setThirdReimbursementInstitutionIdentifierCode(
                bankInformation.getIdentifierCode());
          }
          if (bankInformation.getOption().equals(SelectOptions.OptionB)) {
            request.setThirdReimbursementInstitutionLocation(bankInformation.getLocation());
          }
          if (bankInformation.getOption().equals(SelectOptions.OptionD)) {
            request.setThirdReimbursementInstitutionNameAndAddress(
                bankInformation.getNameAndAddress());
          }
          break;
        case IntermediaryInstitution:
          request.setSelectedIntermediaryInstitutionOption(bankInformation.getOption());
          request.setIntermediaryInstitutionPartyIdentifier(bankInformation.getPartyIdentifier());
          if (bankInformation.getOption().equals(SelectOptions.OptionA)) {
            request.setIntermediaryInstitutionIdentifierCode(bankInformation.getIdentifierCode());
          }
          if (bankInformation.getOption().equals(SelectOptions.OptionD)) {
            request.setIntermediaryInstitutionIdentifierNameAndAddress(
                bankInformation.getNameAndAddress());
          }
          break;
        case AccountWithInstitution:
          request.setSelectedAccountWithInstitutionOption(bankInformation.getOption());
          request.setAccountWithInstitutionPartyIdentifier(bankInformation.getPartyIdentifier());
          if (bankInformation.getOption().equals(SelectOptions.OptionA)) {
            request.setAccountWithInstitutionIdentifierCode(bankInformation.getIdentifierCode());
          }
          if (bankInformation.getOption().equals(SelectOptions.OptionB)) {
            request.setAccountWithInstitutionPartyLocation(bankInformation.getLocation());
          }
          if (bankInformation.getOption().equals(SelectOptions.OptionD)) {
            request.setAccountWithInstitutionPartyNameAndAddress(
                bankInformation.getNameAndAddress());
          }
          break;
      }
    }

    request.setSendersReference(transaction.getTransactionReferenceNumber());
    if (transaction.getShadowAccountCurrency().equalsIgnoreCase("ACU")) {
      request.setInterbankSettlementCurrency("USD");
    } else {
      request.setInterbankSettlementCurrency(transaction.getShadowAccountCurrency());
    }
    request.setInterbankSettlementAmount(transaction.getAmountFcy());
    //            request.setSelectedOrderingCustomerOption(SelectOptions.OptionK);
    //    request.setOrderingCustomerAccount(remittanceTransaction.getOperatingAccountNumber());
    //    request.setOrderingCustomerNameAndAddress(
    //        remittanceTransaction
    //            .getApplicant()
    //            .concat(System.lineSeparator())
    //            .concat(remittanceTransaction.getApplicantAddress()));
    //    request.setSelectedBeneficiaryCustomerOption(SelectOptions.NoLetterOption);
    //
    // request.setBeneficiaryCustomerAccount(remittanceTransaction.getBeneficiaryAccountNumber());
    //    request.setBeneficiaryCustomerNameAndAddress(
    //        remittanceTransaction
    //            .getBeneficiaryName()
    //            .concat(System.lineSeparator())
    //            .concat(remittanceTransaction.getBeneficiaryAddress()));
    request.setRemittanceInformation(
        transaction.getAdditionalInformation().getRemittanceInformation());
    request.setSenderToReceiverInformation(
        transaction.getAdditionalInformation().getSenderToReceiverInformation());

    request.setInterbankSettlementValueDate(transaction.getValueDate());

    if (transaction.getAdditionalInformation() != null) {
      request = mapAdditionalInformation(request, transaction.getAdditionalInformation());
    }
    return request;
  }

  public MT103MessageRequest mapAdditionalInformation(
      MT103MessageRequest request, AdditionalInformation additionalInformation) {
    TimeIndication timeIndication = new TimeIndication();
    List<TimeIndication> timeIndications = new ArrayList<>();
    timeIndication
        .setCode(additionalInformation.getCode())
        .setOffset(additionalInformation.getOffset())
        .setSign(additionalInformation.getSign())
        .setTimeIndication(additionalInformation.getTimeIndication());
    timeIndications.add(timeIndication);
    request
        .setBankOperationCode(additionalInformation.getBankOperationCode().name())
        .setInstructedAmount(additionalInformation.getInstructedAmount())
        .setInstructedCurrency(additionalInformation.getInstructedCurrency())
        .setTimeIndications(timeIndications)
        .setExchangeRate(additionalInformation.getExchangeRate())
        .setSendersChargeCurrency(additionalInformation.getSendersChargeCurrency())
        .setSendersChargeAmount(additionalInformation.getSendersChargeAmount())
        .setReceiversChargeCurrency(additionalInformation.getReceiversChargeCurrency())
        .setReceiversChargeAmount(additionalInformation.getSendersChargeAmount())
        .setTransactionTypeCode(additionalInformation.getTransactionTypeCode())
        .setInstructionCode(additionalInformation.getInstructionCode())
        .setInstructionCodeAdditionalInformation(
            additionalInformation.getInstructionCodeAdditionalInformation())
        .setRegulatoryReportingCode(additionalInformation.getRegulatoryReportingCode())
        .setRegulatoryReportingCountryCode(
            additionalInformation.getRegulatoryReportingCountryCode())
        .setEnvelopeContents(additionalInformation.getEnvelopContents())
        .setRegulatoryReportingCNarrative(additionalInformation.getRegulatoryReportingCNarrative())
        .setRemittanceInformation(additionalInformation.getRemittanceInformation())
        .setSenderToReceiverInformation(additionalInformation.getSenderToReceiverInformation())
        .setDetailsOfCharges(additionalInformation.getDetailsOfCharges().name());
    return request;
  }
}
