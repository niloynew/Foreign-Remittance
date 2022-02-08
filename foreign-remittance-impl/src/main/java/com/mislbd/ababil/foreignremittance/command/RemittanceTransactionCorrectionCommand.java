package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.HasIdentity;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Correction Remittance Transaction", group = "ID")
public class RemittanceTransactionCorrectionCommand extends Command<Long> implements HasIdentity {

  private Long remittanceTransactionId;

  public RemittanceTransactionCorrectionCommand(Long payload, Long remittanceTransactionId) {
    super(payload);
    this.remittanceTransactionId = remittanceTransactionId;
  }

  public Long getRemittanceTransactionId() {
    return remittanceTransactionId;
  }

  public void setRemittanceTransactionId(Long remittanceTransactionId) {
    this.remittanceTransactionId = remittanceTransactionId;
  }

  @Override
  public String getIdentity() {
    return String.valueOf(getPayload());
  }
}
