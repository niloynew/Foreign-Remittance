package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.RejectShadowTransactionRecordCommand;
import com.mislbd.ababil.foreignremittance.command.SettleShadowTransactionRecordCommand;
import com.mislbd.ababil.foreignremittance.domain.OtherCbsSystemSettlementStatus;
import com.mislbd.ababil.foreignremittance.domain.ShadowTransactionRecord;
import com.mislbd.ababil.foreignremittance.exception.AccountNotFoundException;
import com.mislbd.ababil.foreignremittance.exception.ExternalModuleSettlementAccountNotFoundException;
import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
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
import com.mislbd.cityservice.gateway.model.TransactionRequest;
import com.mislbd.cityservice.gateway.service.TheCityBankService;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class TransactionReconcileCommandHandlerAggregate {

  private static final String EXTERNAL_MODULE_NAME = "FINACLE";
  private static final Logger LOGGER =
      LoggerFactory.getLogger(TransactionReconcileCommandHandlerAggregate.class);
  private final Auditor auditor;
  private final ExternalModuleSettlementAccountRepository settlementAccountRepository;
  private final TheCityBankService theCityBankService;
  private final ShadowAccountRepository shadowAccountRepository;
  private final ShadowTransactionRecordRepository shadowTransactionRecordRepository;

  public TransactionReconcileCommandHandlerAggregate(
      Auditor auditor,
      ExternalModuleSettlementAccountRepository settlementAccountRepository,
      TheCityBankService theCityBankService,
      ShadowAccountRepository shadowAccountRepository,
      ShadowTransactionRecordRepository shadowTransactionRecordRepository) {
    this.auditor = auditor;
    this.settlementAccountRepository = settlementAccountRepository;
    this.theCityBankService = theCityBankService;
    this.shadowAccountRepository = shadowAccountRepository;
    this.shadowTransactionRecordRepository = shadowTransactionRecordRepository;
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
        if (x.getReconcileStatus() == OtherCbsSystemSettlementStatus.Settled) continue;
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
        shadowTransactionRecordRepository.save(
            transactionRecordEntity.setReconcileStatus(OtherCbsSystemSettlementStatus.Settled));
        BigDecimal txnAmount = x.getDebit();
        String debitAccount = shadowAccount.getNostroAccountNumber();
        String creditAccount = settlementAccount.getExternalAccount();
        if (Integer.valueOf(x.getTxnDefinitionId().toString().substring(0, 1)) == 1) {
          txnAmount = x.getCredit();
          debitAccount = settlementAccount.getExternalAccount();
          creditAccount = shadowAccount.getNostroAccountNumber();
        }
        try {
          theCityBankService.doTransaction(
              TransactionRequest.requestBuilder()
                  .debitAccount(debitAccount)
                  .creditAccount(creditAccount)
                  .currencyCode(shadowAccount.getCurrencyCode())
                  .remarks(
                      shadowAccount.getNostroAccountNumber()
                          + " : "
                          + x.getGlobalTxnNo()
                          + " ("
                          + EXTERNAL_MODULE_NAME
                          + " RECONCILE)")
                  .transactionAmount(txnAmount.doubleValue())
                  .reference(String.valueOf(x.getGlobalTxnNo()))
                  .requestId(StringUtils.leftPad(String.valueOf(x.getGlobalTxnNo()), 12, "0"))
                  .build());
          success += 1;
        } catch (Exception e) {
          shadowTransactionRecordRepository.save(
              transactionRecordEntity.setReconcileStatus(OtherCbsSystemSettlementStatus.Pending));
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
