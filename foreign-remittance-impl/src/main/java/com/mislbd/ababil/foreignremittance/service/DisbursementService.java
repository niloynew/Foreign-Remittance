package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.AuditInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeInformation;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.mapper.ShadowAccountMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.ababil.transaction.domain.TransactionRequestType;
import com.mislbd.ababil.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisbursementService {

    private final TransactionService transactionService;
    private final RemittanceTransactionMapper remittanceTransactionMapper;
    private final ShadowAccountMapper shadowAccountMapper;
    private final ShadowAccountRepository shadowAccountRepository;

    public DisbursementService(TransactionService transactionService, RemittanceTransactionMapper remittanceTransactionMapper, ShadowAccountMapper shadowAccountMapper, ShadowAccountRepository shadowAccountRepository) {
        this.transactionService = transactionService;
        this.remittanceTransactionMapper = remittanceTransactionMapper;
        this.shadowAccountMapper = shadowAccountMapper;
        this.shadowAccountRepository = shadowAccountRepository;
    }


    public Long doTransaction(RemittanceTransactionEntity remittanceTransactionEntity, AuditInformation auditInformation, List<RemittanceChargeInformation> charges) {

        // Managing Debit transaction
        ShadowAccountEntity shadowAccountEntity = shadowAccountRepository.findByNumber(remittanceTransactionEntity.getDebitAccountNumber()).get();
        transactionService.doGlTransaction(remittanceTransactionMapper
                .getNetPayableGLDebit(
                        remittanceTransactionEntity,
                        shadowAccountEntity.getProduct().getProductGLCode(),
                        auditInformation),
                TransactionRequestType.TRANSFER);

        // Managing Credit transaction
        switch (remittanceTransactionEntity.getCreditAccountType()) {
            case GL:
                transactionService.doGlTransaction(remittanceTransactionMapper
                        .getNetPayableGLCredit(
                                remittanceTransactionEntity,
                                auditInformation),
                        TransactionRequestType.TRANSFER);
                break;

            case CASA:
                transactionService.doCasaTransaction(remittanceTransactionMapper
                        .getNetPayableCASACredit(
                                remittanceTransactionEntity,
                                auditInformation),
                        TransactionRequestType.TRANSFER);
                break;
        }
        // Managing charge transaction
        switch (remittanceTransactionEntity.getChargeAccountType()) {
            case GL:

                break;
            case CASA:

                break;
        }

        // manage exchange gain transaction
//        transactionService.doGlTransaction(remittanceTransactionMapper.getExchangeGainGL)

        return remittanceTransactionEntity.getGlobalTransactionNo();
    }

}
