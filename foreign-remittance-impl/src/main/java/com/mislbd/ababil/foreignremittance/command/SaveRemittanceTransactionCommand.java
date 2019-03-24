package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.asset.command.api.Command;

public class SaveRemittanceTransactionCommand extends Command<RemittanceTransaction> {

  public SaveRemittanceTransactionCommand(RemittanceTransaction payload) {
    super(payload);
  }
}
