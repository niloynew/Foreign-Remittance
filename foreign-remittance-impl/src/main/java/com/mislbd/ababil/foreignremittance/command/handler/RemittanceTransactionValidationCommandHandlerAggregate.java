package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.CreateOutwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.CreateRemittanceTransactionCommand;
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

    //    RemittanceTransaction remittanceTransaction = command.getPayload();
    //
    //    if (remittanceTransaction.getChargeAccountType() == AccountType.CASA) {
    //      // check operating account and charge account are same or not
    //      if (remittanceTransaction
    //          .getOperatingAccountNumber()
    //          .equals(remittanceTransaction.getChargeAccountNumber())) {
    //        if (remittanceTransaction
    //                .getAmountRcy()
    //                .compareTo(remittanceTransaction.getChargeAmountRcy())
    //            >= 0) {
    //          return;
    //        }
    //      }
    //
    //      Balance chargeAccountBalance =
    //          casaAccountService.getDepositAccountBalance(
    //              remittanceTransaction.getChargeAccountNumber());
    //      if (remittanceTransaction
    //              .getChargeAmountRcy()
    //              .compareTo(chargeAccountBalance.getAvailableBalance())
    //          > 0) {
    //        throw new ForeignRemittanceBaseException(
    //            "Insufficient account balance in " +
    // remittanceTransaction.getChargeAccountNumber());
    //      }
    //    }
  }

  @ValidationHandler
  public void validateOutwardDisbursement(CreateOutwardRemittanceTransactionCommand command) {
    //    RemittanceTransaction remittanceTransaction = command.getPayload();
    //
    //    if (remittanceTransaction.getOperatingAccountType().equals(AccountType.CASA)
    //        && remittanceTransaction.getChargeAccountType().equals(AccountType.CASA)) {
    //      if (remittanceTransaction
    //          .getOperatingAccountNumber()
    //          .equals(remittanceTransaction.getChargeAccountNumber())) {
    //        BigDecimal totalAmount =
    //
    // remittanceTransaction.getAmountRcy().add(remittanceTransaction.getChargeAmountRcy());
    //        Balance balance =
    //            casaAccountService.getDepositAccountBalance(
    //                remittanceTransaction.getOperatingAccountNumber());
    //        if (totalAmount.compareTo(balance.getAvailableBalance()) > 0) {
    //          throw new ForeignRemittanceBaseException(
    //              "Insufficient account balance in "
    //                  + remittanceTransaction.getOperatingAccountNumber());
    //        }
    //      } else {
    //        Balance remittanceBalance =
    //            casaAccountService.getDepositAccountBalance(
    //                remittanceTransaction.getOperatingAccountNumber());
    //        if
    // (remittanceTransaction.getAmountRcy().compareTo(remittanceBalance.getAvailableBalance())
    //            > 0) {
    //          throw new ForeignRemittanceBaseException(
    //              "Insufficient account balance in "
    //                  + remittanceTransaction.getOperatingAccountNumber());
    //        }
    //        Balance chargeBalance =
    //            casaAccountService.getDepositAccountBalance(
    //                remittanceTransaction.getChargeAccountNumber());
    //        if (remittanceTransaction
    //                .getChargeAmountRcy()
    //                .compareTo(chargeBalance.getAvailableBalance())
    //            > 0) {
    //          throw new ForeignRemittanceBaseException(
    //              "Insufficient account balance in " +
    // remittanceTransaction.getChargeAccountNumber());
    //        }
    //      }
    //    } else if (remittanceTransaction.getOperatingAccountType().equals(AccountType.CASA)) {
    //      Balance remittanceBalance =
    //          casaAccountService.getDepositAccountBalance(
    //              remittanceTransaction.getOperatingAccountNumber());
    //      if
    // (remittanceTransaction.getAmountRcy().compareTo(remittanceBalance.getAvailableBalance())
    //          > 0) {
    //        throw new ForeignRemittanceBaseException(
    //            "Insufficient account balance in " +
    // remittanceTransaction.getOperatingAccountNumber());
    //      }
    //    } else if (remittanceTransaction.getChargeAccountType().equals(AccountType.CASA)) {
    //      Balance chargeBalance =
    //          casaAccountService.getDepositAccountBalance(
    //              remittanceTransaction.getChargeAccountNumber());
    //      if
    // (remittanceTransaction.getChargeAmountRcy().compareTo(chargeBalance.getAvailableBalance())
    //          > 0) {
    //        throw new ForeignRemittanceBaseException(
    //            "Insufficient account balance in " +
    // remittanceTransaction.getChargeAccountNumber());
    //      }
    //    }
  }
}
