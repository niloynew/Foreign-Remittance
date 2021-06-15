package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.CreateInwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.CreateOutwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.AccountType;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
import com.mislbd.ababil.foreignremittance.exception.RemittanceTransactionException;
import com.mislbd.ababil.foreignremittance.external.domain.Balance;
import com.mislbd.ababil.foreignremittance.external.service.CASAAccountService;
import com.mislbd.ababil.foreignremittance.mapper.TransactionToRequestMapper;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.ValidationHandler;
import com.mislbd.swift.broker.model.raw.mt1xx.MT103MessageRequest;
import com.mislbd.swift.broker.service.XmmIntegrationService;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
public class RemittanceTransactionValidationCommandHandlerAggregate {

  private final CASAAccountService casaAccountService;
  private final TransactionToRequestMapper transactionToRequestMapper;
  private final XmmIntegrationService xmmIntegrationService;

  Logger logger =
      LoggerFactory.getLogger(RemittanceTransactionValidationCommandHandlerAggregate.class);

  public RemittanceTransactionValidationCommandHandlerAggregate(
      CASAAccountService casaAccountService,
      TransactionToRequestMapper transactionToRequestMapper,
      XmmIntegrationService xmmIntegrationService) {
    this.casaAccountService = casaAccountService;
    this.transactionToRequestMapper = transactionToRequestMapper;
    this.xmmIntegrationService = xmmIntegrationService;
  }

  @ValidationHandler
  public void validateInwardDisbursement(CreateInwardRemittanceTransactionCommand command) {

    RemittanceTransaction remittanceTransaction = command.getPayload();
    if (remittanceTransaction.getOperatingAccountType().equals(AccountType.GL)) {
      return;
    }

    if (remittanceTransaction.getChargeAccountType() == AccountType.CASA) {
      Balance chargeAccountBalance =
          casaAccountService.getDepositAccountBalance(
              remittanceTransaction.getChargeAccountNumber());
      if (chargeAccountBalance
              .getAvailableBalance()
              .compareTo(
                  remittanceTransaction
                      .getTotalChargeAmountAfterWaived()
                      .add(remittanceTransaction.getTotalVatAmountAfterWaived()))
          < 0) {
        throw new ForeignRemittanceBaseException(
            "Insufficient creditAccountBalance in "
                + remittanceTransaction.getChargeAccountNumber());
      }
    }
  }

  @ValidationHandler
  public void validateOutwardDisbursement(CreateOutwardRemittanceTransactionCommand command) {
    RemittanceTransaction remittanceTransaction = command.getPayload();
    if (remittanceTransaction.getOperatingAccountType().equals(AccountType.GL)) {
      return;
    }
    if (remittanceTransaction
        .getOperatingAccountNumber()
        .equals(remittanceTransaction.getChargeAccountNumber())) {
      Balance balance =
          casaAccountService.getDepositAccountBalance(
              remittanceTransaction.getChargeAccountNumber());
      BigDecimal totalBalance =
          remittanceTransaction
              .getAmountRcy()
              .add(
                  remittanceTransaction
                      .getTotalChargeAmountAfterWaived()
                      .add(remittanceTransaction.getTotalVatAmountAfterWaived()));
      if (balance.getAvailableBalance().compareTo(totalBalance) < 0) {
        throw new RemittanceTransactionException(
            "Insufficient balance in " + remittanceTransaction.getChargeAccountNumber());
      }
    } else {
      if (remittanceTransaction.getOperatingAccountType() == AccountType.CASA) {
        Balance creditAccountBalance =
            casaAccountService.getDepositAccountBalance(
                remittanceTransaction.getOperatingAccountNumber());
        if (creditAccountBalance
                .getAvailableBalance()
                .compareTo(remittanceTransaction.getAmountRcy())
            < 0) {
          throw new ForeignRemittanceBaseException(
              "Insufficient creditAccountBalance in "
                  + remittanceTransaction.getOperatingAccountNumber());
        }
      }

      if (remittanceTransaction.getChargeAccountType() == AccountType.CASA) {
        Balance chargeAccountBalance =
            casaAccountService.getDepositAccountBalance(
                remittanceTransaction.getChargeAccountNumber());
        if (chargeAccountBalance
                .getAvailableBalance()
                .compareTo(
                    remittanceTransaction
                        .getTotalChargeAmountAfterWaived()
                        .add(remittanceTransaction.getTotalVatAmountAfterWaived()))
            < 0) {
          throw new ForeignRemittanceBaseException(
              "Insufficient creditAccountBalance in "
                  + remittanceTransaction.getChargeAccountNumber());
        }
      }
    }

    if (remittanceTransaction.isDoPublishMT103()) {
      publishMT103(remittanceTransaction);
    }
  }

  private void publishMT103(RemittanceTransaction remittanceTransaction) {
    MT103MessageRequest mt103MessageRequest =
        transactionToRequestMapper.mapTransactionToMessageRequest(remittanceTransaction);
    try {
      xmmIntegrationService.publishCategoryNMessage(mt103MessageRequest);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ForeignRemittanceBaseException("Message can't publish");
    }
  }
}
