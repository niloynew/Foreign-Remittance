package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.CreateRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
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
  public void validateInwardDisbursement(CreateRemittanceTransactionCommand command) {

    RemittanceTransaction remittanceTransaction = command.getPayload();
    if (remittanceTransaction.getCbsTransactions().isEmpty()) {
      throw new ForeignRemittanceBaseException("No transaction leg found");
    }
  }
}
