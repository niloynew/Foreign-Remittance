package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransactionCorrectionRequest;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.HasIdentity;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Correction Remittance Transaction", group = "ID")
public class RemittanceTransactionCorrectionCommand
    extends Command<RemittanceTransactionCorrectionRequest> implements HasIdentity {

  public RemittanceTransactionCorrectionCommand(RemittanceTransactionCorrectionRequest payload) {
    super(payload);
  }

  @Override
  public String getIdentity() {
    return String.valueOf(getPayload().getGlobalTxnNumber());
  }
}
