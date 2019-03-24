package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.command.api.Command;

public class UpdateShadowAccountCommand extends Command<Account> {

  public UpdateShadowAccountCommand(long accountId, Account payLoad) {
    super(payLoad);
  }

  public UpdateShadowAccountCommand() {}
}
