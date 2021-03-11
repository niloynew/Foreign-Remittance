package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.ShadowTransactionRecord;
import com.mislbd.asset.command.api.Command;

public class RejectShadowTransactionRecordCommand extends Command<ShadowTransactionRecord> {

  private Long id;

  public RejectShadowTransactionRecordCommand(ShadowTransactionRecord payload, Long id) {
    super(payload);
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
