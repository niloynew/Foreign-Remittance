package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.NostroTransactionRecordsDto;
import com.mislbd.asset.command.api.Command;

public class ProcessNostroTransactionCommand extends Command<NostroTransactionRecordsDto> {

  public ProcessNostroTransactionCommand(NostroTransactionRecordsDto payload) {
    super(payload);
  }
}
