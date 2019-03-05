package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.asset.command.api.Command;

public class DeleteIDProductCommand extends Command<Long> {
  public DeleteIDProductCommand(Long payload) {
    super(payload);
  }
}
