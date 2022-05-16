package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.RejectShadowTransactionRecordCommand;
import com.mislbd.ababil.foreignremittance.command.SettleShadowTransactionRecordCommand;
import com.mislbd.ababil.foreignremittance.domain.OtherCbsSystemSettlementStatus;
import com.mislbd.ababil.foreignremittance.domain.ReconcileTxnLog;
import com.mislbd.ababil.foreignremittance.domain.ShadowTransactionRecord;
import com.mislbd.ababil.foreignremittance.exception.AccountNotFoundException;
import com.mislbd.ababil.foreignremittance.exception.ExternalModuleSettlementAccountNotFoundException;
import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
import com.mislbd.ababil.foreignremittance.mapper.ReconcileTxnLogMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ReconcileTxnLogRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowTransactionRecordRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowTransactionRecordEntity;
import com.mislbd.ababil.transaction.repository.jpa.ExternalModuleSettlementAccountRepository;
import com.mislbd.ababil.transaction.repository.schema.ExternalModuleSettlementAccountEntity;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import com.mislbd.ext.cbs.api.ExternalCBSService;
import com.mislbd.ext.cbs.api.TransactionRequest;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class TransactionReconcileCommandHandlerAggregate {

  private static final String EXTERNAL_MODULE_NAME = "EXT_CBS";
  private static final Logger LOGGER =
      LoggerFactory.getLogger(TransactionReconcileCommandHandlerAggregate.class);
  private final Auditor auditor;
  private final ExternalModuleSettlementAccountRepository settlementAccountRepository;
  private final ExternalCBSService externalCBSService;
  private final ShadowAccountRepository shadowAccountRepository;
  private final ShadowTransactionRecordRepository shadowTransactionRecordRepository;
  private final ReconcileTxnLogRepository reconcileTxnLogRepository;
  private final ReconcileTxnLogMapper reconcileTxnLogMapper;

  public TransactionReconcileCommandHandlerAggregate(
      Auditor auditor,
      ExternalModuleSettlementAccountRepository settlementAccountRepository,
      ExternalCBSService externalCBSService,
      ShadowAccountRepository shadowAccountRepository,
      ShadowTransactionRecordRepository shadowTransactionRecordRepository,
      ReconcileTxnLogRepository reconcileTxnLogRepository,
      ReconcileTxnLogMapper reconcileTxnLogMapper) {
    this.auditor = auditor;
    this.settlementAccountRepository = settlementAccountRepository;
    this.externalCBSService = externalCBSService;
    this.shadowAccountRepository = shadowAccountRepository;
    this.shadowTransactionRecordRepository = shadowTransactionRecordRepository;
    this.reconcileTxnLogRepository = reconcileTxnLogRepository;
    this.reconcileTxnLogMapper = reconcileTxnLogMapper;
  }

  @CommandListener(
      commandClasses = {
        SettleShadowTransactionRecordCommand.class,
        RejectShadowTransactionRecordCommand.class
      })
  public void auditSwiftRegister(CommandEvent e) {
    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Integer> reconcileTransactionRecords(
      SettleShadowTransactionRecordCommand command) {

    List<ShadowTransactionRecord> recordList = command.getPayload().getShadowTransactionRecords();
    int success = 0;
    if (recordList != null && !recordList.isEmpty()) {
      for (ShadowTransactionRecord x : recordList) {
        saveLog(command, x);
        if (x.getReconcileStatus() == OtherCbsSystemSettlementStatus.Settled
            || x.getReconcileStatus() == OtherCbsSystemSettlementStatus.Reject) continue;
        ShadowAccountEntity shadowAccount =
            shadowAccountRepository
                .findById(x.getAccountId())
                .orElseThrow(AccountNotFoundException::new);
        ExternalModuleSettlementAccountEntity settlementAccount =
            settlementAccountRepository
                .findByModuleTypeAndCurrencyCode(
                    EXTERNAL_MODULE_NAME, shadowAccount.getCurrencyCode())
                .orElseThrow(ExternalModuleSettlementAccountNotFoundException::new);
        ShadowTransactionRecordEntity transactionRecordEntity =
            shadowTransactionRecordRepository
                .findById(x.getId())
                .orElseThrow(
                    () ->
                        new ForeignRemittanceBaseException(
                            "Shadow Transaction record not found for id: " + x.getId()));
        BigDecimal txnAmount = x.getDebit();
        String debitAccount = shadowAccount.getNostroAccountNumber();
        String creditAccount = settlementAccount.getExternalAccount();
        if (Integer.valueOf(x.getTxnDefinitionId().toString().substring(0, 1)) == 1) {
          txnAmount = x.getCredit();
          debitAccount = settlementAccount.getExternalAccount();
          creditAccount = shadowAccount.getNostroAccountNumber();
        }
        try {
          externalCBSService.doTransaction(
              TransactionRequest.requestBuilder()
                  .debitAccount(debitAccount)
                  .creditAccount(creditAccount)
                  .currencyCode(shadowAccount.getCurrencyCode())
                  .remarks(
                      x.getTxnNarration() != null && x.getTxnNarration().length() > 30
                          ? x.getTxnNarration().substring(0, 30)
                          : x.getTxnNarration())
                  .transactionAmount(txnAmount.doubleValue())
                  .reference(String.valueOf(x.getGlobalTxnNo()))
                  .requestId(StringUtils.leftPad(String.valueOf(x.getGlobalTxnNo()), 12, "0"))
                  .build());
          shadowTransactionRecordRepository.save(
              transactionRecordEntity.setReconcileStatus(OtherCbsSystemSettlementStatus.Settled));
          success += 1;
        } catch (Exception e) {
          LOGGER.error(
              "Settlement failed for txnId: "
                  + x.getId()
                  + ", voucher no: "
                  + x.getGlobalTxnNo()
                  + ", amount: "
                  + txnAmount
                  + ", error : "
                  + e.getMessage());
        }
      }
    }
    return CommandResponse.of(success);
  }

  public void saveLog(SettleShadowTransactionRecordCommand command, ShadowTransactionRecord x) {
    ReconcileTxnLog reconcileTxnLog = new ReconcileTxnLog();
    reconcileTxnLog.setExecutedBy(command.getExecutedBy());
    reconcileTxnLog.setInitiator(command.getInitiator());
    reconcileTxnLog.setInitiatingTime(command.getInitiatingTime());
    reconcileTxnLog.setInitiatorBranch(command.getInitiatorBranch());
    reconcileTxnLog.setGlobalTxnNo(x.getGlobalTxnNo());
    reconcileTxnLog.setTxnNarration(x.getTxnNarration());
    reconcileTxnLog.setTxnDate(x.getTxnDate());
    reconcileTxnLog.setAccountNumber(x.getAccountNumber());
    reconcileTxnLogRepository.save(reconcileTxnLogMapper.domainToEntity().map(reconcileTxnLog));
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> rejectTransactionRecords(
      RejectShadowTransactionRecordCommand command) {
    ShadowTransactionRecordEntity transactionRecordEntity =
        shadowTransactionRecordRepository
            .findById(command.getId())
            .orElseThrow(
                () ->
                    new ForeignRemittanceBaseException(
                        "Shadow Transaction record not found for id: " + command.getId()));
    if (transactionRecordEntity.getReconcileStatus() == OtherCbsSystemSettlementStatus.Settled) {
      throw new ForeignRemittanceBaseException("Transaction already settled");
    }
    shadowTransactionRecordRepository.save(
        transactionRecordEntity.setReconcileStatus(OtherCbsSystemSettlementStatus.Reject));
    return CommandResponse.asVoid();
  }
}
