package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.*;
import com.mislbd.ababil.foreignremittance.mapper.SwiftRegisterMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroTransactionEntity;
import com.mislbd.ababil.foreignremittance.service.SwiftRegisterService;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandProcessor;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
  private final CommandProcessor commandProcessor;
  private final SwiftMTMessageService swiftMTMessageService;
  private final SwiftRegisterService swiftRegisterService;
  private String serviceURL = "http://192.168.1.104:8087/swift-service";

  public SwiftMessageCommandHandlerAggregate(
      NostroTransactionRepository nostroTransactionRepository,
      ModelMapper modelMapper,
      Auditor auditor,
      CommandProcessor commandProcessor,
      SwiftMTMessageService swiftMTMessageService,
      SwiftRegisterMapper swiftRegisterMapper,
      SwiftRegisterService swiftRegisterService) {

    this.nostroTransactionRepository = nostroTransactionRepository;
    this.modelMapper = modelMapper;
    this.auditor = auditor;
    this.commandProcessor = commandProcessor;
    this.swiftMTMessageService = swiftMTMessageService;
    this.swiftRegisterService = swiftRegisterService;
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

    NostroTransactionEntity nostroTransactionEntity =
        modelMapper.map(command.getPayload(), NostroTransactionEntity.class);
    LocalDate valueDate = convertToLocalDateViaInstant(command.getPayload().getValueDate());
    nostroTransactionEntity.setValueDate(valueDate);
    nostroTransactionRepository.save(nostroTransactionEntity);
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
          NostroTransactionEntity nostroTransactionEntity =
              modelMapper.map(dto, NostroTransactionEntity.class);
          nostroTransactionEntity.setSelected(true);
          nostroTransactionRepository.save(nostroTransactionEntity);
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
      PublishSingleCustomerCreditTransferMessageCommand command) {
    //    swiftMTMessageService.publish103message(serviceURL, command.getPayload());
    // ToDo change when kafka integration complete
    ProcessResult processResult =
        swiftMTMessageService.save103message(serviceURL, command.getPayload());
    MT103MessageRequest request = command.getPayload();
    if (processResult.getErrorCode() == 0) {
      MessageResponse messageResponse =
          swiftMTMessageService.generate103message(serviceURL, command.getPayload());
      swiftRegisterService.registerMessage(
          request.getSendersReference(),
          request.getSenderAddress(),
          request.getReceiverAddress(),
          messageResponse.getMessage());
    }
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<ProcessResult> save103Message(
      CreateSingleCustomerCreditTransferMessageCommand command) {
    ProcessResult processResult =
        swiftMTMessageService.save103message(serviceURL, command.getPayload());
    MT103MessageRequest request = command.getPayload();
    if (processResult.getErrorCode() == 0) {
      MessageResponse messageResponse =
          swiftMTMessageService.generate103message(serviceURL, command.getPayload());
      swiftRegisterService.registerMessage(
          request.getSendersReference(),
          request.getSenderAddress(),
          request.getReceiverAddress(),
          messageResponse.getMessage());
    }
    return CommandResponse.of(processResult);
  }

  @Transactional
  @CommandHandler
  public CommandResponse<MessageResponse> generate103Message(
      GenerateSingleCustomerCreditTransferMessageCommand command) {
    MessageResponse messageResponse =
        swiftMTMessageService.generate103message(serviceURL, command.getPayload());
    return CommandResponse.of(messageResponse);
  }

  public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }
}
