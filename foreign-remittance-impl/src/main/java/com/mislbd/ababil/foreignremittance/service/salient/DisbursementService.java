package com.mislbd.ababil.foreignremittance.service.salient;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.domain.AuditInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeInformation;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.transaction.domain.TransactionRequestType;
import com.mislbd.ababil.transaction.service.TransactionService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DisbursementService {

  private final TransactionService transactionService;
  private final RemittanceTransactionMapper remittanceTransactionMapper;
  private final ConfigurationService configurationService;

  public DisbursementService(
      TransactionService transactionService,
      RemittanceTransactionMapper remittanceTransactionMapper,
      ConfigurationService configurationService) {
    this.transactionService = transactionService;
    this.remittanceTransactionMapper = remittanceTransactionMapper;
    this.configurationService = configurationService;
  }

  public Long doInwardTransaction(
      RemittanceTransactionEntity remittanceTransactionEntity,
      AuditInformation auditInformation,
      List<RemittanceChargeInformation> charges,
      BigDecimal totalChargeAndVat,
      Long activityId) {
    String baseCurrency = configurationService.getBaseCurrencyCode();

    /*
     * Debit the principle amount to the respective GL
     * Credit the payable amount to the respective customer or GL account
     *
     * Find the total charge and vat
     * For each kind of charge, credit the charge account either on GL or SubGL defined in charge configuration
     * For each kind of charge, credit the vat amount either on GL or SubGL defined in charge configuration
     * From customer account or from GL account, debit the total vat and charge
     *
     * Debit the exchange gain to GL account defined in product definition
     * */

    shadowAccountTransaction(remittanceTransactionEntity, true, auditInformation, activityId);
    switch (remittanceTransactionEntity.getOperatingAccountType()) {
      case GL:
        transactionService.doGlTransaction(
            remittanceTransactionMapper.getNetPayableClientGL(
                remittanceTransactionEntity, baseCurrency, false, auditInformation, activityId),
            TransactionRequestType.TRANSFER);
        break;

      case CASA:
        transactionService.doCasaTransaction(
            remittanceTransactionMapper.getNetPayableCASAClient(
                remittanceTransactionEntity, false, auditInformation, activityId),
            TransactionRequestType.TRANSFER);
        break;
    }

    chargeTransaction(
        remittanceTransactionEntity,
        charges,
        auditInformation,
        totalChargeAndVat,
        baseCurrency,
        activityId);
    return remittanceTransactionEntity.getGlobalTransactionNo();
  }

  public Long doOutwardTransaction(
      RemittanceTransactionEntity remittanceTransactionEntity,
      AuditInformation auditInformation,
      List<RemittanceChargeInformation> charges,
      BigDecimal totalChargeAndVat,
      Long activityId) {

    /*
     * Credit the principle amount to the respective GL
     * Debit the payable amount to the respective customer or GL account
     *
     * Find the total charge and vat
     * For each kind of charge, credit the charge account either on GL or SubGL defined in charge configuration
     * For each kind of charge, credit the vat amount either on GL or SubGL defined in charge configuration
     * From customer account or from GL account, debit the total vat and charge
     *
     * Debit the exchange gain to GL account defined in product definition
     * */
    String baseCurrency = configurationService.getBaseCurrencyCode();

    // Managing Credit transaction
    shadowAccountTransaction(remittanceTransactionEntity, false, auditInformation, activityId);
    switch (remittanceTransactionEntity.getOperatingAccountType()) {
      case GL:
        transactionService.doGlTransaction(
            remittanceTransactionMapper.getNetPayableClientGL(
                remittanceTransactionEntity, baseCurrency, true, auditInformation, activityId),
            TransactionRequestType.TRANSFER);
        break;

      case CASA:
        transactionService.doCasaTransaction(
            remittanceTransactionMapper.getNetPayableCASAClient(
                remittanceTransactionEntity, true, auditInformation, activityId),
            TransactionRequestType.TRANSFER);
        break;
    }

    chargeTransaction(
        remittanceTransactionEntity,
        charges,
        auditInformation,
        totalChargeAndVat,
        baseCurrency,
        activityId);
    return remittanceTransactionEntity.getGlobalTransactionNo();
  }

  public void chargeTransaction(
      RemittanceTransactionEntity entity,
      List<RemittanceChargeInformation> charges,
      AuditInformation auditInformation,
      BigDecimal totalChargeAndVat,
      String baseCurrency,
      Long activityId) {

    if (charges != null && !charges.isEmpty()) {
      for (RemittanceChargeInformation charge : charges) {
        switch (charge.getChargeAccountType()) {
          case GL:
            transactionService.doGlTransaction(
                remittanceTransactionMapper.getChargeableGLCredit(
                    entity, auditInformation, charge, activityId),
                TransactionRequestType.TRANSFER);
            break;
          case SUBGL:
            transactionService.doSubGlTransaction(
                remittanceTransactionMapper.getChargeableSubGLCredit(
                    entity, auditInformation, charge, activityId),
                TransactionRequestType.TRANSFER);
            break;
        }

        switch (charge.getVatAccountType()) {
          case GL:
            transactionService.doGlTransaction(
                remittanceTransactionMapper.getVATGLCredit(
                    entity, auditInformation, charge, activityId),
                TransactionRequestType.TRANSFER);
            break;
          case SUBGL:
            transactionService.doSubGlTransaction(
                remittanceTransactionMapper.getVALSubGLCredit(
                    entity, auditInformation, charge, activityId),
                TransactionRequestType.TRANSFER);
            break;
        }
      }
    }
    // customer Account Debit
    switch (entity.getChargeAccountType()) {
      case GL:
        transactionService.doGlTransaction(
            remittanceTransactionMapper.getChargeableGLDebit(
                entity, auditInformation, totalChargeAndVat, baseCurrency, activityId),
            TransactionRequestType.TRANSFER);
        break;
      case CASA:
        transactionService.doCasaTransaction(
            remittanceTransactionMapper.getChargeableCASADebit(
                entity, auditInformation, totalChargeAndVat, baseCurrency),
            TransactionRequestType.TRANSFER);
        break;
    }
  }

  private void shadowAccountTransaction(
      RemittanceTransactionEntity entity,
      boolean isDebit,
      AuditInformation auditInformation,
      Long activityId) {
    transactionService.doIDTransaction(
        remittanceTransactionMapper.getNetPayableShadow(
            entity, isDebit, auditInformation, activityId),
        TransactionRequestType.TRANSFER);
  }
}
