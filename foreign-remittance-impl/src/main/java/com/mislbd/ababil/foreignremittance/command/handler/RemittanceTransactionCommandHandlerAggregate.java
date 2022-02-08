package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.CreateRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.RemittanceTransactionCorrectionCommand;
import com.mislbd.ababil.foreignremittance.domain.*;
import com.mislbd.ababil.foreignremittance.exception.RemittanceTransactionNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.service.TransactionRegisterService;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import com.mislbd.security.core.NgSession;


import com.mislbd.transaction.api.transaction.service.external.AbabilTransactionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
@Slf4j
public class RemittanceTransactionCommandHandlerAggregate {

  private final NgSession ngSession;
  private final Auditor auditor;
  private final RemittanceTransactionMapper transactionMapper;
  private final RemittanceTransactionRepository transactionRepository;
  private final AbabilTransactionClient transactionClient;
  private final TransactionRegisterService transactionRegisterService;

  public RemittanceTransactionCommandHandlerAggregate(NgSession ngSession, Auditor auditor, RemittanceTransactionMapper transactionMapper, RemittanceTransactionRepository transactionRepository, AbabilTransactionClient transactionClient, TransactionRegisterService transactionRegisterService) {
    this.ngSession = ngSession;
    this.auditor = auditor;
    this.transactionMapper = transactionMapper;
    this.transactionRepository = transactionRepository;
    this.transactionClient = transactionClient;
    this.transactionRegisterService = transactionRegisterService;
  }

  @CommandListener(
      commandClasses = {
        CreateRemittanceTransactionCommand.class
      })
  public void auditRemittanceTransaction(CommandEvent e) {
    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> createRemittanceTransaction(
      CreateRemittanceTransactionCommand command) {
    RemittanceTransaction transaction = command.getPayload();
    RemittanceTransactionEntity remittanceTransactionEntity =
            transactionMapper.domainToEntity().map(transaction);
    String globalTxnNumber = null;
    try {
      globalTxnNumber = transactionClient.doTransaction(transaction.getCbsTransactions());
      remittanceTransactionEntity.setTransactionStatus(RemittanceTransactionStatus.Succeed);
      saveTransactionEntity(remittanceTransactionEntity);
      transactionRegisterService.doRegister(transaction.getCbsTransactions(), remittanceTransactionEntity.getId());
    } catch (Exception e){
      log.error("Error in Feign transaction", e.getMessage());
      remittanceTransactionEntity.setTransactionStatus(RemittanceTransactionStatus.Failed);
      saveTransactionEntity(remittanceTransactionEntity);
    }
      return CommandResponse.of(Long.valueOf(globalTxnNumber));
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> correctionRemittanceTransaction(
      RemittanceTransactionCorrectionCommand command) {

    Long globalTxnNumber = command.getPayload();
    try{
      transactionClient.doCorrection(globalTxnNumber);
      transactionRegisterService.invalidRegister(globalTxnNumber);
      RemittanceTransactionEntity entity = transactionRepository.findById(command.getRemittanceTransactionId()).orElseThrow(RemittanceTransactionNotFoundException::new);
      entity.setTransactionStatus(RemittanceTransactionStatus.Reversed);
      saveTransactionEntity(entity);
    } catch (Exception e){
      log.error("Error in Feign reverse transaction", e.getMessage());
    }
    return CommandResponse.of(command.getPayload());
  }

  private void saveTransactionEntity(
      RemittanceTransactionEntity entity) {
    transactionRepository.save(entity);
  }
}