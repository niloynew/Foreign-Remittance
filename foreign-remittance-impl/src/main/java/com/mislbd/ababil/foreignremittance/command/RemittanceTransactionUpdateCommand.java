package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransactionUpdateDto;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.HasIdentity;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Update Remittance Transaction", group = "ID")
public class RemittanceTransactionUpdateCommand extends Command<RemittanceTransactionUpdateDto>
    implements HasIdentity {

  public RemittanceTransactionUpdateCommand(RemittanceTransactionUpdateDto payload) {
    super(payload);
  }

  @Override
  public String getIdentity() {
    return String.valueOf(getPayload().getRemittanceId());
  }
}
