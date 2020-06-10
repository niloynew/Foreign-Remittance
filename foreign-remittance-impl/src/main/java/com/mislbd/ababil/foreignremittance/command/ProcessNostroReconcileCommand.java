package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDto;
import com.mislbd.asset.command.api.Command;

public class ProcessNostroReconcileCommand extends Command<NostroReconcileDto> {

  public ProcessNostroReconcileCommand(NostroReconcileDto payload) {
    super(payload);
  }
}
