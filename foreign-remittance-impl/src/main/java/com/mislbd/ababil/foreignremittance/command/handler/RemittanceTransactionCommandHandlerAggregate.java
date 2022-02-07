package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.CreateInwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.CreateOutwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.RemittanceTransactionCorrectionCommand;
import com.mislbd.ababil.foreignremittance.domain.*;
import com.mislbd.ababil.foreignremittance.mapper.BankInformationMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceChargeInformationMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.BankInformationRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeInformationRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeInformationEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.BankMappingEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.service.RemittanceTransactionService;
import com.mislbd.ababil.foreignremittance.service.salient.DisbursementService;
import com.mislbd.ababil.transaction.service.TransactionService;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import com.mislbd.security.core.NgSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
@Slf4j
public class RemittanceTransactionCommandHandlerAggregate {

  private static final Long ID_DISBURSEMENT_ACTIVITY_ID = 805L;
  private static final Long ID_PAYMENT_ACTIVITY_ID = 806L;
  private final RemittanceTransactionRepository transactionRepository;
  private final RemittanceTransactionService remittanceTransactionService;
  private final RemittanceTransactionMapper transactionMapper;
  private final BankInformationRepository bankInformationRepository;
  private final BankInformationMapper bankInformationMapper;
  private final RemittanceChargeInformationRepository chargeInformationRepository;
  private final RemittanceChargeInformationMapper chargeInformationMapper;
  private final NgSession ngSession;
  private final DisbursementService disbursementService;
  private final TransactionService transactionService;
  private final Auditor auditor;

  public RemittanceTransactionCommandHandlerAggregate(
      RemittanceTransactionRepository transactionRepository,
      RemittanceTransactionService remittanceTransactionService,
      RemittanceTransactionMapper transactionMapper,
      BankInformationRepository bankInformationRepository,
      BankInformationMapper bankInformationMapper,
      RemittanceChargeInformationRepository chargeInformationRepository,
      RemittanceChargeInformationMapper chargeInformationMapper,
      NgSession ngSession,
      DisbursementService disbursementService,
      TransactionService transactionService,
      Auditor auditor) {

    this.transactionRepository = transactionRepository;
    this.remittanceTransactionService = remittanceTransactionService;
    this.transactionMapper = transactionMapper;
    this.bankInformationRepository = bankInformationRepository;
    this.bankInformationMapper = bankInformationMapper;
    this.chargeInformationRepository = chargeInformationRepository;
    this.chargeInformationMapper = chargeInformationMapper;
    this.ngSession = ngSession;
    this.disbursementService = disbursementService;
    this.transactionService = transactionService;
    this.auditor = auditor;
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

    AuditInformation auditInformation = getAuditInformation(command, null, null);

    RemittanceTransactionEntity remittanceTransactionEntity =
        saveTransactionEntity(transaction, ID_DISBURSEMENT_ACTIVITY_ID, auditInformation);
    BigDecimal totalChargeAndVat =
        transaction
            .getTotalChargeAmountAfterWaived()
            .add(transaction.getTotalVatAmountAfterWaived());

    return CommandResponse.of(
        disbursementService.doInwardTransaction(
            remittanceTransactionEntity,
            auditInformation,
            transaction.getRemittanceChargeInformationList(),
            totalChargeAndVat,
            ID_DISBURSEMENT_ACTIVITY_ID));
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> createOutwardRemittanceTransaction(
      CreateOutwardRemittanceTransactionCommand command) {
    RemittanceTransaction transaction = command.getPayload();

    AuditInformation auditInformation = getAuditInformation(command, null, null);

    RemittanceTransactionEntity remittanceTransactionEntity =
        saveTransactionEntity(transaction, ID_PAYMENT_ACTIVITY_ID, auditInformation);

    BigDecimal totalChargeAndVat =
        transaction
            .getTotalChargeAmountAfterWaived()
            .add(transaction.getTotalVatAmountAfterWaived());
    return CommandResponse.of(
        disbursementService.doOutwardTransaction(
            remittanceTransactionEntity,
            auditInformation,
            transaction.getRemittanceChargeInformationList(),
            totalChargeAndVat,
            ID_PAYMENT_ACTIVITY_ID));
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> correctionRemittanceTransaction(
      RemittanceTransactionCorrectionCommand command) {

    AuditInformation auditInformation = getAuditInformation(null, command, command.getPayload());

    remittanceTransactionService.correctTransaction(auditInformation);

    return CommandResponse.of(command.getPayload());
  }

  private AuditInformation getAuditInformation(
      Command<RemittanceTransaction> transactionCommand,
      RemittanceTransactionCorrectionCommand correctionCommand,
      Long globalTransactionNumber) {
    AuditInformation auditInformation = new AuditInformation();
    if (correctionCommand != null) {
      auditInformation.setEntryUser(correctionCommand.getInitiator());
      auditInformation.setProcessId(correctionCommand.getProcessId());
      auditInformation.setGlobalTxnNumber(globalTransactionNumber);
    } else {
      auditInformation.setEntryUser(transactionCommand.getInitiator());
      auditInformation.setProcessId(transactionCommand.getProcessId());
    }
    auditInformation
        .setVerifyUser(ngSession.getUsername())
        .setVerifyTerminal(ngSession.getTerminal())
        .setUserBranch(ngSession.getUserBranch().intValue())
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
                : remittanceTransactionEntity.getGlobalTransactionNo());

    RemittanceTransactionEntity entity = transactionRepository.save(remittanceTransactionEntity);

    List<BankInformation> bankInformationList = domain.getBankInformation();
    if (!bankInformationList.isEmpty())
      bankInformationList.forEach(
          bankInformation -> {
            BankMappingEntity bankMappingEntity =
                bankInformationMapper.domainToEntity().map(bankInformation);
            bankMappingEntity.setRemittanceTransaction(entity);
            bankInformationRepository.save(bankMappingEntity);
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

// transactionRepository.delete(transaction);
    /*transaction.setValid(false);
    transactionRepository.save(transaction);
    ShadowTransactionRecordEntity shadowTransactionRecordEntity =
        shadowTransactionRecordRepository
            .findByGlobalTxnNo(BigDecimal.valueOf(command.getPayload()))
            .orElseThrow(RemittanceTransactionNotFoundException::new);
    shadowTransactionRecordEntity.setValid(false);
    shadowTransactionRecordRepository.save(shadowTransactionRecordEntity);
    RemittanceTransactionEntity transaction =
        transactionRepository
            .findByGlobalTransactionNo(command.getPayload())
            .orElseThrow(RemittanceTransactionNotFoundException::new);*/
