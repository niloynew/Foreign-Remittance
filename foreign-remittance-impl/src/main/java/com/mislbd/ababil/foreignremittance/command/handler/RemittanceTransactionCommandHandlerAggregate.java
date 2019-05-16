package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.command.CreateInwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.CreateOutwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.AuditInformation;
import com.mislbd.ababil.foreignremittance.domain.BankInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.mapper.BankInformationMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceChargeInformationMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.BankInformationRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeInformationRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.BankInformationEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeInformationEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.service.salient.DisbursementService;
import com.mislbd.ababil.transaction.service.TransactionService;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.security.core.NgSession;
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
      ConfigurationService configurationService) {
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
    BigDecimal totalChargeAndVat =
        transaction.getTotalChargeAmount().add(transaction.getTotalVatAmount());
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
    BigDecimal totalChargeAndVat =
        transaction.getTotalChargeAmount().add(transaction.getTotalVatAmount());
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
}
