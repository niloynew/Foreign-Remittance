package com.mislbd.ababil.foreignremittance.command.handler;

import com.google.common.base.Strings;
import com.mislbd.ababil.approvalflow.exception.OwnBranchNotFoundException;
import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.command.CreateRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.RemittanceTransactionCorrectionCommand;
import com.mislbd.ababil.foreignremittance.domain.*;
import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
import com.mislbd.ababil.foreignremittance.exception.RemittanceTransactionNotFoundException;
import com.mislbd.ababil.foreignremittance.external.domain.ApiTransactionRequest;
import com.mislbd.ababil.foreignremittance.external.domain.CorrectionRequest;
import com.mislbd.ababil.foreignremittance.external.mapper.ApiTransactionMapper;
import com.mislbd.ababil.foreignremittance.external.repository.BatchApiClient;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.service.TransactionRegisterService;
import com.mislbd.ababil.organization.service.BranchService;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
@Slf4j
public class RemittanceTransactionCommandHandlerAggregate {

    private final Auditor auditor;
    private final RemittanceTransactionMapper transactionMapper;
    private final RemittanceTransactionRepository transactionRepository;
    private final TransactionRegisterService transactionRegisterService;
    private final BatchApiClient batchApiClient;
    private final ApiTransactionMapper apiTransactionMapper;
    private final ConfigurationService configurationService;
    private final BranchService branchService;

    public RemittanceTransactionCommandHandlerAggregate(
            Auditor auditor,
            RemittanceTransactionMapper transactionMapper,
            RemittanceTransactionRepository transactionRepository,
            TransactionRegisterService transactionRegisterService,
            BatchApiClient batchApiClient,
            ApiTransactionMapper apiTransactionMapper,
            ConfigurationService configurationService, BranchService branchService) {
        this.auditor = auditor;
        this.transactionMapper = transactionMapper;
        this.transactionRepository = transactionRepository;
        this.transactionRegisterService = transactionRegisterService;
        this.batchApiClient = batchApiClient;
        this.apiTransactionMapper = apiTransactionMapper;
        this.configurationService = configurationService;
        this.branchService = branchService;
    }

    @CommandListener(commandClasses = {CreateRemittanceTransactionCommand.class})
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
        if (transaction.getSenderBIC() == null) {
            remittanceTransactionEntity.setSenderBIC(branchService.findBranch(command.getInitiatorBranch())
                    .orElseThrow(() -> new ForeignRemittanceBaseException("Branch not found with id " + command.getInitiatorBranch())).getSwiftCode());
        }
        Long voucherNumber = null;
        try {
            ApiTransactionRequest request =
                    apiTransactionMapper.cbsTransactionToApiRequest().map(transaction.getCbsTransactions());
            request.setRequestId(generateRequestId(transaction.getTransactionReferenceNumber()));
            request.setRequestDateTime(LocalDateTime.now());
            request.setValueDate(transaction.getValueDate());
            request.setInitiatorBranchId(command.getInitiatorBranch());
            request.setEntryUserTerminal(command.getInitiatorTerminal());
            request.setInitiatorModule("ID");
            request.setReferenceNumber(transaction.getTransactionReferenceNumber());
            request.setTransactionDate(configurationService.getCurrentApplicationDate());
            request.setVerifyUser(command.getVerifier());
            request.setVerifyUserTerminal(command.getVerifierTerminal());
            ResponseEntity<?> responseEntity = batchApiClient.doBatchApiTransaction(request);
            LinkedHashMap<String, Long> response = (LinkedHashMap<String, Long>) responseEntity.getBody();
            voucherNumber = response.get("content");
            remittanceTransactionEntity.setTransactionStatus(RemittanceTransactionStatus.Succeed);
            saveTransactionEntity(remittanceTransactionEntity);
            transactionRegisterService.doRegister(
                    transaction.getCbsTransactions(), voucherNumber, remittanceTransactionEntity.getId());
        } catch (Exception e) {
            log.error("Error in Feign transaction", e.getCause());
        }
        if (voucherNumber != null) {
            return CommandResponse.of(voucherNumber);
        } else {
            throw new ForeignRemittanceBaseException("Transaction Failed");
        }
    }

    @Transactional
    @CommandHandler
    public CommandResponse<Void> correctionRemittanceTransaction(
            RemittanceTransactionCorrectionCommand command) {
        boolean succeed = false;
        Long globalTxnNumber = command.getPayload();
        RemittanceTransactionEntity entity =
                transactionRepository
                        .findById(command.getRemittanceTransactionId())
                        .orElseThrow(RemittanceTransactionNotFoundException::new);
        try {
            CorrectionRequest request = new CorrectionRequest();
            request.setRequestId(generateRequestId(entity.getTransactionReferenceNumber()));
            request.setReferenceNumber(entity.getTransactionReferenceNumber());
            request.setVoucherNumber(String.valueOf(globalTxnNumber));
            batchApiClient.doApiTxnCorrection(request);
            transactionRegisterService.invalidRegister(globalTxnNumber);
            entity.setTransactionStatus(RemittanceTransactionStatus.Reversed);
            saveTransactionEntity(entity);
            succeed = true;
        } catch (Exception e) {
            log.error("Error in Feign reverse transaction", e.getMessage());
        }
        if (succeed) {
            return CommandResponse.asVoid();
        } else {
            throw new ForeignRemittanceBaseException("Reverse Transaction Failed");
        }
    }

    private void saveTransactionEntity(RemittanceTransactionEntity entity) {
        if (entity.getAdditionalInformationEntity() != null) {
            entity.getAdditionalInformationEntity().setRemittanceTransactionEntity(entity);
        }
        transactionRepository.save(entity);
    }

    private String generateRequestId(String referenceNumber) {
        String date = new SimpleDateFormat("ddMMyy").format(new Date());
        return date.concat(referenceNumber)
                .concat(
                        Strings.padStart(transactionRepository.generateRequestIdSequence().toString(), 3, '0'));
    }
}
