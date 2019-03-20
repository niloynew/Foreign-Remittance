package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceMsgDto;
import com.mislbd.asset.command.api.Command;

public class CreateSwiftMsgCommand extends Command<RemittanceMsgDto> {

  public CreateSwiftMsgCommand(RemittanceMsgDto payload) {
    super(payload);
  }
}
