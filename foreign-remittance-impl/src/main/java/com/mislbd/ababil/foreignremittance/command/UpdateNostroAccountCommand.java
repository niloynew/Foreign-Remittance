package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.command.api.Command;

public class UpdateNostroAccountCommand extends Command<Account> {

  public UpdateNostroAccountCommand() {}

  public UpdateNostroAccountCommand(Account payload) {
    super(payload);
  }
}
