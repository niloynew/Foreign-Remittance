package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDtoBroker;
import com.mislbd.asset.command.api.Command;

public class UpdateNostroReconcileCommand extends Command<NostroReconcileDtoBroker> {
  public UpdateNostroReconcileCommand() {}

  public UpdateNostroReconcileCommand(long id, NostroReconcileDtoBroker payload) {
    super(payload);
  }
}
