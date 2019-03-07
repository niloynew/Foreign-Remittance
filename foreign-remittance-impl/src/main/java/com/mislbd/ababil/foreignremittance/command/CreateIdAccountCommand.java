package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.command.api.Command;

public class CreateIdAccountCommand extends Command<Account> {
  public CreateIdAccountCommand(Account payload) {
    super(payload);
  }
}
