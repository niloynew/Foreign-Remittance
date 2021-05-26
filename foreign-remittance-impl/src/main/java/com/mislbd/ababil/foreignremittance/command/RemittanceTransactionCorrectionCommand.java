package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.HasIdentity;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Correction Remittance Transaction", group = "ID")
public class RemittanceTransactionCorrectionCommand extends Command<Long> implements HasIdentity {

  public RemittanceTransactionCorrectionCommand(Long payload) {
    super(payload);
  }

  @Override
  public String getIdentity() {
    return String.valueOf(getPayload());
  }
}

