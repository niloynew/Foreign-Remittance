package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.ExportLC;
import com.mislbd.asset.command.api.Command;

public class SaveExportLCCommand extends Command<ExportLC> {

  public SaveExportLCCommand(ExportLC payload) {
    super(payload);
  }
}
