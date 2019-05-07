package com.mislbd.ababil.foreignremittance.service.salient;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.domain.AuditInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeInformation;
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

  public DisbursementService(
      TransactionService transactionService,
      RemittanceTransactionMapper remittanceTransactionMapper,
      ShadowAccountRepository shadowAccountRepository,
      ConfigurationService configurationService) {
    this.transactionService = transactionService;
    this.remittanceTransactionMapper = remittanceTransactionMapper;
    this.shadowAccountRepository = shadowAccountRepository;
    this.configurationService = configurationService;
  }

  public Long doTransaction(
      RemittanceTransactionEntity remittanceTransactionEntity,
      AuditInformation auditInformation,
      List<RemittanceChargeInformation> charges) {
    String baseCurrency = configurationService.getBaseCurrencyCode();

    /*
     * Debit the principle amount to the respective GL defined in account product mapping
     * Credit the principle amount to the respective customer or GL account
     *
     * Find the total charge and vat
     * For each kind of charge, credit the charge account either on GL or SubGL defined in charge configuration
     * For each kind of charge, credit the vat amount either on GL or SubGL defined in charge configuration
     * From customer account or from GL account, debit the total vat and charge
     *
     * Debit the exchange gain to GL account defined in product definition
     * */

    // Managing Debit transaction
    ShadowAccountEntity shadowAccountEntity =
        shadowAccountRepository
            .findByNumber(remittanceTransactionEntity.getDebitAccountNumber())
            .get();

    BigDecimal hoAmountLcy =
        remittanceTransactionEntity
            .getAmountFcy()
            .multiply(remittanceTransactionEntity.getHoRate());
    transactionService.doIDTransaction(
        remittanceTransactionMapper.getNetPayableGLDebit(
            remittanceTransactionEntity, hoAmountLcy, auditInformation),
        TransactionRequestType.TRANSFER);

    // Managing Credit transaction
    BigDecimal clientAmountLcy =
        remittanceTransactionEntity
            .getAmountFcy()
            .multiply(remittanceTransactionEntity.getClientRate());
    switch (remittanceTransactionEntity.getCreditAccountType()) {
      case GL:
        transactionService.doGlTransaction(
            remittanceTransactionMapper.getNetPayableGLCredit(
                remittanceTransactionEntity, clientAmountLcy, baseCurrency, auditInformation),
            TransactionRequestType.TRANSFER);
        break;

      case CASA:
        transactionService.doCasaTransaction(
            remittanceTransactionMapper.getNetPayableCASACredit(
                remittanceTransactionEntity, clientAmountLcy, auditInformation),
            TransactionRequestType.TRANSFER);
        break;
    }

    // manage exchange gain transaction
    transactionService.doGlTransaction(
        remittanceTransactionMapper.getExchangeGainGL(
            remittanceTransactionEntity,
            baseCurrency,
            shadowAccountEntity.getProduct().getExchangeGainGLCode(),
            auditInformation),
        TransactionRequestType.TRANSFER);

    // Managing charge transaction
    BigDecimal totalCharge = BigDecimal.ZERO;
    BigDecimal totalVat = BigDecimal.ZERO;

    if (charges != null && !charges.isEmpty()) {

      for (RemittanceChargeInformation charge : charges) {
        totalCharge = totalCharge.add(charge.getChargeAmount());
        totalVat = totalVat.add(charge.getVatAmount());
        // individual charge account Credit
        switch (charge.getChargeAccountType()) {
          case GL:
            transactionService.doGlTransaction(
                remittanceTransactionMapper.getChargeableGLCredit(
                    remittanceTransactionEntity, auditInformation, charge),
                TransactionRequestType.TRANSFER);
            break;
          case SUBGL:
            transactionService.doSubGlTransaction(
                remittanceTransactionMapper.getChargeableSubGLCredit(
                    remittanceTransactionEntity, auditInformation, charge),
                TransactionRequestType.TRANSFER);
            break;
        }

        switch (charge.getVatAccountType()) {
          case GL:
            transactionService.doGlTransaction(
                remittanceTransactionMapper.getVATGLCredit(
                    remittanceTransactionEntity, auditInformation, charge),
                TransactionRequestType.TRANSFER);
            break;
          case SUBGL:
            transactionService.doSubGlTransaction(
                remittanceTransactionMapper.getVALSubGLCredit(
                    remittanceTransactionEntity, auditInformation, charge),
                TransactionRequestType.TRANSFER);
            break;
        }
      }
    }

    BigDecimal total = totalCharge.add(totalVat);
    // customer Account Debit
    switch (remittanceTransactionEntity.getChargeAccountType()) {
      case GL:
        transactionService.doGlTransaction(
            remittanceTransactionMapper.getChargeableGLDebit(
                remittanceTransactionEntity, auditInformation, total, baseCurrency),
            TransactionRequestType.TRANSFER);
        break;
      case CASA:
        transactionService.doCasaTransaction(
            remittanceTransactionMapper.getChargeableCASADebit(
                remittanceTransactionEntity, auditInformation, total, baseCurrency),
            TransactionRequestType.TRANSFER);
        break;
    }

    return remittanceTransactionEntity.getGlobalTransactionNo();
  }
}
