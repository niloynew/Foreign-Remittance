package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.RemittanceMessageDto;
import com.mislbd.asset.command.api.Command;

public class CreateSwiftMessageCommand extends Command<RemittanceMessageDto> {

  public CreateSwiftMessageCommand(RemittanceMessageDto payload) {
    super(payload);
  }
}
