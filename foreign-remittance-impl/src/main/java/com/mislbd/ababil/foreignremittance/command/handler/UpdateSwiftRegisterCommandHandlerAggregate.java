package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.UpdateSwiftRegisterCommand;
import com.mislbd.ababil.foreignremittance.service.SwiftRegisterService;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class UpdateSwiftRegisterCommandHandlerAggregate {
  private final SwiftRegisterService swiftRegisterService;
  private final Auditor auditor;

  public UpdateSwiftRegisterCommandHandlerAggregate(
      SwiftRegisterService swiftRegisterService, Auditor auditor) {
    this.swiftRegisterService = swiftRegisterService;
    this.auditor = auditor;
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateRegister(UpdateSwiftRegisterCommand command) {
    swiftRegisterService.updateRegister(command.getPayload());
    return CommandResponse.asVoid();
  }
}
