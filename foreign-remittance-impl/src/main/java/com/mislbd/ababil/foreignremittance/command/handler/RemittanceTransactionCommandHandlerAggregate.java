package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.command.SaveRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.AuditInformation;
import com.mislbd.ababil.foreignremittance.domain.BankInformation;
import com.mislbd.ababil.foreignremittance.mapper.BankInformationMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.BankInformationRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.BankInformationEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.service.DisbursementService;
import com.mislbd.ababil.transaction.service.TransactionService;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;

import java.time.LocalDate;
import java.util.List;

import com.mislbd.security.core.NgSession;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class RemittanceTransactionCommandHandlerAggregate {

    private static final Long DISBURSEMENT_ACTIVITY_ID = 501L;
    private static final String SYSTEM_EXCHANGE_RATE_TYPE = "SYSTEM_EXCHANGE_RATE_TYPE";
    private final RemittanceTransactionRepository transactionRepository;
    private final RemittanceTransactionMapper transactionMapper;
    private final BankInformationRepository bankInformationRepository;
    private final BankInformationMapper bankInformationMapper;
    private final NgSession ngSession;
    private final DisbursementService disbursementService;
    private final TransactionService transactionService;
    private final ConfigurationService configurationService;

    public RemittanceTransactionCommandHandlerAggregate(
            RemittanceTransactionRepository transactionRepository,
            RemittanceTransactionMapper transactionMapper,
            BankInformationRepository bankInformationRepository,
            BankInformationMapper bankInformationMapper, NgSession ngSession, DisbursementService disbursementService, TransactionService transactionService, ConfigurationService configurationService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.bankInformationRepository = bankInformationRepository;
        this.bankInformationMapper = bankInformationMapper;
        this.ngSession = ngSession;
        this.disbursementService = disbursementService;
        this.transactionService = transactionService;
        this.configurationService = configurationService;
    }

    @Transactional
    @CommandHandler
    public CommandResponse<Long> remittanceTransactionEntry(
            SaveRemittanceTransactionCommand command) {

        /*
         * Save entries in RemittanceTransaction table
         * Save entries in BankInformation table
         * transaction begin for inward remittance
         * transaction from inward remittance charge by remittanceChargeInformationList
         * */

        AuditInformation auditInformation = new AuditInformation();
        auditInformation
                .setEntryUser(command.getExecutedBy())
                .setVerifyUser(ngSession.getUsername())
                .setVerifyTerminal(ngSession.getTerminal())
                .setUserBranch(ngSession.getUserBranch().intValue())
                .setProcessId(command.getProcessId())
                .setEntryDate(LocalDate.now());

        RemittanceTransactionEntity remittanceTransactionEntity = transactionMapper.domainToEntity().map(command.getPayload());
        remittanceTransactionEntity
                .setBatchNumber(transactionService.getBatchNumber(
                        auditInformation.getEntryUser(),
                        DISBURSEMENT_ACTIVITY_ID,
                        auditInformation.getUserBranch().longValue()))
                .setGlobalTransactionNo(
                        remittanceTransactionEntity.getGlobalTransactionNo() == null
                                ? transactionService.getGlobalTransactionNumber(command.getExecutedBy(), 501L)
                                : remittanceTransactionEntity.getGlobalTransactionNo())
        .setExchangeRate(transactionService.getSystemExchangeRate(remittanceTransactionEntity.getCurrencyCode()))
        .setExchangeRateType(Long.valueOf(configurationService.getConfiguration(SYSTEM_EXCHANGE_RATE_TYPE).get().getValue()));
        Long remittanceTxnId =
                transactionRepository
                        .save(remittanceTransactionEntity)
                        .getId();
        List<BankInformation> bankInformationList = command.getPayload().getBankInformation();
        if (!bankInformationList.isEmpty())
            bankInformationList.forEach(
                    bankInformation -> {
                        BankInformationEntity bankInformationEntity =
                                bankInformationMapper.domainToEntity().map(bankInformation);
                        bankInformationEntity.setRemittanceTransaction(
                                transactionRepository.getOne(remittanceTxnId));
                        bankInformationRepository.save(bankInformationEntity);
                    });

        return CommandResponse.of(disbursementService.doTransaction(remittanceTransactionEntity, auditInformation));
    }
}
