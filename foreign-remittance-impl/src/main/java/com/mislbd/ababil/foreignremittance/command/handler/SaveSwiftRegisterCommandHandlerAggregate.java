package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.*;
import com.mislbd.ababil.foreignremittance.mapper.SwiftRegisterMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.SwiftRegisterRepository;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import org.springframework.transaction.annotation.Transactional;

public class SaveSwiftRegisterCommandHandlerAggregate {
  private SwiftRegisterRepository swiftRegisterRepository;
  private SwiftRegisterMapper swiftRegisterMapper;
  private final Auditor auditor;

  public SaveSwiftRegisterCommandHandlerAggregate(
      SwiftRegisterRepository swiftRegisterRepository,
      SwiftRegisterMapper swiftRegisterMapper,
      Auditor auditor) {
    this.swiftRegisterRepository = swiftRegisterRepository;
    this.swiftRegisterMapper = swiftRegisterMapper;
    this.auditor = auditor;
  }

  @CommandListener(commandClasses = {SaveSwiftRegisterCommand.class})
  public void auditSwiftRegister(CommandEvent e) {
    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> saveSwiftRegister(SaveSwiftRegisterCommand command) {
    swiftRegisterRepository.save(swiftRegisterMapper.domainToEntity().map(command.getPayload()));
    return CommandResponse.asVoid();
  }
}
