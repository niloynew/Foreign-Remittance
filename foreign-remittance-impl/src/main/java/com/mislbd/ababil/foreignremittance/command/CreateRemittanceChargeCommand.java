package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceCharge;
import com.mislbd.asset.command.api.Command;

public class CreateRemittanceChargeCommand extends Command<RemittanceCharge> {

  public CreateRemittanceChargeCommand(RemittanceCharge payload) {
    super(payload);
  }
}
