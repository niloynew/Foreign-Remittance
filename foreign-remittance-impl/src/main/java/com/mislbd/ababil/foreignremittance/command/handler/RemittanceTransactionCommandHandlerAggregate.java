package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.command.CreateInwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.CreateOutwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.CreateViewMT103FromRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.*;
import com.mislbd.ababil.foreignremittance.exception.BankTypeNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.BankInformationMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceChargeInformationMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.BankInformationRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeInformationRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.BankInformationEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeInformationEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.service.BankTypeService;
import com.mislbd.ababil.foreignremittance.service.salient.DisbursementService;
import com.mislbd.ababil.organization.service.BranchService;
import com.mislbd.ababil.transaction.service.TransactionService;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import com.mislbd.security.core.NgSession;
import com.mislbd.swift.broker.model.BankOperationCode;
import com.mislbd.swift.broker.model.DetailsOfCharges;
import com.mislbd.swift.broker.model.raw.SelectOptions;
import com.mislbd.swift.broker.model.raw.mt1xx.MT103MessageRequest;
import com.mislbd.swift.broker.service.SwiftMTMessageService;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class RemittanceTransactionCommandHandlerAggregate {

  private static final Long DISBURSEMENT_ACTIVITY_ID = 701L;
  private static final Long PAYMENT_ACTIVITY_ID = 702L;
  private static final String SYSTEM_EXCHANGE_RATE_TYPE = "SYSTEM_EXCHANGE_RATE_TYPE";
  private final RemittanceTransactionRepository transactionRepository;
  private final RemittanceTransactionMapper transactionMapper;
  private final BankInformationRepository bankInformationRepository;
  private final BankInformationMapper bankInformationMapper;
  private final RemittanceChargeInformationRepository chargeInformationRepository;
  private final RemittanceChargeInformationMapper chargeInformationMapper;
  private final NgSession ngSession;
  private final DisbursementService disbursementService;
  private final TransactionService transactionService;
  private final ConfigurationService configurationService;
  private BigDecimal totalChargeAmount = null;
  private final Auditor auditor;
  private final BranchService branchService;
  private final SwiftMTMessageService swiftMTMessageService;
  private final BankTypeService bankTypeService;
  private String serviceURL = "192.168.1.104:8087/swift-service";

  //  CalendarConfigurationService calendarConfigurationService;

  public RemittanceTransactionCommandHandlerAggregate(
      RemittanceTransactionRepository transactionRepository,
      RemittanceTransactionMapper transactionMapper,
      BankInformationRepository bankInformationRepository,
      BankInformationMapper bankInformationMapper,
      RemittanceChargeInformationRepository chargeInformationRepository,
      RemittanceChargeInformationMapper chargeInformationMapper,
      NgSession ngSession,
      DisbursementService disbursementService,
      TransactionService transactionService,
      ConfigurationService configurationService,
      Auditor auditor,
      BranchService branchService,
      SwiftMTMessageService swiftMTMessageService,
      BankTypeService bankTypeService) {

    this.transactionRepository = transactionRepository;
    this.transactionMapper = transactionMapper;
    this.bankInformationRepository = bankInformationRepository;
    this.bankInformationMapper = bankInformationMapper;
    this.chargeInformationRepository = chargeInformationRepository;
    this.chargeInformationMapper = chargeInformationMapper;
    this.ngSession = ngSession;
    this.disbursementService = disbursementService;
    this.transactionService = transactionService;
    this.configurationService = configurationService;
    this.auditor = auditor;
    this.branchService = branchService;
    this.swiftMTMessageService = swiftMTMessageService;
    this.bankTypeService = bankTypeService;
  }

  @CommandListener(
      commandClasses = {
        CreateInwardRemittanceTransactionCommand.class,
        CreateOutwardRemittanceTransactionCommand.class
      })
  public void auditCreateInwardRemittanceTransactionAndCreateInwardRemittanceTransaction(
      CommandEvent e) {

    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> createInwardRemittanceTransaction(
      CreateInwardRemittanceTransactionCommand command) {
    RemittanceTransaction transaction = command.getPayload();
    /*
     * Save entries in RemittanceTransaction table
     * Save entries in BankInformation table
     * Save charge information along with global transaction number
     * transaction processing
     * */

    AuditInformation auditInformation = getAuditInformation(command);

    RemittanceTransactionEntity remittanceTransactionEntity =
        saveTransactionEntity(transaction, DISBURSEMENT_ACTIVITY_ID, auditInformation);
    if (transaction.getTotalChargeAmountAfterWaived() == null) {
      totalChargeAmount = transaction.getTotalChargeAmount();
    } else {
      totalChargeAmount = transaction.getTotalChargeAmountAfterWaived();
    }
    BigDecimal totalChargeAndVat = totalChargeAmount.add(transaction.getTotalVatAmount());
    return CommandResponse.of(
        disbursementService.doInwardTransaction(
            remittanceTransactionEntity,
            auditInformation,
            transaction.getRemittanceChargeInformationList(),
            totalChargeAndVat));
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> createOutwardRemittanceTransaction(
      CreateOutwardRemittanceTransactionCommand command) {
    RemittanceTransaction transaction = command.getPayload();
    AuditInformation auditInformation = getAuditInformation(command);
    RemittanceTransactionEntity remittanceTransactionEntity =
        saveTransactionEntity(transaction, PAYMENT_ACTIVITY_ID, auditInformation);
    if (transaction.getTotalChargeAmountAfterWaived() == null) {
      totalChargeAmount = transaction.getTotalChargeAmount();
    } else {
      totalChargeAmount = transaction.getTotalChargeAmountAfterWaived();
    }
    BigDecimal totalChargeAndVat = totalChargeAmount.add(transaction.getTotalVatAmount());
    return CommandResponse.of(
        disbursementService.doOutwardTransaction(
            remittanceTransactionEntity,
            auditInformation,
            command.getPayload().getRemittanceChargeInformationList(),
            totalChargeAndVat));
  }

  private AuditInformation getAuditInformation(Command<RemittanceTransaction> command) {
    AuditInformation auditInformation = new AuditInformation();
    auditInformation
        .setEntryUser(command.getExecutedBy())
        .setVerifyUser(ngSession.getUsername())
        .setVerifyTerminal(ngSession.getTerminal())
        .setUserBranch(ngSession.getUserBranch().intValue())
        .setProcessId(command.getProcessId())
        .setEntryDate(LocalDate.now());
    return auditInformation;
  }

  private RemittanceTransactionEntity saveTransactionEntity(
      RemittanceTransaction domain, Long activityId, AuditInformation auditInformation) {

    RemittanceTransactionEntity remittanceTransactionEntity =
        transactionMapper.domainToEntity().map(domain);

    remittanceTransactionEntity
        .setBatchNumber(
            transactionService.getBatchNumber(
                auditInformation.getEntryUser(),
                activityId,
                auditInformation.getUserBranch().longValue()))
        .setGlobalTransactionNo(
            remittanceTransactionEntity.getGlobalTransactionNo() == null
                ? transactionService.getGlobalTransactionNumber(
                    auditInformation.getEntryUser(), activityId)
                : remittanceTransactionEntity.getGlobalTransactionNo())
        .setExchangeRate(
            transactionService.getSystemExchangeRate(remittanceTransactionEntity.getCurrencyCode()))
        .setExchangeRateType(
            Long.valueOf(
                configurationService.getConfiguration(SYSTEM_EXCHANGE_RATE_TYPE).get().getValue()));

    RemittanceTransactionEntity entity = transactionRepository.save(remittanceTransactionEntity);

    List<BankInformation> bankInformationList = domain.getBankInformation();
    if (!bankInformationList.isEmpty())
      bankInformationList.forEach(
          bankInformation -> {
            BankInformationEntity bankInformationEntity =
                bankInformationMapper.domainToEntity().map(bankInformation);
            bankInformationEntity.setRemittanceTransaction(entity);
            bankInformationRepository.save(bankInformationEntity);
          });

    List<RemittanceChargeInformation> chargeInformationList =
        domain.getRemittanceChargeInformationList();
    if (!chargeInformationList.isEmpty())
      chargeInformationList.forEach(
          chargeInformation -> {
            RemittanceChargeInformationEntity chargeInformationEntity =
                chargeInformationMapper.domainToEntity().map(chargeInformation);
            chargeInformationEntity.setRemittanceTransaction(entity);
            chargeInformationRepository.save(chargeInformationEntity);
          });

    return entity;
  }

  @Transactional
  @CommandHandler
  public CommandResponse<MT103MessageRequest> view103MessagefromRemittanceTransaction(
      CreateViewMT103FromRemittanceTransactionCommand command) {
    MT103MessageRequest mt103MessageRequest = mapTransactionToMessageRequest(command.getPayload());
    return CommandResponse.of(mt103MessageRequest);
  }

  private MT103MessageRequest mapTransactionToMessageRequest(
      RemittanceTransaction remittanceTransaction) {
    MT103MessageRequest request = new MT103MessageRequest();
    request.setSenderAddress(
        branchService.findBranch(ngSession.getUserBranch()).get().getSwiftCode());
    List<BankInformation> bankInformationList = remittanceTransaction.getBankInformation();
    for (BankInformation bankInformation : bankInformationList) {
      BankType bankType =
          bankTypeService
              .getBankType(bankInformation.getBankTypeId())
              .orElseThrow(BankTypeNotFoundException::new);
      switch (bankType.getCode()) {
        case "00":
          request.setReceiverAddress(bankInformation.getSwiftCode());
          break;
        case "51":
          request.setSendingInstituteIdentifierCode(bankInformation.getSwiftCode());
          break;
        case "52":
          request.setSelectedOrderingInstitutionOption(SelectOptions.OptionA);
          request.setOrderingInstitutionIdentifierCode(bankInformation.getSwiftCode());
          break;
        case "53":
          request.setSelectedSendersCorrespondentOption(SelectOptions.OptionA);
          request.setSendersCorrespondentIdentifierCode(bankInformation.getSwiftCode());
          break;
        case "54":
          request.setSelectedReceiversCorrespondentOption(SelectOptions.OptionA);
          request.setReceiversCorrespondentIdentifierCode(bankInformation.getSwiftCode());
          break;
        case "55":
          request.setSelectedThirdReimbursementInstitutionOption(SelectOptions.OptionA);
          request.setThirdReimbursementInstitutionIdentifierCode(bankInformation.getSwiftCode());
          break;
        case "56":
          request.setSelectedIntermediaryInstitutionOption(SelectOptions.OptionA);
          request.setIntermediaryInstitutionIdentifierCode(bankInformation.getSwiftCode());
          break;
        case "57":
          request.setSelectedAccountWithInstitutionOption(SelectOptions.OptionA);
          request.setAccountWithInstitutionIdentifierCode(bankInformation.getSwiftCode());
          break;
      }
    }

    request.setSendersReference(remittanceTransaction.getTransactionReferenceNumber());
    request.setBankOperationCode(String.valueOf(BankOperationCode.CRED));
    request.setInterbankSettlementValueDate(Date.valueOf(remittanceTransaction.getValueDate()));
    request.setInterbankSettlementCurrency(remittanceTransaction.getCurrencyCode());
    request.setInterbankSettlementAmount(remittanceTransaction.getAmountFcy());
    request.setInstructedCurrency(null);
    request.setInstructedAmount(null);
    request.setSelectedOrderingCustomerOption(SelectOptions.OptionK);
    request.setOrderingCustomerAccount(remittanceTransaction.getApplicantAccountNumber());
    request.setOrderingCustomerNameAndAddress(
        remittanceTransaction
            .getApplicant()
            .concat(System.lineSeparator())
            .concat(remittanceTransaction.getApplicantAddress()));
    request.setSelectedBeneficiaryCustomerOption(SelectOptions.NoLetterOption);
    request.setBeneficiaryCustomerAccount(remittanceTransaction.getBeneficiaryAccountNumber());
    request.setBeneficiaryCustomerNameAndAddress(
        remittanceTransaction
            .getBeneficiaryName()
            .concat(System.lineSeparator())
            .concat(remittanceTransaction.getBeneficiaryAddress()));
    request.setDetailsOfCharges(String.valueOf(DetailsOfCharges.OUR));
    request.setSenderToReceiverInformation("");
    return request;
  }
}
