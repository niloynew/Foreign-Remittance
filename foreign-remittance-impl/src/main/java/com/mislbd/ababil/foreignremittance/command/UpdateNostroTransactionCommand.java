package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.NostroTransactionRecord;
import com.mislbd.asset.command.api.Command;

public class UpdateNostroTransactionCommand extends Command<NostroTransactionRecord> {
  public UpdateNostroTransactionCommand(long id, NostroTransactionRecord payload) {
    super(payload);
  }
}
