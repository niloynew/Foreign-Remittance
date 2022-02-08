package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Create Remittance Transaction", group = "FOREIGN REMITTANCE")
public class CreateRemittanceTransactionCommand extends Command<RemittanceTransaction> {

  public CreateRemittanceTransactionCommand() {}

  public CreateRemittanceTransactionCommand(RemittanceTransaction payload) {
    super(payload);
  }
}
