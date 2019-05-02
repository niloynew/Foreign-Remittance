package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Approve Inward Remittance Transaction", group = "FOREIGN REMITTANCE")
public class ApproveInwardRemittanceTransactionCommand extends Command<Long> {
  public ApproveInwardRemittanceTransactionCommand() {}

  public ApproveInwardRemittanceTransactionCommand(Long payload) {
    super(payload);
  }
}
