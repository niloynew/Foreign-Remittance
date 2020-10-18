package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.asset.command.api.Command;
import com.mislbd.swift.broker.model.raw.NostroAccountTransactionsDto;

public class ProcessNostroTransactionCommand extends Command<NostroAccountTransactionsDto> {

  public ProcessNostroTransactionCommand(NostroAccountTransactionsDto payload) {
    super(payload);
  }
}
