package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.asset.command.api.Command;

public class DeleteChargeMappingCommand extends Command<Long> {

  public DeleteChargeMappingCommand(Long payload) {
    super(payload);
  }
}
