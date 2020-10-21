package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.CreatePublishSingleCustomerCreditTransferMessageCommand;
import com.mislbd.ababil.foreignremittance.command.CreateValidateSingleCustomerCreditTransferMessageCommand;
import com.mislbd.ababil.foreignremittance.command.ProcessNostroTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.UpdateNostroTransactionCommand;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroTransactionEntity;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import com.mislbd.swift.broker.model.MessageResponse;
import com.mislbd.swift.broker.model.ProcessResult;
import com.mislbd.swift.broker.model.raw.NostroAccountTransactionsDto;
import com.mislbd.swift.broker.model.raw.NostroTransaction;
import com.mislbd.swift.broker.model.raw.mt1xx.MT103MessageRequest;
import com.mislbd.swift.broker.service.SwiftMTMessageService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class SwiftMessageCommandHandlerAggregate {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SwiftMessageCommandHandlerAggregate.class);
  private final NostroTransactionRepository nostroTransactionRepository;
  private final ModelMapper modelMapper;
  private final Auditor auditor;
  private final SwiftMTMessageService swiftMTMessageService;
  private String serviceURL = "192.168.1.104:8087/swift-service";

  public SwiftMessageCommandHandlerAggregate(
      NostroTransactionRepository nostroTransactionRepository,
      ModelMapper modelMapper,
      Auditor auditor,
      SwiftMTMessageService swiftMTMessageService) {

    this.nostroTransactionRepository = nostroTransactionRepository;
    this.modelMapper = modelMapper;
    this.auditor = auditor;
    this.swiftMTMessageService = swiftMTMessageService;
  }

  @CommandListener(
      commandClasses = {
        UpdateNostroTransactionCommand.class,
        ProcessNostroTransactionCommand.class
      })
  public void auditNostroReconcile(CommandEvent e) {
    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateMessage(UpdateNostroTransactionCommand command) {
    nostroTransactionRepository.save(
        modelMapper.map(command.getPayload(), NostroTransactionEntity.class));
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Integer> processMessage(ProcessNostroTransactionCommand command) {
    NostroAccountTransactionsDto dtoList = command.getPayload();
    int success = 0;
    if (dtoList.getNostroAccountTransactionList() != null
        && !dtoList.getNostroAccountTransactionList().isEmpty()) {
      for (NostroTransaction dto : dtoList.getNostroAccountTransactionList()) {
        try {
          nostroTransactionRepository.save(modelMapper.map(dto, NostroTransactionEntity.class));
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
  public CommandResponse<ProcessResult> validate103Message(
      CreateValidateSingleCustomerCreditTransferMessageCommand command) {
    ProcessResult processResult= swiftMTMessageService.save103message(serviceURL, command.getPayload());
    return CommandResponse.of(processResult);
  }

    @Transactional
    @CommandHandler
    public CommandResponse<MessageResponse> generate103Message(
            CreateValidateSingleCustomerCreditTransferMessageCommand command) {
        MessageResponse messageResponse= swiftMTMessageService.generate103message(serviceURL, command.getPayload());
        return CommandResponse.of(messageResponse);
    }



    //modelMapper.map(command.getPayload(), MT103MessageRequest .class)


}
