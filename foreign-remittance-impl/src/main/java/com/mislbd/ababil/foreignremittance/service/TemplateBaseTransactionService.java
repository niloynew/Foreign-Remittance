package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.transaction.api.transaction.model.CbsTransaction;
import com.mislbd.transaction.api.transaction.model.Charge;
import com.mislbd.transaction.api.transaction.model.ChargeInformation;
import com.mislbd.transaction.api.transaction.service.TransactionTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateBaseTransactionService {

    private static final String ID_DISBURSEMENT_ACTIVITY_ID = "805";
    private static final String ID_PAYMENT_ACTIVITY_ID = "806";
    private final RemittanceChargeInformationService chargeInformationService;
    private final TransactionTemplateService templateService;

    public TemplateBaseTransactionService(RemittanceChargeInformationService chargeInformationService, TransactionTemplateService templateService) {
        this.chargeInformationService = chargeInformationService;
        this.templateService = templateService;
    }

    public List<CbsTransaction> buildTransaction(RemittanceTransaction transaction) {
        // Generate Charges for transaction
        List<Charge> charges = chargeInformationService.getChargeInfo(transaction.getRemittanceType(), transaction.getTransactionTypeId(), transaction.getAmountLcy());
        ChargeInformation chargeInformation = ChargeInformation.builder()
                .charges(charges)
                .debitAccount(transaction.getOperatingAccountNumber())
                .debitAccountType(transaction.getOperatingAccountType())
                .debitAccountCurrency(transaction.getOperatingAccountCurrency())
                .build();
        transaction.setChargeInformation(chargeInformation);
        if(transaction.getRemittanceType() == RemittanceType.INWARD_REMITTANCE){
            return templateService.buildTransaction(transaction, ID_DISBURSEMENT_ACTIVITY_ID);
        } else {
            return templateService.buildTransaction(transaction, ID_PAYMENT_ACTIVITY_ID);
        }
    }
}

