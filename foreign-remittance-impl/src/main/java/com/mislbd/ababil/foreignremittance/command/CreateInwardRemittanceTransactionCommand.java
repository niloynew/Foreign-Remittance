package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Create Inward Remittance Transaction", group = "FOREIGN REMITTANCE")
public class CreateInwardRemittanceTransactionCommand extends Command<RemittanceTransaction> {

  public CreateInwardRemittanceTransactionCommand() {}

  public CreateInwardRemittanceTransactionCommand(RemittanceTransaction payload) {
    super(payload);
  }
}
