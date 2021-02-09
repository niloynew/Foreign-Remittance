package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.BankInformation;
import com.mislbd.ababil.foreignremittance.domain.BankType;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.exception.BankTypeNotFoundException;
import com.mislbd.ababil.foreignremittance.service.BankTypeService;
import com.mislbd.ababil.organization.service.BranchService;
import com.mislbd.security.core.NgSession;
import com.mislbd.swift.broker.model.BankOperationCode;
import com.mislbd.swift.broker.model.DetailsOfCharges;
import com.mislbd.swift.broker.model.raw.SelectOptions;
import com.mislbd.swift.broker.model.raw.mt1xx.MT103MessageRequest;
import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TransactionToRequestMapper {

  private final NgSession ngSession;
  private final BranchService branchService;
  private final BankTypeService bankTypeService;

  public TransactionToRequestMapper(
      NgSession ngSession, BranchService branchService, BankTypeService bankTypeService) {
    this.ngSession = ngSession;
    this.branchService = branchService;
    this.bankTypeService = bankTypeService;
  }

  public MT103MessageRequest mapTransactionToMessageRequest(
      RemittanceTransaction remittanceTransaction, String senderAddress, String receiverAddress) {

    MT103MessageRequest request = new MT103MessageRequest();
    request.setSenderAddress(senderAddress);
    request.setReceiverAddress(receiverAddress);
    List<BankInformation> bankInformationList = remittanceTransaction.getBankInformation();
    for (BankInformation bankInformation : bankInformationList) {
      BankType bankType =
          bankTypeService
              .getBankType(bankInformation.getBankTypeId())
              .orElseThrow(BankTypeNotFoundException::new);
      switch (bankType.getCode()) {
        case "00":
          request.setReceiverAddress(bankInformation.getSwiftCode());
          break;
        case "51":
          request.setSendingInstituteIdentifierCode(bankInformation.getSwiftCode());
          break;
        case "52":
          request.setSelectedOrderingInstitutionOption(SelectOptions.OptionA);
          request.setOrderingInstitutionIdentifierCode(bankInformation.getSwiftCode());
          break;
        case "53":
          request.setSelectedSendersCorrespondentOption(SelectOptions.OptionA);
          request.setSendersCorrespondentIdentifierCode(bankInformation.getSwiftCode());
          break;
        case "54":
          request.setSelectedReceiversCorrespondentOption(SelectOptions.OptionA);
          request.setReceiversCorrespondentIdentifierCode(bankInformation.getSwiftCode());
          break;
        case "55":
          request.setSelectedThirdReimbursementInstitutionOption(SelectOptions.OptionA);
          request.setThirdReimbursementInstitutionIdentifierCode(bankInformation.getSwiftCode());
          break;
        case "56":
          request.setSelectedIntermediaryInstitutionOption(SelectOptions.OptionA);
          request.setIntermediaryInstitutionIdentifierCode(bankInformation.getSwiftCode());
          break;
        case "57":
          request.setSelectedAccountWithInstitutionOption(SelectOptions.OptionA);
          request.setAccountWithInstitutionIdentifierCode(bankInformation.getSwiftCode());
          break;
      }
    }

    request.setSendersReference(remittanceTransaction.getTransactionReferenceNumber());
    request.setBankOperationCode(String.valueOf(BankOperationCode.CRED));
    request.setInterbankSettlementValueDate(Date.valueOf(remittanceTransaction.getValueDate()));
    request.setInterbankSettlementCurrency(remittanceTransaction.getCurrencyCode());
    request.setInterbankSettlementAmount(remittanceTransaction.getAmountFcy());
    request.setInstructedCurrency(null);
    request.setInstructedAmount(null);
    request.setSelectedOrderingCustomerOption(SelectOptions.OptionK);
    request.setOrderingCustomerAccount(remittanceTransaction.getApplicantAccountNumber());
    request.setOrderingCustomerNameAndAddress(
        remittanceTransaction
            .getApplicant()
            .concat(System.lineSeparator())
            .concat(remittanceTransaction.getApplicantAddress()));
    request.setSelectedBeneficiaryCustomerOption(SelectOptions.NoLetterOption);
    request.setBeneficiaryCustomerAccount(remittanceTransaction.getBeneficiaryAccountNumber());
    request.setBeneficiaryCustomerNameAndAddress(
        remittanceTransaction
            .getBeneficiaryName()
            .concat(System.lineSeparator())
            .concat(remittanceTransaction.getBeneficiaryAddress()));
    request.setDetailsOfCharges(String.valueOf(DetailsOfCharges.OUR));
    request.setSenderToReceiverInformation(
        remittanceTransaction.getCommodityDescription() != null
            ? remittanceTransaction.getCommodityDescription()
            : "");
    return request;
  }
}
