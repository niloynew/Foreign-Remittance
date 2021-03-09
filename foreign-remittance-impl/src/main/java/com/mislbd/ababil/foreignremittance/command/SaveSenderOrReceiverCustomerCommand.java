package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.SenderOrReceiverCustomer;
import com.mislbd.asset.command.api.Command;

public class SaveSenderOrReceiverCustomerCommand extends Command<SenderOrReceiverCustomer> {

  public SaveSenderOrReceiverCustomerCommand(SenderOrReceiverCustomer payload) {
    super(payload);
  }
}
