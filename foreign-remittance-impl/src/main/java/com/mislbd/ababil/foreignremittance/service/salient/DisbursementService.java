package com.mislbd.ababil.foreignremittance.service.salient;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.domain.AuditInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeInformation;
import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
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
      RemittanceTransactionEntity entity,
      AuditInformation auditInformation,
      List<RemittanceChargeInformation> charges,
      BigDecimal totalChargeAndVat,
      Long activityId) {
    String baseCurrency = configurationService.getBaseCurrencyCode();
    String narration =
        "Remit "
            + entity.getShadowAccountCurrency()
            + " "
            + entity.getAmountFcy()
            + " @ "
            + entity.getOperatingRate()
            + "/"
            + entity.getTransactionReferenceNumber();
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

    shadowAccountTransaction(entity, true, auditInformation, narration, activityId);
    switch (entity.getOperatingAccountType()) {
      case GL:
        transactionService.doGlTransaction(
            remittanceTransactionMapper.getNetPayableClientGL(
                entity, false, auditInformation, narration, activityId, baseCurrency),
            TransactionRequestType.TRANSFER);
        break;

      case CASA:
        transactionService.doCasaTransaction(
            remittanceTransactionMapper.getNetPayableCASAClient(
                entity, false, auditInformation, narration, activityId),
            TransactionRequestType.TRANSFER);
        break;
    }

    chargeTransaction(
        entity, charges, auditInformation, totalChargeAndVat, baseCurrency, activityId);
    checkDebitCredit(entity.getGlobalTransactionNo());
    return entity.getGlobalTransactionNo();
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
    String narration = "Remittance# " + remittanceTransactionEntity.getTransactionReferenceNumber();

    // Managing Credit transaction
    shadowAccountTransaction(
        remittanceTransactionEntity, false, auditInformation, narration, activityId);
    switch (remittanceTransactionEntity.getOperatingAccountType()) {
      case GL:
        transactionService.doGlTransaction(
            remittanceTransactionMapper.getNetPayableClientGL(
                remittanceTransactionEntity,
                true,
                auditInformation,
                narration,
                activityId,
                baseCurrency),
            TransactionRequestType.TRANSFER);
        break;

      case CASA:
        transactionService.doCasaTransaction(
            remittanceTransactionMapper.getNetPayableCASAClient(
                remittanceTransactionEntity, true, auditInformation, narration, activityId),
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
    checkDebitCredit(remittanceTransactionEntity.getGlobalTransactionNo());
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
        if (charge.getChargeAmountAfterWaived() != null
            && !charge.getChargeAmountAfterWaived().equals(BigDecimal.ZERO)) {
          transactionService.doGlTransaction(
              remittanceTransactionMapper.getChargeableGLCredit(
                  entity, auditInformation, charge, activityId),
              TransactionRequestType.TRANSFER);
        }

        if (charge.getVatAmountAfterWaived() != null
            && !charge.getVatAmountAfterWaived().equals(BigDecimal.ZERO)) {
          transactionService.doGlTransaction(
              remittanceTransactionMapper.getVATGLCredit(
                  entity, auditInformation, charge, activityId),
              TransactionRequestType.TRANSFER);
        }
      }
    }
    // customer Account Debit
    if (totalChargeAndVat != null && !totalChargeAndVat.equals(BigDecimal.ZERO)) {
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
                  entity, auditInformation, totalChargeAndVat, activityId, baseCurrency),
              TransactionRequestType.TRANSFER);
          break;
      }
    }
  }

  private void shadowAccountTransaction(
      RemittanceTransactionEntity entity,
      boolean isDebit,
      AuditInformation auditInformation,
      String narration,
      Long activityId) {
    transactionService.doIDTransaction(
        remittanceTransactionMapper.getNetPayableShadow(
            entity, isDebit, auditInformation, narration, activityId),
        TransactionRequestType.TRANSFER);
  }

  private void checkDebitCredit(Long globalTransactionNumber) {
    try {
      transactionService.isDebitCreditEqual(globalTransactionNumber);
    } catch (Exception e) {
      throw new ForeignRemittanceBaseException(e.getMessage());
    }
  }
}
