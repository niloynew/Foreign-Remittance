package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.command.api.Command;

public class SaveShadowAccountCommand extends Command<Account> {
  public SaveShadowAccountCommand(Account payload) {
    super(payload);
  }
}
