package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.asset.command.api.Command;
import com.mislbd.swift.broker.model.raw.NostroTransaction;

public class UpdateNostroTransactionCommand extends Command<NostroTransaction> {
  public UpdateNostroTransactionCommand(long id, NostroTransaction payload) {
    super(payload);
  }
}
