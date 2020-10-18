package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.CreatePublishSingleCustomerCreditTransferMessageCommand;
import com.mislbd.ababil.foreignremittance.command.CreateValidateSingleCustomerCreditTransferMessageCommand;
import com.mislbd.ababil.foreignremittance.command.ProcessNostroReconcileCommand;
import com.mislbd.ababil.foreignremittance.command.UpdateNostroReconcileCommand;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDtoBroker;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDtoBrokerList;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroReconcileRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroReconcileEntity;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import com.mislbd.swift.broker.service.SwiftMTMessageService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class SwiftMessageCommandHandlerAggregate {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SwiftMessageCommandHandlerAggregate.class);
  private final NostroReconcileRepository nostroReconcileRepository;
  private final ModelMapper modelMapper;
  private final Auditor auditor;
  private final SwiftMTMessageService swiftMTMessageService;
  private String serviceURL = "192.168.1.104:8087/swift-service";

  public SwiftMessageCommandHandlerAggregate(
      NostroReconcileRepository nostroReconcileRepository,
      ModelMapper modelMapper,
      Auditor auditor,
      SwiftMTMessageService swiftMTMessageService) {
    this.nostroReconcileRepository = nostroReconcileRepository;
    this.modelMapper = modelMapper;
    this.auditor = auditor;
    this.swiftMTMessageService = swiftMTMessageService;
  }

  @CommandListener(
      commandClasses = {UpdateNostroReconcileCommand.class, ProcessNostroReconcileCommand.class})
  public void auditNostroReconcile(CommandEvent e) {
    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateMessage(UpdateNostroReconcileCommand command) {
    nostroReconcileRepository.save(
        modelMapper.map(command.getPayload(), NostroReconcileEntity.class));
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Integer> processMessage(ProcessNostroReconcileCommand command) {
    NostroReconcileDtoBrokerList dtoList = command.getPayload();
    int success = 0;
    if (dtoList.getNostroReconcileDtoBrokerList() != null
        && !dtoList.getNostroReconcileDtoBrokerList().isEmpty()) {
      for (NostroReconcileDtoBroker dto : dtoList.getNostroReconcileDtoBrokerList()) {
        try {
          nostroReconcileRepository.save(modelMapper.map(dto, NostroReconcileEntity.class));
          success++;
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      LOGGER.info(success + " nostro reconcile messages saved.");
    }
    return CommandResponse.of(success);
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> publish103Message(
      CreatePublishSingleCustomerCreditTransferMessageCommand command) {
    swiftMTMessageService.publish103message(serviceURL, command.getPayload());
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> save103Message(
      CreateValidateSingleCustomerCreditTransferMessageCommand command) {
    swiftMTMessageService.save103message(serviceURL, command.getPayload());
    return CommandResponse.asVoid();
  }
}
