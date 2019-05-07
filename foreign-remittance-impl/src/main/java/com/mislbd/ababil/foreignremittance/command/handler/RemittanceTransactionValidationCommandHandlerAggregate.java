package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.command.SaveInwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.AccountType;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.exception.RemittanceTransactionException;
import com.mislbd.ababil.foreignremittance.external.domain.Account;
import com.mislbd.ababil.foreignremittance.external.domain.Balance;
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
    String localCurrency = configurationService.getBaseCurrencyCode();
    RemittanceTransaction remittanceTransaction = command.getPayload();
    /*
     * =================
     * General checking
     * =================
     * Check credit account is GL or CASA
     * IF CASA selected then
     *   check currency
     * Else
     *   proceed transaction
     * */

    if (remittanceTransaction.getCreditAccountType() == AccountType.CASA) {

      /*
      =========================
      Currency related checking
      =========================
      * if credit account is active then
      *   check the debit account currency and credit account currency
      *   if debit account currency is foreign and credit account currency is local
      *     then proceed transaction
      *   if both the currency are equal
      *     then proceed transaction
      *   if both currencies are foreign and they are not same
      *     send error message
      * else
      *   send error message
      * */
      Account creditAccount =
          casaAccountService.getAccountByNumber(remittanceTransaction.getCreditAccountNumber());
      if (creditAccount.getStatus().equalsIgnoreCase("ACTIVATED")) {
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
          if (!creditAccountCurrency.equalsIgnoreCase(localCurrency)) {
            throw new RemittanceTransactionException(
                "Debit account currency must be equal to credit account currency or local currency");
          }
        }
      } else {
        throw new RemittanceTransactionException("Selected credit account is not active");
      }
    }

    /*
    ========================
    Balance related checking
    ========================
    * if charge account type CASA selected
    *   if charge account currency is not local
    *     then throw exception
    *   else
    *     check the balance of the account
    *     if balance is less than total charge and vat amount
    *       send error message
    *     else
    *       proceed transaction
    * */

    if (remittanceTransaction.getChargeAccountType() == AccountType.CASA) {
      Account chargeAccount =
          casaAccountService.getAccountByNumber(remittanceTransaction.getChargeAccountNumber());
      if (chargeAccount.getStatus().equalsIgnoreCase("ACTIVATED")) {
        if (!chargeAccount.getCurrencyCode().equalsIgnoreCase(localCurrency)) {
          throw new RemittanceTransactionException("Charge account must be of local currency");
        } else {
          Balance balance =
              casaAccountService.getDepositAccountBalance(
                  remittanceTransaction.getChargeAccountNumber());

          if (balance
                  .getAvailableBalance()
                  .compareTo(
                      remittanceTransaction
                          .getTotalChargeAmount()
                          .add(remittanceTransaction.getTotalVatAmount()))
              == -1) {
            throw new RemittanceTransactionException(
                "Insufficient balance in " + remittanceTransaction.getChargeAccountNumber());
          }
        }
      } else {
        throw new RemittanceTransactionException("Selected charge account is not active");
      }
    }
  }
}
