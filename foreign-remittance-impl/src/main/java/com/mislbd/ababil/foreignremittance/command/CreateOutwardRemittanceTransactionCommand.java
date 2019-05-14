package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Approve Outward Remittance Transaction", group = "FOREIGN REMITTANCE")
public class CreateOutwardRemittanceTransactionCommand extends Command<RemittanceTransaction> {

  public CreateOutwardRemittanceTransactionCommand() {}

  public CreateOutwardRemittanceTransactionCommand(RemittanceTransaction payload) {
    super(payload);
  }
}
