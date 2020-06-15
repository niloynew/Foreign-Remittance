package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDto;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDtoList;
import com.mislbd.asset.command.api.Command;

public class ProcessNostroReconcileCommand extends Command<NostroReconcileDtoList> {

  public ProcessNostroReconcileCommand(NostroReconcileDtoList payload) {
    super(payload);
  }
}
