package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.command.api.Command;

public class InactiveShadowAccountCommand extends Command<Account> {

  public InactiveShadowAccountCommand(long accountId, Account payload) {
    super(payload);
  }
}
