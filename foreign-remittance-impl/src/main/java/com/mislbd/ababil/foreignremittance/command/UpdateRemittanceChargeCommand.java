package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceCharge;
import com.mislbd.asset.command.api.Command;

public class UpdateRemittanceChargeCommand extends Command<RemittanceCharge> {

  public UpdateRemittanceChargeCommand(RemittanceCharge payload) {
    super(payload);
  }
}
