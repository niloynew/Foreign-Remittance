package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeMapping;
import com.mislbd.asset.command.api.Command;

public class CreateRemittanceChargeMappingCommand extends Command<RemittanceChargeMapping> {

  public CreateRemittanceChargeMappingCommand(RemittanceChargeMapping payload) {
    super(payload);
  }
}
