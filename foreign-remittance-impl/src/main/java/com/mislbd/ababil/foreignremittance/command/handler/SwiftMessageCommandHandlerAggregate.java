package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.command.*;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.exception.RemittanceTransactionNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroTransactionEntity;
import com.mislbd.ababil.foreignremittance.service.RemittanceTransactionService;
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
import com.mislbd.swift.broker.service.XmmIntegrationService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class SwiftMessageCommandHandlerAggregate {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SwiftMessageCommandHandlerAggregate.class);
  private final ShadowTransactionRepository shadowTransactionRepository;
  private final RemittanceTransactionRepository remittanceTransactionRepository;
  private final ModelMapper modelMapper;
  private final RemittanceTransactionMapper remittanceTransactionMapper;
  private final Auditor auditor;
  private final CommandProcessor commandProcessor;
  private final SwiftMTMessageService swiftMTMessageService;
  private final SwiftRegisterService swiftRegisterService;
  private final ConfigurationService configurationService;
  private final XmmIntegrationService xmmIntegrationService;
  private final RemittanceTransactionService remittanceTransactionService;
  private String serviceURL = "http://192.168.1.104:8087/swift-service";

  public SwiftMessageCommandHandlerAggregate(
      ShadowTransactionRepository shadowTransactionRepository,
      RemittanceTransactionRepository remittanceTransactionRepository,
      ModelMapper modelMapper,
      RemittanceTransactionMapper remittanceTransactionMapper,
      Auditor auditor,
      CommandProcessor commandProcessor,
      SwiftMTMessageService swiftMTMessageService,
      SwiftRegisterService swiftRegisterService,
      ConfigurationService configurationService,
      XmmIntegrationService xmmIntegrationService,
      RemittanceTransactionService remittanceTransactionService) {

    this.shadowTransactionRepository = shadowTransactionRepository;
    this.remittanceTransactionRepository = remittanceTransactionRepository;
    this.modelMapper = modelMapper;
    this.remittanceTransactionMapper = remittanceTransactionMapper;
    this.auditor = auditor;
    this.commandProcessor = commandProcessor;
    this.swiftMTMessageService = swiftMTMessageService;
    this.swiftRegisterService = swiftRegisterService;
    this.configurationService = configurationService;
    this.xmmIntegrationService = xmmIntegrationService;
    this.remittanceTransactionService = remittanceTransactionService;
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

    shadowTransactionRepository.save(nostroTransactionEntity);
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
          shadowTransactionRepository.save(nostroTransactionEntity);
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
    MT103MessageRequest request = command.getPayload();
    request.setEntryUser(String.valueOf(configurationService.getConfiguration("XMM_USER")));
    request.setEntryUserBranch(
        String.valueOf(configurationService.getConfiguration("XMM_USER_BRANCH")));
    request.setTransactionReferenceNumber(request.getSendersReference());
    request.setApplicationDate(configurationService.getCurrentApplicationDate());

    xmmIntegrationService.publishCategoryNMessage(request);
    RemittanceTransaction remittanceTransaction =
        remittanceTransactionService
            .findTransaction(request.getTransactionReferenceNumber())
            .orElseThrow(RemittanceTransactionNotFoundException::new);
    remittanceTransaction.setPublishedToXmm(true);
    remittanceTransactionRepository.save(
        remittanceTransactionMapper.domainToEntity().map(remittanceTransaction));
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
    MT103MessageRequest request = command.getPayload();
    request.setEntryUser(
        configurationService.getConfiguration("XMM_USER").get().getExpectedValue());
    request.setEntryUserBranch(
        configurationService.getConfiguration("XMM_USER_BRANCH").get().getExpectedValue());
    request.setTransactionReferenceNumber(request.getSendersReference());
    request.setApplicationDate(configurationService.getCurrentApplicationDate());
    MessageResponse messageResponse = xmmIntegrationService.publishCategoryNMessage(request);
    return CommandResponse.of(messageResponse);
  }
}







//    swiftMTMessageService.publish103message(serviceURL, command.getPayload());
// ToDo change when kafka integration complete
//    ProcessResult processResult =
//        swiftMTMessageService.save103message(serviceURL, command.getPayload());
    /*if (processResult.getErrorCode() == 0) {
        MessageResponse messageResponse =
                swiftMTMessageService.generate103message(serviceURL, command.getPayload());
        swiftRegisterService.registerMessage(
                request.getSendersReference(),
                request.getSenderAddress(),
                request.getReceiverAddress(),
                messageResponse.getMessage());
    }*/