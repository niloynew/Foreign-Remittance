package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.HasIdentity;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Create Remittance Transaction", group = "FOREIGN REMITTANCE")
public class CreateRemittanceTransactionCommand extends Command<RemittanceTransaction>
    implements HasIdentity {

  private String transactionReferenceNumber;

  public CreateRemittanceTransactionCommand() {}

  public CreateRemittanceTransactionCommand(RemittanceTransaction payload) {
    super(payload);
    this.transactionReferenceNumber = payload.getTransactionReferenceNumber();
  }

  @Override
  public String getIdentity() {
    return this.transactionReferenceNumber;
  }
}
