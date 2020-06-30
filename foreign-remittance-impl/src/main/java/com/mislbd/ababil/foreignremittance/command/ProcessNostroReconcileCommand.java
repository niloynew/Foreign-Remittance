package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDtoBrokerList;
import com.mislbd.asset.command.api.Command;

public class ProcessNostroReconcileCommand extends Command<NostroReconcileDtoBrokerList> {

  public ProcessNostroReconcileCommand(NostroReconcileDtoBrokerList payload) {
    super(payload);
  }
}
