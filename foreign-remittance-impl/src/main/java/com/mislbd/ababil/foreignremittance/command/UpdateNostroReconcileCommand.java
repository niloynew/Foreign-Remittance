package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDto;
import com.mislbd.asset.command.api.Command;

public class UpdateNostroReconcileCommand extends Command<NostroReconcileDto> {
  public UpdateNostroReconcileCommand() {}

  public UpdateNostroReconcileCommand(long id, NostroReconcileDto payload) {
    super(payload);
  }
}
