package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.CbsTemplateTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.transaction.api.transaction.model.CbsTransaction;
import com.mislbd.transaction.api.transaction.model.Charge;
import com.mislbd.transaction.api.transaction.model.ChargeInformation;
import com.mislbd.transaction.api.transaction.service.TransactionTemplateService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TemplateBaseTransactionService {

  private static final String ID_DISBURSEMENT_ACTIVITY_ID = "805";
  private static final String ID_PAYMENT_ACTIVITY_ID = "806";
  private final RemittanceChargeInformationService chargeInformationService;
  private final TransactionTemplateService templateService;

  public TemplateBaseTransactionService(
      RemittanceChargeInformationService chargeInformationService,
      TransactionTemplateService templateService) {
    this.chargeInformationService = chargeInformationService;
    this.templateService = templateService;
  }

  public List<CbsTemplateTransaction> buildTransaction(RemittanceTransaction transaction) {
    // Generate Charges for transaction
    List<Charge> charges =
        chargeInformationService.getChargeInfo(
            transaction.getRemittanceType(),
            transaction.getTransactionTypeId(),
            transaction.getAmountLcy());
    ChargeInformation chargeInformation =
        ChargeInformation.builder()
            .charges(charges)
            .debitAccount(transaction.getOperatingAccountNumber())
            .debitAccountType(transaction.getOperatingAccountType())
            .debitAccountCurrency(transaction.getOperatingAccountCurrency())
            .build();
    transaction.setChargeInformation(chargeInformation);
      List<CbsTemplateTransaction> cbsTemplateTransactions = new ArrayList<>();
      // mapper for CbsTransactions to CbsTemplateTransactions
    if (transaction.getRemittanceType() == RemittanceType.INWARD_REMITTANCE) {
      List<CbsTransaction> cbsTransactions = templateService.buildTransaction(transaction, ID_DISBURSEMENT_ACTIVITY_ID);
    } else {
//        cbsTransactions = templateService.buildTransaction(transaction, ID_PAYMENT_ACTIVITY_ID);
    }
    return cbsTemplateTransactions;
  }
}
