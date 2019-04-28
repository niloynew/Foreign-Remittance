package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.command.api.Command;

public class InactiveNostroAccountCommand extends Command<Account> {

  public InactiveNostroAccountCommand(long accountId, Account payload) {
    super(payload);
  }
}
