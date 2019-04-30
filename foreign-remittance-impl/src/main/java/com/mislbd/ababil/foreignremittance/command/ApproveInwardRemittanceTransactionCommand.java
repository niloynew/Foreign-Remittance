package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.asset.command.api.Command;

public class ApproveInwardRemittanceTransactionCommand extends Command<Long> {
  public ApproveInwardRemittanceTransactionCommand() {}

  public ApproveInwardRemittanceTransactionCommand(Long payload) {
    super(payload);
  }
}
