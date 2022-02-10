package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.currency.ExchangeRateService;
import com.mislbd.ababil.foreignremittance.domain.CbsTemplateTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
import com.mislbd.ababil.foreignremittance.mapper.TemplateBaseTransactionMapper;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.transaction.api.transaction.model.Charge;
import com.mislbd.transaction.api.transaction.model.ChargeInformation;
import com.mislbd.transaction.api.transaction.service.TransactionTemplateService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TemplateBaseTransactionService {

  private static final String ID_DISBURSEMENT_ACTIVITY_ID = "805";
  private static final String ID_PAYMENT_ACTIVITY_ID = "806";
  private final RemittanceChargeInformationService chargeInformationService;
  private final TransactionTemplateService templateService;
  private final TemplateBaseTransactionMapper templateBaseTransactionMapper;
  private final ConfigurationService configurationService;
  private final ExchangeRateService exchangeRateService;

  public TemplateBaseTransactionService(
      RemittanceChargeInformationService chargeInformationService,
      TransactionTemplateService templateService,
      TemplateBaseTransactionMapper templateBaseTransactionMapper,
      ConfigurationService configurationService,
      ExchangeRateService exchangeRateService) {
    this.chargeInformationService = chargeInformationService;
    this.templateService = templateService;
    this.templateBaseTransactionMapper = templateBaseTransactionMapper;
    this.configurationService = configurationService;
    this.exchangeRateService = exchangeRateService;
  }

  public List<CbsTemplateTransaction> buildTransaction(RemittanceTransaction transaction) {
    // Generate Charges for transaction
    Long chargeRateType =
        Long.valueOf(
            configurationService
                .getConfiguration("ID_CHARGE_RATE_TYPE")
                .orElseThrow(() -> new ForeignRemittanceBaseException("Charge Rate Type not found"))
                .getValue());
    String baseCurrency = configurationService.getBaseCurrencyCode();
    BigDecimal chargeRate =
        exchangeRateService
            .findExchangeRate(transaction.getShadowAccountCurrency(), baseCurrency, chargeRateType)
            .getExchangeRate();
    List<Charge> charges =
        chargeInformationService.getChargeInfo(
            transaction.getRemittanceType(),
            transaction.getTransactionTypeId(),
            transaction.getAmountFcy().multiply(chargeRate));
    ChargeInformation chargeInformation =
        ChargeInformation.builder()
            .charges(charges)
            .debitAccount(transaction.getOperatingAccountNumber())
            .debitAccountType(transaction.getOperatingAccountType())
            .debitAccountCurrency(transaction.getOperatingAccountCurrency())
            .build();
    transaction.setChargeInformation(chargeInformation);
    if (transaction.getRemittanceType() == RemittanceType.INWARD_REMITTANCE) {
      return ListResultBuilder.build(
          templateService.buildTransaction(transaction, ID_DISBURSEMENT_ACTIVITY_ID),
          templateBaseTransactionMapper.cbsTxnToRemittanceTxn());
    } else {
      return ListResultBuilder.build(
          templateService.buildTransaction(transaction, ID_PAYMENT_ACTIVITY_ID),
          templateBaseTransactionMapper.cbsTxnToRemittanceTxn());
    }
  }
}
