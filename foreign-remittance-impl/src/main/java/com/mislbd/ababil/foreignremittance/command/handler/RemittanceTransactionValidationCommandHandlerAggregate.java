package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.command.SaveInwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.exception.RemittanceTransactionException;
import com.mislbd.ababil.foreignremittance.external.service.CASAAccountService;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.ValidationHandler;

@Aggregate
public class RemittanceTransactionValidationCommandHandlerAggregate {

  private final ShadowAccountRepository shadowAccountRepository;
  private final CASAAccountService casaAccountService;
  private final ConfigurationService configurationService;

  public RemittanceTransactionValidationCommandHandlerAggregate(
      ShadowAccountRepository shadowAccountRepository,
      CASAAccountService casaAccountService,
      ConfigurationService configurationService) {
    this.shadowAccountRepository = shadowAccountRepository;
    this.casaAccountService = casaAccountService;
    this.configurationService = configurationService;
  }

  @ValidationHandler
  public void validateInwardDisbursement(SaveInwardRemittanceTransactionCommand command) {
    RemittanceTransaction remittanceTransaction = command.getPayload();
    /*
    =========================
    Currency related checking
    =========================
    * check the debit account currency and credit account currency
    * if one is cross and another one is local
    *   then process transaction
    * if both the currency are equal
    *   then process transaction
    * if both currencies are foreign and they are not same
    *   send error message
    * */
    String debitAccountCurrency =
        shadowAccountRepository
            .findByNumber(remittanceTransaction.getDebitAccountNumber())
            .get()
            .getCurrencyCode();
    String creditAccountCurrency =
        casaAccountService
            .getAccountByNumber(remittanceTransaction.getCreditAccountNumber())
            .getCurrencyCode();
    if (!debitAccountCurrency.equalsIgnoreCase(creditAccountCurrency)) {
      if (!creditAccountCurrency.equalsIgnoreCase(configurationService.getBaseCurrencyCode())) {
        throw new RemittanceTransactionException(
            "Currency must be equal to debit account currency or Local currency");
      }
    }

    /*
    ========================
    Balance related checking
    ========================
    *
    *
    * */
  }
}
