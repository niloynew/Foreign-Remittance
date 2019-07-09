package com.mislbd.ababil.foreignremittance.service.salient;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.domain.AuditInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeInformation;
import com.mislbd.ababil.foreignremittance.external.service.CASAAccountService;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.ababil.transaction.domain.TransactionRequestType;
import com.mislbd.ababil.transaction.service.TransactionService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DisbursementService {

  private final TransactionService transactionService;
  private final RemittanceTransactionMapper remittanceTransactionMapper;
  private final ShadowAccountRepository shadowAccountRepository;
  private final ConfigurationService configurationService;
  private final CASAAccountService casaAccountService;

  public DisbursementService(
      TransactionService transactionService,
      RemittanceTransactionMapper remittanceTransactionMapper,
      ShadowAccountRepository shadowAccountRepository,
      ConfigurationService configurationService,
      CASAAccountService casaAccountService) {
    this.transactionService = transactionService;
    this.remittanceTransactionMapper = remittanceTransactionMapper;
    this.shadowAccountRepository = shadowAccountRepository;
    this.configurationService = configurationService;
    this.casaAccountService = casaAccountService;
  }

  public Long doInwardTransaction(
      RemittanceTransactionEntity remittanceTransactionEntity,
      AuditInformation auditInformation,
      List<RemittanceChargeInformation> charges,
      BigDecimal totalChargeAndVat) {
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

    shadowAccountTransaction(remittanceTransactionEntity, true, auditInformation);
    BigDecimal clientAmountLcy =
        remittanceTransactionEntity
            .getAmountFcy()
            .multiply(remittanceTransactionEntity.getClientRate());
    switch (remittanceTransactionEntity.getCreditAccountType()) {
      case GL:
        transactionService.doGlTransaction(
            remittanceTransactionMapper.getNetPayableClientGL(
                remittanceTransactionEntity,
                clientAmountLcy,
                baseCurrency,
                false,
                auditInformation),
            TransactionRequestType.TRANSFER);
        break;

      case CASA:
        if (casaAccountService
            .getAccountByNumber(remittanceTransactionEntity.getCreditAccountNumber())
            .getCurrencyCode()
            .equalsIgnoreCase(baseCurrency)) {
          transactionService.doCasaTransaction(
              remittanceTransactionMapper.getNetPayableCASAClientForForLcy(
                  remittanceTransactionEntity,
                  baseCurrency,
                  clientAmountLcy,
                  false,
                  auditInformation),
              TransactionRequestType.TRANSFER);
        } else {
          transactionService.doCasaTransaction(
              remittanceTransactionMapper.getNetPayableCASAClientForForFcy(
                  remittanceTransactionEntity, false, auditInformation),
              TransactionRequestType.TRANSFER);
        }
        break;
    }

    exchangeGainTransaction(remittanceTransactionEntity, baseCurrency, true, auditInformation);
    chargeTransaction(
        remittanceTransactionEntity, charges, auditInformation, totalChargeAndVat, baseCurrency);
    return remittanceTransactionEntity.getGlobalTransactionNo();
  }

  public Long doOutwardTransaction(
      RemittanceTransactionEntity remittanceTransactionEntity,
      AuditInformation auditInformation,
      List<RemittanceChargeInformation> charges,
      BigDecimal totalChargeAndVat) {

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
    shadowAccountTransaction(remittanceTransactionEntity, false, auditInformation);
    BigDecimal clientAmountLcy =
        remittanceTransactionEntity
            .getAmountFcy()
            .multiply(remittanceTransactionEntity.getClientRate());
    switch (remittanceTransactionEntity.getDebitAccountType()) {
      case GL:
        transactionService.doGlTransaction(
            remittanceTransactionMapper.getNetPayableClientGL(
                remittanceTransactionEntity, clientAmountLcy, baseCurrency, true, auditInformation),
            TransactionRequestType.TRANSFER);
        break;

      case CASA:
        if (casaAccountService
            .getAccountByNumber(remittanceTransactionEntity.getDebitAccountNumber())
            .getCurrencyCode()
            .equalsIgnoreCase(baseCurrency)) {
          transactionService.doCasaTransaction(
              remittanceTransactionMapper.getNetPayableCASAClientForForLcy(
                  remittanceTransactionEntity,
                  baseCurrency,
                  clientAmountLcy,
                  true,
                  auditInformation),
              TransactionRequestType.TRANSFER);
        } else {
          transactionService.doCasaTransaction(
              remittanceTransactionMapper.getNetPayableCASAClientForForFcy(
                  remittanceTransactionEntity, true, auditInformation),
              TransactionRequestType.TRANSFER);
        }
        break;
    }
    exchangeGainTransaction(remittanceTransactionEntity, baseCurrency, false, auditInformation);
    chargeTransaction(
        remittanceTransactionEntity, charges, auditInformation, totalChargeAndVat, baseCurrency);
    return remittanceTransactionEntity.getGlobalTransactionNo();
  }

  public void exchangeGainTransaction(
      RemittanceTransactionEntity entity,
      String baseCurrency,
      boolean isDebit,
      AuditInformation auditInformation) {
    ShadowAccountEntity shadowAccountEntity;
    if (isDebit) {
      shadowAccountEntity =
          shadowAccountRepository.findByNumber(entity.getDebitAccountNumber()).get();
    } else {
      shadowAccountEntity =
          shadowAccountRepository.findByNumber(entity.getCreditAccountNumber()).get();
    }
    transactionService.doGlTransaction(
        remittanceTransactionMapper.getExchangeGainGL(
            entity,
            baseCurrency,
            shadowAccountEntity.getProduct().getExchangeGainGLCode(),
            auditInformation),
        TransactionRequestType.TRANSFER);
  }

  public void chargeTransaction(
      RemittanceTransactionEntity entity,
      List<RemittanceChargeInformation> charges,
      AuditInformation auditInformation,
      BigDecimal totalChargeAndVat,
      String baseCurrency) {

    if (charges != null && !charges.isEmpty()) {
      for (RemittanceChargeInformation charge : charges) {
        switch (charge.getChargeAccountType()) {
          case GL:
            transactionService.doGlTransaction(
                remittanceTransactionMapper.getChargeableGLCredit(entity, auditInformation, charge),
                TransactionRequestType.TRANSFER);
            break;
          case SUBGL:
            transactionService.doSubGlTransaction(
                remittanceTransactionMapper.getChargeableSubGLCredit(
                    entity, auditInformation, charge),
                TransactionRequestType.TRANSFER);
            break;
        }

        switch (charge.getVatAccountType()) {
          case GL:
            transactionService.doGlTransaction(
                remittanceTransactionMapper.getVATGLCredit(entity, auditInformation, charge),
                TransactionRequestType.TRANSFER);
            break;
          case SUBGL:
            transactionService.doSubGlTransaction(
                remittanceTransactionMapper.getVALSubGLCredit(entity, auditInformation, charge),
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
                entity, auditInformation, totalChargeAndVat, baseCurrency),
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

  public void shadowAccountTransaction(
      RemittanceTransactionEntity entity, boolean isDebit, AuditInformation auditInformation) {
    BigDecimal hoAmountLcy = entity.getAmountFcy().multiply(entity.getHoRate());
    transactionService.doIDTransaction(
        remittanceTransactionMapper.getNetPayableShadow(
            entity, hoAmountLcy, isDebit, auditInformation),
        TransactionRequestType.TRANSFER);
  }
}
