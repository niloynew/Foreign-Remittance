package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.command.*;
import com.mislbd.ababil.foreignremittance.domain.AdditionalInformation;
import com.mislbd.ababil.foreignremittance.domain.NostroTransactionRecord;
import com.mislbd.ababil.foreignremittance.domain.NostroTransactionRecordsDto;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.exception.RemittanceTransactionNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.AdditionInformationMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceAdditionalInformationRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.AdditionalInformationEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroTransactionRecordEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.service.RemittanceTransactionService;
import com.mislbd.ababil.foreignremittance.service.SwiftRegisterService;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import com.mislbd.security.core.NgSession;
import com.mislbd.swift.broker.model.MessageResponse;
import com.mislbd.swift.broker.model.ProcessResult;
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
  private final NostroTransactionRepository nostroTransactionRepository;
  private final RemittanceTransactionRepository remittanceTransactionRepository;
  private final RemittanceAdditionalInformationRepository remittanceAdditionalInformationRepository;
  private final ModelMapper modelMapper;
  private final RemittanceTransactionMapper remittanceTransactionMapper;
  private final AdditionInformationMapper additionInformationMapper;
  private final Auditor auditor;
  private final CommandProcessor commandProcessor;
  private final SwiftMTMessageService swiftMTMessageService;
  private final SwiftRegisterService swiftRegisterService;
  private final ConfigurationService configurationService;
  private final XmmIntegrationService xmmIntegrationService;
  private final RemittanceTransactionService remittanceTransactionService;
  private final NgSession ngSession;
  private String serviceURL = "http://192.168.1.104:8087/swift-service";

  public SwiftMessageCommandHandlerAggregate(
      NostroTransactionRepository nostroTransactionRepository,
      RemittanceTransactionRepository remittanceTransactionRepository,
      RemittanceAdditionalInformationRepository remittanceAdditionalInformationRepository,
      ModelMapper modelMapper,
      RemittanceTransactionMapper remittanceTransactionMapper,
      AdditionInformationMapper additionInformationMapper,
      Auditor auditor,
      CommandProcessor commandProcessor,
      SwiftMTMessageService swiftMTMessageService,
      SwiftRegisterService swiftRegisterService,
      ConfigurationService configurationService,
      XmmIntegrationService xmmIntegrationService,
      RemittanceTransactionService remittanceTransactionService,
      NgSession ngSession) {

    this.nostroTransactionRepository = nostroTransactionRepository;
    this.remittanceTransactionRepository = remittanceTransactionRepository;
    this.remittanceAdditionalInformationRepository = remittanceAdditionalInformationRepository;
    this.modelMapper = modelMapper;
    this.remittanceTransactionMapper = remittanceTransactionMapper;
    this.additionInformationMapper = additionInformationMapper;
    this.auditor = auditor;
    this.commandProcessor = commandProcessor;
    this.swiftMTMessageService = swiftMTMessageService;
    this.swiftRegisterService = swiftRegisterService;
    this.configurationService = configurationService;
    this.xmmIntegrationService = xmmIntegrationService;
    this.remittanceTransactionService = remittanceTransactionService;
    this.ngSession = ngSession;
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

    NostroTransactionRecordEntity nostroTransactionRecordEntity =
        modelMapper.map(command.getPayload(), NostroTransactionRecordEntity.class);

    nostroTransactionRepository.save(nostroTransactionRecordEntity);
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Integer> processMessage(ProcessNostroTransactionCommand command) {
    NostroTransactionRecordsDto dtoList = command.getPayload();
    int success = 0;
    if (dtoList.getNostroAccountTransactionRecordsList() != null
        && !dtoList.getNostroAccountTransactionRecordsList().isEmpty()) {
      for (NostroTransactionRecord dto : dtoList.getNostroAccountTransactionRecordsList()) {
        try {
          NostroTransactionRecordEntity nostroTransactionRecordEntity =
              modelMapper.map(dto, NostroTransactionRecordEntity.class);
          nostroTransactionRecordEntity.setSelected(true);
          nostroTransactionRepository.save(nostroTransactionRecordEntity);
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
  public CommandResponse<MessageResponse> publish103Message(
      PublishSingleCustomerCreditTransferMessageCommand command) {
    MT103MessageRequest request = command.getPayload();
    //    request.setEntryUser(
    //        configurationService.getConfiguration("XMM_USER").get().getExpectedValue());
    //    request.setEntryUserBranch(
    //        configurationService.getConfiguration("XMM_USER_BRANCH").get().getExpectedValue());
    request.setEntryUser(command.getInitiator());
    request.setEntryUserBranch(String.valueOf(command.getInitiatorBranch()).concat("00"));
    request.setTransactionReferenceNumber(request.getSendersReference());
    request.setApplicationDate(configurationService.getCurrentApplicationDate());
    MessageResponse messageResponse = xmmIntegrationService.publishCategoryNMessage(request);
    RemittanceTransaction remittanceTransaction =
        remittanceTransactionService
            .findTransaction(request.getTransactionReferenceNumber())
            .orElseThrow(RemittanceTransactionNotFoundException::new);
    if (messageResponse.getStatus().equalsIgnoreCase("200")) {
      //      remittanceTransaction.setPublishedToXmm(true);
      remittanceTransactionRepository.save(
          remittanceTransactionMapper.domainToEntity().map(remittanceTransaction));
      remittanceAdditionalInformationRepository.save(mapAdditionalInformation(request));
    }

    return CommandResponse.of(messageResponse);
  }

  @Transactional
  @CommandHandler
  public CommandResponse<MessageResponse> authorize103Message(
      AuthorizeSingleCustomerCreditTransferMessageCommand command) {
    String reference = command.getPayload();

    MessageResponse messageResponse = xmmIntegrationService.authorizeCategoryNMessage(reference);
    RemittanceTransaction remittanceTransaction =
        remittanceTransactionService
            .findTransaction(reference)
            .orElseThrow(RemittanceTransactionNotFoundException::new);
    if (messageResponse.getStatus().equalsIgnoreCase("200")) {
      //      remittanceTransaction.setAuthorizedToXmm(true);
      remittanceTransactionRepository.save(
          remittanceTransactionMapper.domainToEntity().map(remittanceTransaction));
      // remittanceAdditionalInformationRepository.save(mapAdditionalInformation(request));
    }

    return CommandResponse.of(messageResponse);
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
    request.setEntryUser(ngSession.getUsername());
    request.setEntryUserBranch(String.valueOf(ngSession.getUserBranch()).concat("00"));
    request.setTransactionReferenceNumber(request.getSendersReference());
    request.setApplicationDate(configurationService.getCurrentApplicationDate());
    MessageResponse messageResponse = xmmIntegrationService.publishCategoryNMessage(request);
    return CommandResponse.of(messageResponse);
  }

  public AdditionalInformationEntity mapAdditionalInformation(MT103MessageRequest request) {

    RemittanceTransactionEntity entity =
        remittanceTransactionRepository
            .findByTransactionReferenceNumber(request.getSendersReference())
            .orElseThrow(RemittanceTransactionNotFoundException::new);
    AdditionalInformation additionalInformation = new AdditionalInformation();
    if (entity.getAdditionalInformationEntity() != null) {
      additionalInformation.setId(entity.getAdditionalInformationEntity().getId());
    }
    if (request.getTimeIndications() != null && !request.getTimeIndications().isEmpty()) {
      additionalInformation
          .setTimeIndication(request.getTimeIndications().get(0).getTimeIndication())
          .setSign(request.getTimeIndications().get(0).getSign())
          .setOffset(request.getTimeIndications().get(0).getOffset());
    }
    additionalInformation
        .setInstructedAmount(request.getInstructedAmount())
        .setInstructedCurrency(request.getInstructedCurrency())
        .setExchangeRate(request.getExchangeRate())
        .setSendersChargeCurrency(request.getSendersChargeCurrency())
        .setSendersChargeAmount(request.getSendersChargeAmount())
        .setReceiversChargeCurrency(request.getReceiversChargeCurrency())
        .setReceiversChargeAmount(request.getSendersChargeAmount())
        .setTransactionTypeCode(request.getTransactionTypeCode())
        .setInstructionCode(request.getInstructionCode())
        .setInstructionCodeAdditionalInformation(request.getInstructionCodeAdditionalInformation())
        .setRegulatoryReportingCode(request.getRegulatoryReportingCode())
        .setRegulatoryReportingCountryCode(request.getRegulatoryReportingCountryCode())
        .setRegulatoryReportingCNarrative(request.getRegulatoryReportingCNarrative())
        .setEnvelopContents(request.getEnvelopeContents())
        .setRemittanceInformation(request.getRemittanceInformation())
        .setSenderToReceiverInformation(request.getSenderToReceiverInformation())
    //        .setSendingInstitutePartyIdentifier(request.getSendingInstitutePartyIdentifier())
    //        .setSendingInstituteIdentifierCode(request.getSendingInstituteIdentifierCode())
    //        .setSelectedOrderingInstitutionOption(request.getSelectedOrderingInstitutionOption())
    //
    // .setOrderingInstitutionPartyIdentifier(request.getOrderingInstitutionPartyIdentifier())
    //        .setOrderingInstitutionIdentifierCode(request.getOrderingInstitutionIdentifierCode())
    //        .setOrderingInstitutionPartyNameAndAddress(
    //            request.getOrderingInstitutionPartyNameAndAddress())
    //
    // .setSelectedSendersCorrespondentOption(request.getSelectedSendersCorrespondentOption())
    //
    // .setSendersCorrespondentPartyIdentifier(request.getSendersCorrespondentPartyIdentifier())
    //
    // .setSendersCorrespondentIdentifierCode(request.getSendersCorrespondentIdentifierCode())
    //        .setSendersCorrespondentLocation(request.getSendersCorrespondentLocation())
    //
    // .setSendersCorrespondentNameAndAddress(request.getSendersCorrespondentNameAndAddress())
    //
    // .setSelectedReceiversCorrespondentOption(request.getSelectedReceiversCorrespondentOption())
    //        .setReceiversCorrespondentPartyIdentifier(
    //            request.getReceiversCorrespondentPartyIdentifier())
    //
    // .setReceiversCorrespondentIdentifierCode(request.getReceiversCorrespondentIdentifierCode())
    //        .setReceiversCorrespondentLocation(request.getReceiversCorrespondentLocation())
    //
    // .setReceiversCorrespondentNameAndAddress(request.getReceiversCorrespondentNameAndAddress())
    //        .setSelectedThirdReimbursementInstitutionOption(
    //            request.getSelectedThirdReimbursementInstitutionOption())
    //        .setThirdReimbursementInstitutionPartyIdentifier(
    //            request.getThirdReimbursementInstitutionPartyIdentifier())
    //        .setThirdReimbursementInstitutionIdentifierCode(
    //            request.getThirdReimbursementInstitutionIdentifierCode())
    //        .setThirdReimbursementInstitutionLocation(
    //            request.getThirdReimbursementInstitutionLocation())
    //        .setThirdReimbursementInstitutionNameAndAddress(
    //            request.getThirdReimbursementInstitutionNameAndAddress())
    //        .setSelectedIntermediaryInstitutionOption(
    //            request.getSelectedIntermediaryInstitutionOption())
    //        .setIntermediaryInstitutionPartyIdentifier(
    //            request.getIntermediaryInstitutionPartyIdentifier())
    //        .setIntermediaryInstitutionIdentifierCode(
    //            request.getIntermediaryInstitutionIdentifierCode())
    //        .setIntermediaryInstitutionIdentifierNameAndAddress(
    //            request.getIntermediaryInstitutionIdentifierNameAndAddress())
    //
    // .setSelectedAccountWithInstitutionOption(request.getSelectedAccountWithInstitutionOption())
    //        .setAccountWithInstitutionPartyIdentifier(
    //            request.getAccountWithInstitutionPartyIdentifier())
    //
    // .setAccountWithInstitutionIdentifierCode(request.getAccountWithInstitutionIdentifierCode())
    //
    // .setAccountWithInstitutionPartyLocation(request.getAccountWithInstitutionPartyLocation())
    //        .setAccountWithInstitutionPartyNameAndAddress(
    //            request.getAccountWithInstitutionPartyNameAndAddress())
    ;
    AdditionalInformationEntity additionalInformationEntity =
        additionInformationMapper.domainToEntity().map(additionalInformation);
    additionalInformationEntity.setRemittanceTransactionEntity(entity);

    return additionalInformationEntity;
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
