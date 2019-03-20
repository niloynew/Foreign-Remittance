package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.command.api.Command;

public class SaveNostroAccountCommand extends Command<Account> {

  public SaveNostroAccountCommand(Account payload) {
    super(payload);
  }
}
