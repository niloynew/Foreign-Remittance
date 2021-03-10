package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.ShadowTransactionRecordList;
import com.mislbd.asset.command.api.Command;

public class ShadowTransactionRecordReconcileCommand extends Command<ShadowTransactionRecordList> {

  public ShadowTransactionRecordReconcileCommand(ShadowTransactionRecordList payload) {
    super(payload);
  }
}
