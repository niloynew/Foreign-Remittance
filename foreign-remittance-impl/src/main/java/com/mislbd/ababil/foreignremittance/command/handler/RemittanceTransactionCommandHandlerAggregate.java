package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.command.CreateInwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.CreateOutwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.*;
import com.mislbd.ababil.foreignremittance.mapper.BankInformationMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceChargeInformationMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.BankInformationRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeInformationRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeInformationEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionBankMappingEntity;
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
import com.mislbd.swift.broker.service.SwiftMTMessageService;
import java.math.BigDecimal;
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
  private BigDecimal totalVatAmount = null;
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
  public void auditCreateInwardRemittanceTransaction(CommandEvent e) {

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
    if (transaction.getTotalVatAmountAfterWaived() == null) {
      totalVatAmount = transaction.getTotalVatAmount();
    } else {
      totalVatAmount = transaction.getTotalVatAmountAfterWaived();
    }
    BigDecimal totalChargeAndVat = totalChargeAmount.add(totalVatAmount);
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

    if (transaction.getTotalVatAmountAfterWaived() == null) {
      totalVatAmount = transaction.getTotalVatAmount();
    } else {
      totalVatAmount = transaction.getTotalVatAmountAfterWaived();
    }

    BigDecimal totalChargeAndVat = totalChargeAmount.add(totalVatAmount);
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
        .setEntryUser(command.getInitiator())
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
            RemittanceTransactionBankMappingEntity remittanceTransactionBankMappingEntity =
                bankInformationMapper.domainToEntity().map(bankInformation);
            remittanceTransactionBankMappingEntity.setRemittanceTransaction(entity);
            bankInformationRepository.save(remittanceTransactionBankMappingEntity);
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
}
