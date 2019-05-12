package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.asset.command.api.Command;

public class SaveInwardRemittanceTransactionCommand extends Command<RemittanceTransaction> {

  public SaveInwardRemittanceTransactionCommand(RemittanceTransaction payload) {
    super(payload);
  }
}
