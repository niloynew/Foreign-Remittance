package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.CreateSwiftMsgCommand;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class SwiftMesgCommandHandlerAggregate {
  private final SwiftMsgRepository swiftMsgRepository;
  private final RemittanceMsgDtoMapper mapper;

  public SwiftMesgCommandHandlerAggregate(
      SwiftMsgRepository swiftMsgRepository, RemittanceMsgDtoMapper mapper) {
    this.swiftMsgRepository = swiftMsgRepository;
    this.mapper = mapper;
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> createSwiftMsg(CreateSwiftMsgCommand command) {
    return CommandResponse.of(
        swiftMsgRepository.save(mapper.domainToEntity().map(command.getPayload())).getId());
  }
}
