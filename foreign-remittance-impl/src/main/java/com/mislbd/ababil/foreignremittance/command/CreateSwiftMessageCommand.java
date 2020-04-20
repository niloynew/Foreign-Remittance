package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceMsgDto;
import com.mislbd.asset.command.api.Command;

public class CreateSwiftMessageCommand extends Command<RemittanceMsgDto> {

  public CreateSwiftMessageCommand(RemittanceMsgDto payload) {
    super(payload);
  }
}
