package com.mislbd.ababil.foreignremittance.mapper;

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
  //  public MT103MessageRequest mapTransactionToMessageRequest(
  //      RemittanceTransaction remittanceTransaction) {
  //    MT103MessageRequest request = new MT103MessageRequest();
  //    request.setSenderAddress(
  //        branchService.findBranch(ngSession.getUserBranch()).get().getSwiftCode());
  //
  //    List<BankInformation> bankInformationList = remittanceTransaction.getBankInformation();
  //
  //    for (BankInformation bankInformation : bankInformationList) {
  //      BankType bankType =
  //          bankTypeService
  //              .getBankType(bankInformation.getBankTypeId())
  //              .orElseThrow(BankTypeNotFoundException::new);
  //
  //      if (bankType.getCode().equalsIgnoreCase("00")) {
  //        request.setReceiverAddress(bankInformation.getSwiftCode());
  //      }
  //      if (!remittanceTransaction.isPublishedToXmm()) {
  //        switch (bankType.getCode()) {
  //          case "51":
  //            request.setSendingInstituteIdentifierCode(bankInformation.getSwiftCode());
  //            break;
  //          case "52":
  //            request.setSelectedOrderingInstitutionOption(SelectOptions.OptionA);
  //            request.setOrderingInstitutionIdentifierCode(bankInformation.getSwiftCode());
  //            break;
  //          case "53":
  //            request.setSelectedSendersCorrespondentOption(SelectOptions.OptionA);
  //            request.setSendersCorrespondentIdentifierCode(bankInformation.getSwiftCode());
  //            break;
  //          case "54":
  //            request.setSelectedReceiversCorrespondentOption(SelectOptions.OptionA);
  //            request.setReceiversCorrespondentIdentifierCode(bankInformation.getSwiftCode());
  //            break;
  //          case "55":
  //            request.setSelectedThirdReimbursementInstitutionOption(SelectOptions.OptionA);
  //
  // request.setThirdReimbursementInstitutionIdentifierCode(bankInformation.getSwiftCode());
  //            break;
  //          case "56":
  //            request.setSelectedIntermediaryInstitutionOption(SelectOptions.OptionA);
  //            request.setIntermediaryInstitutionIdentifierCode(bankInformation.getSwiftCode());
  //            break;
  //          case "57":
  //            request.setSelectedAccountWithInstitutionOption(SelectOptions.OptionA);
  //            request.setAccountWithInstitutionIdentifierCode(bankInformation.getSwiftCode());
  //            break;
  //        }
  //      }
  //    }
  //
  //    request.setSendersReference(remittanceTransaction.getTransactionReferenceNumber());
  //
  //    request.setBankOperationCode(String.valueOf(BankOperationCode.CRED));
  //    request.setInterbankSettlementValueDate(remittanceTransaction.getValueDate());
  //    if (remittanceTransaction.getShadowAccountCurrency().equalsIgnoreCase("ACU")) {
  //      request.setInterbankSettlementCurrency("USD");
  //    } else {
  //      request.setInterbankSettlementCurrency(remittanceTransaction.getShadowAccountCurrency());
  //    }
  //    request.setInterbankSettlementAmount(remittanceTransaction.getAmountFcy());
  //    request.setInstructedCurrency(null);
  //    request.setInstructedAmount(null);
  //    request.setSelectedOrderingCustomerOption(SelectOptions.OptionK);
  //    request.setOrderingCustomerAccount(remittanceTransaction.getOperatingAccountNumber());
  //    request.setOrderingCustomerNameAndAddress(
  //        remittanceTransaction
  //            .getApplicant()
  //            .concat(System.lineSeparator())
  //            .concat(remittanceTransaction.getApplicantAddress()));
  //    request.setSelectedBeneficiaryCustomerOption(SelectOptions.NoLetterOption);
  //    request.setBeneficiaryCustomerAccount(remittanceTransaction.getBeneficiaryAccountNumber());
  //    request.setBeneficiaryCustomerNameAndAddress(
  //        remittanceTransaction
  //            .getBeneficiaryName()
  //            .concat(System.lineSeparator())
  //            .concat(remittanceTransaction.getBeneficiaryAddress()));
  //    request.setDetailsOfCharges(String.valueOf(DetailsOfCharges.OUR));
  //    request.setRemittanceInformation(
  //        remittanceTransaction.getCommodityDescription() != null
  //            ? remittanceTransaction.getCommodityDescription()
  //            : "");
  //    request.setSenderToReceiverInformation(
  //        remittanceTransaction.getDeliveryTerm() != null
  //            ? remittanceTransaction.getDeliveryTerm()
  //            : "");
  //    //
  // request.setInterbankSettlementValueDate(Date.valueOf(remittanceTransaction.getValueDate()));
  //
  //    if (remittanceTransaction.getRemittanceAdditionalInformation() != null) {
  //      request =
  //          mapAdditionalInformation(
  //              request, remittanceTransaction.getRemittanceAdditionalInformation());
  //    }
  //    return request;
  //  }
  //
  //  public MT103MessageRequest mapAdditionalInformation(
  //      MT103MessageRequest request, AdditionalInformation additionalInformation) {
  //    TimeIndication timeIndication = new TimeIndication();
  //    List<TimeIndication> timeIndications = new ArrayList<TimeIndication>();
  //    timeIndication
  //        .setCode(additionalInformation.getCode())
  //        .setOffset(additionalInformation.getOffset())
  //        .setSign(additionalInformation.getSign())
  //        .setTimeIndication(additionalInformation.getTimeIndication());
  //    timeIndications.add(timeIndication);
  //
  //    request
  //        .setInstructedAmount(additionalInformation.getInstructedAmount())
  //        .setInstructedCurrency(additionalInformation.getInstructedCurrency())
  //        .setTimeIndications(timeIndications)
  //        .setExchangeRate(additionalInformation.getExchangeRate())
  //        .setSendersChargeCurrency(additionalInformation.getSendersChargeCurrency())
  //        .setSendersChargeAmount(additionalInformation.getSendersChargeAmount())
  //        .setReceiversChargeCurrency(additionalInformation.getReceiversChargeCurrency())
  //        .setReceiversChargeAmount(additionalInformation.getSendersChargeAmount())
  //        .setTransactionTypeCode(additionalInformation.getTransactionTypeCode())
  //        .setInstructionCode(additionalInformation.getInstructionCode())
  //        .setInstructionCodeAdditionalInformation(
  //            additionalInformation.getInstructionCodeAdditionalInformation())
  //        .setRegulatoryReportingCode(additionalInformation.getRegulatoryReportingCode())
  //        .setRegulatoryReportingCountryCode(
  //            additionalInformation.getRegulatoryReportingCountryCode())
  //        .setEnvelopeContents(additionalInformation.getEnvelopContents())
  //
  // .setRegulatoryReportingCNarrative(additionalInformation.getRegulatoryReportingCNarrative())
  //        .setRemittanceInformation(additionalInformation.getRemittanceInformation())
  //        .setSenderToReceiverInformation(additionalInformation.getSenderToReceiverInformation())
  //        .setSendingInstitutePartyIdentifier(
  //            additionalInformation.getSendingInstitutePartyIdentifier())
  //        .setSendingInstituteIdentifierCode(
  //            additionalInformation.getSendingInstituteIdentifierCode())
  //        .setSelectedOrderingInstitutionOption(
  //            additionalInformation.getSelectedOrderingInstitutionOption())
  //        .setOrderingInstitutionPartyIdentifier(
  //            additionalInformation.getOrderingInstitutionPartyIdentifier())
  //        .setOrderingInstitutionIdentifierCode(
  //            additionalInformation.getOrderingInstitutionIdentifierCode())
  //        .setOrderingInstitutionPartyNameAndAddress(
  //            additionalInformation.getOrderingInstitutionPartyNameAndAddress())
  //        .setSelectedSendersCorrespondentOption(
  //            additionalInformation.getSelectedSendersCorrespondentOption())
  //        .setSendersCorrespondentPartyIdentifier(
  //            additionalInformation.getSendersCorrespondentPartyIdentifier())
  //        .setSendersCorrespondentIdentifierCode(
  //            additionalInformation.getSendersCorrespondentIdentifierCode())
  //
  // .setSendersCorrespondentLocation(additionalInformation.getSendersCorrespondentLocation())
  //        .setSendersCorrespondentNameAndAddress(
  //            additionalInformation.getSendersCorrespondentNameAndAddress())
  //        .setSelectedReceiversCorrespondentOption(
  //            additionalInformation.getSelectedReceiversCorrespondentOption())
  //        .setReceiversCorrespondentPartyIdentifier(
  //            additionalInformation.getReceiversCorrespondentPartyIdentifier())
  //        .setReceiversCorrespondentIdentifierCode(
  //            additionalInformation.getReceiversCorrespondentIdentifierCode())
  //        .setReceiversCorrespondentLocation(
  //            additionalInformation.getReceiversCorrespondentLocation())
  //        .setReceiversCorrespondentNameAndAddress(
  //            additionalInformation.getReceiversCorrespondentNameAndAddress())
  //        .setSelectedThirdReimbursementInstitutionOption(
  //            additionalInformation.getSelectedThirdReimbursementInstitutionOption())
  //        .setThirdReimbursementInstitutionPartyIdentifier(
  //            additionalInformation.getThirdReimbursementInstitutionPartyIdentifier())
  //        .setThirdReimbursementInstitutionIdentifierCode(
  //            additionalInformation.getThirdReimbursementInstitutionIdentifierCode())
  //        .setThirdReimbursementInstitutionLocation(
  //            additionalInformation.getThirdReimbursementInstitutionLocation())
  //        .setThirdReimbursementInstitutionNameAndAddress(
  //            additionalInformation.getThirdReimbursementInstitutionNameAndAddress())
  //        .setSelectedIntermediaryInstitutionOption(
  //            additionalInformation.getSelectedIntermediaryInstitutionOption())
  //        .setIntermediaryInstitutionPartyIdentifier(
  //            additionalInformation.getIntermediaryInstitutionPartyIdentifier())
  //        .setIntermediaryInstitutionIdentifierCode(
  //            additionalInformation.getIntermediaryInstitutionIdentifierCode())
  //        .setIntermediaryInstitutionIdentifierNameAndAddress(
  //            additionalInformation.getIntermediaryInstitutionIdentifierNameAndAddress())
  //        .setSelectedAccountWithInstitutionOption(
  //            additionalInformation.getSelectedAccountWithInstitutionOption())
  //        .setAccountWithInstitutionPartyIdentifier(
  //            additionalInformation.getAccountWithInstitutionPartyIdentifier())
  //        .setAccountWithInstitutionIdentifierCode(
  //            additionalInformation.getAccountWithInstitutionIdentifierCode())
  //        .setAccountWithInstitutionPartyLocation(
  //            additionalInformation.getAccountWithInstitutionPartyLocation())
  //        .setAccountWithInstitutionPartyNameAndAddress(
  //            additionalInformation.getAccountWithInstitutionPartyNameAndAddress());
  //    return request;
  //  }
}
