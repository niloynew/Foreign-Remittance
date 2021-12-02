package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.HasIdentity;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Create Outward Remittance Transaction", group = "FOREIGN REMITTANCE")
public class CreateOutwardRemittanceTransactionCommand extends Command<RemittanceTransaction>
    implements HasIdentity {

  private String transactionReferenceNumber;

  public CreateOutwardRemittanceTransactionCommand(String transactionReferenceNumber) {
    this.transactionReferenceNumber = transactionReferenceNumber;
  }

  public CreateOutwardRemittanceTransactionCommand(
      RemittanceTransaction payload, String transactionReferenceNumber) {
    super(payload);
    this.transactionReferenceNumber = transactionReferenceNumber;
  }

  @Override
  public String getIdentity() {
    return this.transactionReferenceNumber;
  }
}
