package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.UpdateSwiftRegisterCommand;
import com.mislbd.ababil.foreignremittance.mapper.SwiftRegisterMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.SwiftRegisterRepository;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class UpdateSwiftRegisterCommandHandlerAggregate {
  private SwiftRegisterRepository swiftRegisterRepository;
  private SwiftRegisterMapper swiftRegisterMapper;
  private final Auditor auditor;

  public UpdateSwiftRegisterCommandHandlerAggregate(
      SwiftRegisterRepository swiftRegisterRepository,
      SwiftRegisterMapper swiftRegisterMapper,
      Auditor auditor) {
    this.swiftRegisterRepository = swiftRegisterRepository;
    this.swiftRegisterMapper = swiftRegisterMapper;
    this.auditor = auditor;
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateRegister(UpdateSwiftRegisterCommand command) {
    swiftRegisterRepository.save(swiftRegisterMapper.domainToEntity().map(command.getPayload()));
    return CommandResponse.asVoid();
  }
}
