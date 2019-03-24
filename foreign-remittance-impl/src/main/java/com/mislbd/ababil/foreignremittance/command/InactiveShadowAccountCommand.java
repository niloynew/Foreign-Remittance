package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.command.api.Command;

public class InactiveShadowAccountCommand extends Command<Account> {

  private String shadowAccountNumber;

  public InactiveShadowAccountCommand(String shadowAccountNumber) {
    super();
    this.shadowAccountNumber = shadowAccountNumber;
  }

  public String getShadowAccountNumber() {
    return shadowAccountNumber;
  }

  public InactiveShadowAccountCommand setShadowAccountNumber(String shadowAccountNumber) {
    this.shadowAccountNumber = shadowAccountNumber;
    return this;
  }
}
