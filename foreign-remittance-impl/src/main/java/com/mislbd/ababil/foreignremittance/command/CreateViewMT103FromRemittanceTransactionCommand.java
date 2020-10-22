package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.asset.command.api.Command;

public class CreateViewMT103FromRemittanceTransactionCommand
    extends Command<RemittanceTransaction> {
  public CreateViewMT103FromRemittanceTransactionCommand() {}

  public CreateViewMT103FromRemittanceTransactionCommand(RemittanceTransaction payload) {
    super(payload);
  }
}
