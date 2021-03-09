package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.*;
import com.mislbd.ababil.foreignremittance.domain.SwiftRegister;
import com.mislbd.ababil.foreignremittance.exception.SwiftRegisterNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.SwiftRegisterMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.SwiftRegisterRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.SwiftRegisterEntity;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class SwiftRegisterCommandHandlerAggregate {
  private SwiftRegisterRepository swiftRegisterRepository;
  private SwiftRegisterMapper swiftRegisterMapper;
  private final Auditor auditor;

  public SwiftRegisterCommandHandlerAggregate(
      SwiftRegisterRepository swiftRegisterRepository,
      SwiftRegisterMapper swiftRegisterMapper,
      Auditor auditor) {
    this.swiftRegisterRepository = swiftRegisterRepository;
    this.swiftRegisterMapper = swiftRegisterMapper;
    this.auditor = auditor;
  }

  @CommandListener(
      commandClasses = {SaveSwiftRegisterCommand.class, UpdateSwiftRegisterCommand.class})
  public void auditSwiftRegister(CommandEvent e) {
    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> saveSwiftRegister(SaveSwiftRegisterCommand command) {
    swiftRegisterRepository.save(swiftRegisterMapper.domainToEntity().map(command.getPayload()));
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateRegister(UpdateSwiftRegisterCommand command) {
    SwiftRegister swiftRegister = command.getPayload();
    Optional<SwiftRegisterEntity> register =
        swiftRegisterRepository.findById(swiftRegister.getId());
    if (!register.isPresent()) {
      throw new SwiftRegisterNotFoundException();
    }
    return CommandResponse.asVoid();
  }
}
