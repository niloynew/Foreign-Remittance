package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.command.api.Command;

public class InactiveNostroAccountCommand extends Command<Account> {

  private String nostroAccountNumber;

  public InactiveNostroAccountCommand(String nostroAccountNumber) {
    super();
    this.nostroAccountNumber = nostroAccountNumber;
  }

  public String getNostroAccountNumber() {
    return nostroAccountNumber;
  }

  public InactiveNostroAccountCommand setNostroAccountNumber(String nostroAccountNumber) {
    this.nostroAccountNumber = nostroAccountNumber;
    return this;
  }
}
