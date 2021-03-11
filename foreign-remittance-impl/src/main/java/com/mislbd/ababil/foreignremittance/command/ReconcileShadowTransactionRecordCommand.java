package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.ShadowTransactionRecordList;
import com.mislbd.asset.command.api.Command;

public class ReconcileShadowTransactionRecordCommand extends Command<ShadowTransactionRecordList> {

  public ReconcileShadowTransactionRecordCommand(ShadowTransactionRecordList payload) {
    super(payload);
  }
}
