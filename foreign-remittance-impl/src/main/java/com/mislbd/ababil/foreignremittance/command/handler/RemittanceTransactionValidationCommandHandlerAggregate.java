package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.CreateRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
import com.mislbd.ababil.foreignremittance.external.service.CASAAccountService;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.ValidationHandler;

@Aggregate
public class RemittanceTransactionValidationCommandHandlerAggregate {

  private final CASAAccountService casaAccountService;

  public RemittanceTransactionValidationCommandHandlerAggregate(
      CASAAccountService casaAccountService) {
    this.casaAccountService = casaAccountService;
  }

  @ValidationHandler
  public void validateRemittanceTransaction(CreateRemittanceTransactionCommand command) {

    RemittanceTransaction remittanceTransaction = command.getPayload();
    if (remittanceTransaction.getCbsTransactions().isEmpty()) {
      throw new ForeignRemittanceBaseException("No transaction leg found");
    }
    if (remittanceTransaction.getRemittanceType().equals(RemittanceType.INWARD_REMITTANCE)) {
      if (remittanceTransaction.getSenderBIC() == null
          || remittanceTransaction.getSenderBIC().isEmpty()) {
        throw new ForeignRemittanceBaseException("Sender BIC not found");
      }
    } else {
      if (remittanceTransaction.getReceiverBIC() == null
          || remittanceTransaction.getReceiverBIC().isEmpty()) {
        throw new ForeignRemittanceBaseException("Receiver BIC not found");
      }
    }
  }
}
