package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.UpdateNostroReconcileCommand;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroReconcileRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroReconcileEntity;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class SwiftMesgCommandHandlerAggregate {

  private final NostroReconcileRepository nostroReconcileRepository;
  private final ModelMapper modelMapper;

  public SwiftMesgCommandHandlerAggregate(
      NostroReconcileRepository nostroReconcileRepository, ModelMapper modelMapper) {
    this.nostroReconcileRepository = nostroReconcileRepository;
    this.modelMapper = modelMapper;
  }

  //  private final SwiftMsgRepository swiftMsgRepository;
  //  private final RemittanceMsgDtoMapper mapper;
  //
  //  public SwiftMesgCommandHandlerAggregate(
  //      SwiftMsgRepository swiftMsgRepository, RemittanceMsgDtoMapper mapper) {
  //    this.swiftMsgRepository = swiftMsgRepository;
  //    this.mapper = mapper;
  //  }
  //
  //  @Transactional
  //  @CommandHandler
  //  public CommandResponse<Long> createSwiftMsg(CreateSwiftMsgCommand command) {
  //    return CommandResponse.of(
  //        swiftMsgRepository.save(mapper.domainToEntity().map(command.getPayload())).getId());
  //  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateMessage(UpdateNostroReconcileCommand command) {
    nostroReconcileRepository.save(
        modelMapper.map(command.getPayload(), NostroReconcileEntity.class));
    return CommandResponse.asVoid();
  }
}
