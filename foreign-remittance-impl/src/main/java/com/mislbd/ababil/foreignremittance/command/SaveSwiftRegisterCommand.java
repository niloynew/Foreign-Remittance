package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.SwiftRegister;
import com.mislbd.asset.command.api.Command;

public class SaveSwiftRegisterCommand extends Command<SwiftRegister> {
  public SaveSwiftRegisterCommand(SwiftRegister payload) {
    super(payload);
  }
}
