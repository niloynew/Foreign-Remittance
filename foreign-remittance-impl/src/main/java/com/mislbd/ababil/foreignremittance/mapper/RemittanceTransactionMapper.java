package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.AuditInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionTypeRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.transaction.domain.CasaTransactionRequest;
import com.mislbd.ababil.transaction.domain.GlTransactionRequest;
import com.mislbd.ababil.transaction.domain.IDTransactionRequest;
import com.mislbd.ababil.transaction.domain.SubGlTransactionRequest;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class RemittanceTransactionMapper {

  private static final Long activityId = Long.valueOf(601);
  private final RemittanceTransactionRepository remittanceTransactionRepository;
  private final TransactionTypeRepository transactionTypeRepository;
  private final ShadowAccountRepository shadowAccountRepository;
  private BigDecimal chargeAmount = null;
  private BigDecimal totalChargeAmount = null;

  public RemittanceTransactionMapper(
      RemittanceTransactionRepository remittanceTransactionRepository,
      TransactionTypeRepository transactionTypeRepository,
      ShadowAccountRepository shadowAccountRepository) {
    this.remittanceTransactionRepository = remittanceTransactionRepository;
    this.transactionTypeRepository = transactionTypeRepository;
    this.shadowAccountRepository = shadowAccountRepository;
  }

  public ResultMapper<RemittanceTransactionEntity, RemittanceTransaction> entityToDomain() {
    return entity ->
        new RemittanceTransaction()
            .setId(entity.getId())
            .setRemittanceType(entity.getRemittanceType())
            .setTransactionTypeId(entity.getTransactionType().getId())
            //                .setPaymentPurposeId(entity.getPaymentPurposeId())
            .setPpCode(entity.getPpCode())
            .setCommodityDescription(entity.getCommodityDescription())
            .setTransactionReferenceNumber(entity.getTransactionReferenceNumber())
            .setInstrumentNumber(entity.getInstrumentNumber())
            .setCbFundSourceId(entity.getCbFundSourceId())
            .setDeliveryTerm(entity.getDeliveryTerm())
            .setApplicantId(entity.getApplicantId())
            .setApplicant(entity.getApplicant())
            .setApplicantAddress(entity.getApplicantAddress())
            .setApplicantAccountNumber(entity.getApplicantAccountNumber())
            .setValueDate(entity.getValueDate())
            .setBatchNumber(entity.getBatchNumber())
            .setBeneficiaryName(entity.getBeneficiaryName())
            .setBeneficiaryAccountNumber(entity.getBeneficiaryAccountNumber())
            .setBeneficiaryAddress(entity.getBeneficiaryAddress())
            .setB2bInformation(entity.getB2bInformation())
            .setDebitAccountNumber(entity.getDebitAccountNumber())
            .setCreditAccountNumber(entity.getCreditAccountNumber())
            .setChargeAccountNumber(entity.getChargeAccountNumber())
            .setCurrencyCode(entity.getCurrencyCode())
            .setClientRateTypeId(entity.getClientRateTypeId())
            .setClientRate(entity.getClientRate())
            .setHoRateTypeId(entity.getHoRateTypeId())
            .setHoRate(entity.getHoRate())
            .setAmountFcy(entity.getAmountFcy())
            .setAmountLcy(entity.getAmountLcy())
            .setExchangeGainLoss(entity.getExchangeGainLoss())
            .setGlobalTransactionNo(entity.getGlobalTransactionNo())
            .setTotalChargeAmount(entity.getTotalChargeAmount())
            .setTotalChargeAmountAfterWaived(entity.getTotalChargeAmountAfterWaived());
  }

  public ResultMapper<RemittanceTransaction, RemittanceTransactionEntity> domainToEntity() {
    return domain ->
        remittanceTransactionRepository
            .findById(Long.valueOf(domain.getId()))
            .orElseGet(RemittanceTransactionEntity::new)
            .setRemittanceType(domain.getRemittanceType())
            .setTransactionType(transactionTypeRepository.getOne(domain.getTransactionTypeId()))
            //            .setPaymentPurposeId(domain.getPaymentPurposeId())
            .setPpCode(domain.getPpCode())
            .setCommodityDescription(domain.getCommodityDescription())
            .setTransactionReferenceNumber(domain.getTransactionReferenceNumber())
            .setInstrumentNumber(domain.getInstrumentNumber())
            .setCbFundSourceId(domain.getCbFundSourceId())
            .setDeliveryTerm(domain.getDeliveryTerm())
            .setApplicantId(domain.getApplicantId())
            .setApplicant(domain.getApplicant())
            .setApplicantAddress(domain.getApplicantAddress())
            .setApplicantAccountNumber(domain.getApplicantAccountNumber())
            .setBeneficiaryName(domain.getBeneficiaryName())
            .setBeneficiaryAddress(domain.getBeneficiaryAddress())
            .setBeneficiaryAccountNumber(domain.getBeneficiaryAccountNumber())
            .setB2bInformation(domain.getB2bInformation())
            .setDebitAccountType(domain.getDebitAccountType())
            .setDebitAccountNumber(domain.getDebitAccountNumber())
            .setCreditAccountType(domain.getCreditAccountType())
            .setCreditAccountNumber(domain.getCreditAccountNumber())
            .setChargeAccountType(domain.getChargeAccountType())
            .setChargeAccountNumber(domain.getChargeAccountNumber())
            .setCurrencyCode(domain.getCurrencyCode())
            .setClientRateTypeId(domain.getClientRateTypeId())
            .setClientRate(domain.getClientRate())
            .setHoRateTypeId(domain.getHoRateTypeId())
            .setHoRate(domain.getHoRate())
            .setAmountFcy(domain.getAmountFcy())
            .setAmountLcy(domain.getAmountLcy())
            .setExchangeGainLoss(domain.getExchangeGainLoss())
            .setValueDate(domain.getValueDate())
            .setTotalChargeAmount(domain.getTotalChargeAmount())
            .setTotalChargeAmountAfterWaived(domain.getTotalChargeAmountAfterWaived());
  }

  public IDTransactionRequest getNetPayableShadow(
      RemittanceTransactionEntity request,
      BigDecimal hoAmountLcy,
      boolean isDebit,
      AuditInformation auditInformation) {
    String narration;
    if (isDebit) {
      narration = "Disburse from A/C " + request.getDebitAccountNumber() + " : shadow account";
    } else {
      narration = "Payment from A/C " + request.getCreditAccountNumber() + " : shadow account";
    }
    return IDTransactionRequest.builder()
        .activityId(activityId)
        .amountCcy(request.getAmountFcy() == null ? BigDecimal.ZERO : request.getAmountFcy())
        .amountLcy(hoAmountLcy)
        .currencyCode(request.getCurrencyCode())
        .exchangeRate(request.getHoRate())
        .rateType(request.getHoRateTypeId())
        .isDebitTransaction(isDebit)
        .batchNo(request.getBatchNumber())
        .globalTxnNo(request.getGlobalTransactionNo())
        .entryUser(auditInformation.getEntryUser())
        .entryTerminal(auditInformation.getEntryTerminal())
        .entryTime(auditInformation.getEntryDate())
        .verifyUser(auditInformation.getVerifyUser())
        .verifyTerminal(auditInformation.getVerifyTerminal())
        .narration(narration)
        .approvalFlowInstanceId(auditInformation.getProcessId())
        .initiatorModule("ID")
        .initiatorBranch(auditInformation.getUserBranch())
        .accNumber(isDebit ? request.getDebitAccountNumber() : request.getCreditAccountNumber())
        .build();
  }

  public GlTransactionRequest getNetPayableClientGL(
      RemittanceTransactionEntity request,
      BigDecimal clientAmount,
      String baseCurrency,
      boolean isDebit,
      AuditInformation auditInformation) {
    String narration;
    if (isDebit) {
      narration = "Payment from A/C " + request.getCreditAccountNumber() + " for GL ";
    } else {
      narration = "Disburse from A/C " + request.getDebitAccountNumber() + " for GL ";
    }
    return GlTransactionRequest.builder()
        .activityId(activityId)
        .amountLcy(clientAmount == null ? BigDecimal.ZERO : clientAmount)
        .currencyCode(request.getCurrencyCode())
        .exchangeRate(request.getClientRate())
        .rateType(request.getExchangeRateType())
        .isDebitTransaction(isDebit)
        .batchNo(request.getBatchNumber())
        .globalTxnNo(request.getGlobalTransactionNo())
        .ownerBranch(auditInformation.getUserBranch())
        .entryUser(auditInformation.getEntryUser())
        .entryTerminal(auditInformation.getEntryTerminal())
        .entryTime(auditInformation.getEntryDate())
        .verifyUser(auditInformation.getVerifyUser())
        .verifyTerminal(auditInformation.getVerifyTerminal())
        .narration(narration)
        .approvalFlowInstanceId(auditInformation.getProcessId())
        .initiatorModule("ID")
        .initiatorBranch(auditInformation.getUserBranch())
        .glCode(isDebit ? request.getDebitAccountNumber() : request.getCreditAccountNumber())
        .build();
  }

  public CasaTransactionRequest getNetPayableCASAClientForForFcy(
      RemittanceTransactionEntity request, boolean isDebit, AuditInformation auditInformation) {
    String narration;
    if (isDebit) {
      narration = "Payment from A/C " + request.getCreditAccountNumber() + " for CASA ";
    } else {
      narration = "Disburse from A/C " + request.getCreditAccountNumber() + " for CASA ";
    }
    return CasaTransactionRequest.builder()
        .instrumentNo("V-")
        .activityId(activityId)
        .amountCcy(request.getAmountFcy() == null ? BigDecimal.ZERO : request.getAmountFcy())
        .amountLcy(request.getAmountFcy() == null ? BigDecimal.ZERO : request.getAmountFcy())
        .currencyCode(request.getCurrencyCode())
        .exchangeRate(request.getClientRate())
        .rateType(request.getClientRateTypeId())
        .isDebitTransaction(isDebit)
        .batchNo(request.getBatchNumber())
        .globalTxnNo(request.getGlobalTransactionNo())
        .entryUser(auditInformation.getEntryUser())
        .entryTerminal(auditInformation.getEntryTerminal())
        .entryTime(auditInformation.getEntryDate())
        .verifyTerminal(auditInformation.getVerifyTerminal())
        .narration(narration)
        .approvalFlowInstanceId(auditInformation.getProcessId())
        .initiatorBranch(auditInformation.getUserBranch())
        .initiatorModule("ID")
        .verifyUser(auditInformation.getVerifyUser())
        .accNumber(isDebit ? request.getDebitAccountNumber() : request.getCreditAccountNumber())
        .build();
  }

  public CasaTransactionRequest getNetPayableCASAClientForForLcy(
      RemittanceTransactionEntity request,
      String baseCurrency,
      BigDecimal clientAmount,
      boolean isDebit,
      AuditInformation auditInformation) {
    String narration;
    if (isDebit) {
      narration = "Payment from A/C " + request.getCreditAccountNumber() + " for CASA ";
    } else {
      narration = "Disburse from A/C " + request.getCreditAccountNumber() + " for CASA ";
    }
    return CasaTransactionRequest.builder()
        .instrumentNo("V-")
        .activityId(activityId)
        .amountCcy(clientAmount)
        .amountLcy(clientAmount)
        .currencyCode(baseCurrency)
        .exchangeRate(BigDecimal.ONE)
        .rateType(1)
        .rateType(request.getClientRateTypeId())
        .isDebitTransaction(isDebit)
        .batchNo(request.getBatchNumber())
        .globalTxnNo(request.getGlobalTransactionNo())
        .entryUser(auditInformation.getEntryUser())
        .entryTerminal(auditInformation.getEntryTerminal())
        .entryTime(auditInformation.getEntryDate())
        .verifyUser(auditInformation.getVerifyUser())
        .verifyTerminal(auditInformation.getVerifyTerminal())
        .narration(narration)
        .approvalFlowInstanceId(auditInformation.getProcessId())
        .initiatorBranch(auditInformation.getUserBranch())
        .initiatorModule("ID")
        .accNumber(isDebit ? request.getDebitAccountNumber() : request.getCreditAccountNumber())
        .build();
  }

  public GlTransactionRequest getExchangeGainGL(
      RemittanceTransactionEntity request,
      String baseCurrency,
      String exchangeGainGLCode,
      AuditInformation auditInformation) {
    return GlTransactionRequest.builder()
        .activityId(activityId)
        .amountCcy(
            request.getExchangeGainLoss() == null ? BigDecimal.ZERO : request.getExchangeGainLoss())
        .amountLcy(
            request.getExchangeGainLoss() == null ? BigDecimal.ZERO : request.getExchangeGainLoss())
        .currencyCode(baseCurrency)
        .exchangeRate(BigDecimal.ONE)
        .rateType(1)
        .isDebitTransaction(false)
        .batchNo(request.getBatchNumber())
        .globalTxnNo(request.getGlobalTransactionNo())
        .entryUser(auditInformation.getEntryUser())
        .entryTerminal(auditInformation.getEntryTerminal())
        .entryTime(auditInformation.getEntryDate())
        .verifyUser(auditInformation.getVerifyUser())
        .verifyTerminal(auditInformation.getVerifyTerminal())
        .ownerBranch(auditInformation.getUserBranch())
        .narration("Exchange gain")
        .approvalFlowInstanceId(auditInformation.getProcessId())
        .initiatorModule("ID")
        .initiatorBranch(auditInformation.getUserBranch())
        .glCode(exchangeGainGLCode)
        .build();
  }

  public GlTransactionRequest getChargeableGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge) {

    if (charge.getChargeAmountAfterWaived() == null) {
      chargeAmount = charge.getChargeAmount();
    } else {
      chargeAmount = charge.getChargeAmountAfterWaived();
    }
    return GlTransactionRequest.builder()
        .activityId(activityId)
        .amountCcy(chargeAmount)
        .amountLcy(chargeAmount)
        .currencyCode(charge.getCurrency())
        .exchangeRate(charge.getExchangeRate())
        .rateType(1)
        .isDebitTransaction(false)
        .ownerBranch(auditInformation.getUserBranch())
        .batchNo(request.getBatchNumber())
        .globalTxnNo(request.getGlobalTransactionNo())
        .entryUser(auditInformation.getEntryUser())
        .entryTerminal(auditInformation.getEntryTerminal())
        .entryTime(auditInformation.getEntryDate())
        .verifyUser(auditInformation.getVerifyUser())
        .verifyTerminal(auditInformation.getVerifyTerminal())
        .narration(charge.getChargeName() + " from A/C " + request.getChargeAccountNumber())
        .approvalFlowInstanceId(auditInformation.getProcessId())
        .initiatorModule("ID")
        .initiatorBranch(auditInformation.getUserBranch())
        .glCode(charge.getChargeAccountCode())
        .build();
  }

  public SubGlTransactionRequest getChargeableSubGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge) {

    if (charge.getChargeAmountAfterWaived() == null) {
      chargeAmount = charge.getChargeAmount();
    } else {
      chargeAmount = charge.getChargeAmountAfterWaived();
    }
    return SubGlTransactionRequest.builder()
        .activityId(activityId)
        .amountCcy(chargeAmount)
        .amountLcy(chargeAmount)
        .currencyCode(charge.getCurrency())
        .exchangeRate(charge.getExchangeRate())
        //                .setRateType(charge.getR)
        .currencyCode(charge.getCurrency())
        .isDebitTransaction(false)
        .batchNo(request.getBatchNumber())
        .globalTxnNo(request.getGlobalTransactionNo())
        .entryUser(auditInformation.getEntryUser())
        .entryTerminal(auditInformation.getEntryTerminal())
        .entryTime(auditInformation.getEntryDate())
        .verifyUser(auditInformation.getVerifyUser())
        .verifyTerminal(auditInformation.getVerifyTerminal())
        .narration(charge.getChargeName() + " from A/C " + request.getChargeAccountNumber())
        .approvalFlowInstanceId(auditInformation.getProcessId())
        .initiatorBranch(auditInformation.getUserBranch())
        .initiatorModule("ID")
        .accNumber(charge.getChargeAccountCode())
        .build();
  }

  public GlTransactionRequest getVATGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge) {
    return GlTransactionRequest.builder()
        .activityId(activityId)
        .amountCcy(charge.getVatAmount())
        .amountLcy(charge.getVatAmount())
        .currencyCode(charge.getCurrency())
        .exchangeRate(charge.getExchangeRate())
        .rateType(1)
        .currencyCode(charge.getCurrency())
        .isDebitTransaction(false)
        .batchNo(request.getBatchNumber())
        .globalTxnNo(request.getGlobalTransactionNo())
        .entryUser(auditInformation.getEntryUser())
        .entryTerminal(auditInformation.getEntryTerminal())
        .entryTime(auditInformation.getEntryDate())
        .verifyUser(auditInformation.getVerifyUser())
        .verifyTerminal(auditInformation.getVerifyTerminal())
        .narration("VAT on charge from A/C " + request.getChargeAccountNumber())
        .approvalFlowInstanceId(auditInformation.getProcessId())
        .initiatorBranch(auditInformation.getUserBranch())
        .ownerBranch(auditInformation.getUserBranch())
        .initiatorModule("ID")
        .glCode(charge.getVatAccountCode())
        .build();
  }

  public SubGlTransactionRequest getVALSubGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge) {
    return SubGlTransactionRequest.builder()
        .activityId(activityId)
        .amountCcy(charge.getVatAmount())
        .amountLcy(charge.getVatAmount())
        .currencyCode(charge.getCurrency())
        .exchangeRate(charge.getExchangeRate())
        //                .setRateType()
        .currencyCode(charge.getCurrency())
        .isDebitTransaction(false)
        .batchNo(request.getBatchNumber())
        .globalTxnNo(request.getGlobalTransactionNo())
        .entryUser(auditInformation.getEntryUser())
        .entryTerminal(auditInformation.getEntryTerminal())
        .entryTime(auditInformation.getEntryDate())
        .verifyUser(auditInformation.getVerifyUser())
        .verifyTerminal(auditInformation.getVerifyTerminal())
        .narration("VAT on charge from A/C " + request.getChargeAccountNumber())
        .approvalFlowInstanceId(auditInformation.getProcessId())
        .initiatorBranch(auditInformation.getUserBranch())
        .initiatorModule("ID")
        .accNumber(charge.getVatAccountCode())
        .build();
  }

  public CasaTransactionRequest getChargeableCASADebit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      BigDecimal totalCharges,
      String baseCurrency) {

    return CasaTransactionRequest.builder()
        .instrumentNo("V-")
        .activityId(activityId)
        .amountCcy(totalCharges)
        .amountLcy(totalCharges)
        .currencyCode(baseCurrency)
        .exchangeRate(request.getExchangeRate())
        .rateType(request.getExchangeRateType())
        .isDebitTransaction(true)
        .batchNo(request.getBatchNumber())
        .globalTxnNo(request.getGlobalTransactionNo())
        .entryUser(auditInformation.getEntryUser())
        .entryTerminal(auditInformation.getEntryTerminal())
        .entryTime(auditInformation.getEntryDate())
        .verifyUser(auditInformation.getVerifyUser())
        .verifyTerminal(auditInformation.getVerifyTerminal())
        .narration("Charge deducted from A/C " + request.getChargeAccountNumber())
        .approvalFlowInstanceId(auditInformation.getProcessId())
        .initiatorBranch(auditInformation.getUserBranch())
        .initiatorModule("ID")
        .accNumber(request.getChargeAccountNumber())
        .build();
  }

  public GlTransactionRequest getChargeableGLDebit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      BigDecimal totalCharges,
      String baseCurrency) {
    return GlTransactionRequest.builder()
        .activityId(activityId)
        .amountCcy(totalCharges)
        .amountLcy(totalCharges)
        .exchangeRate(BigDecimal.ONE)
        .rateType(1)
        .currencyCode(baseCurrency)
        .isDebitTransaction(true)
        .batchNo(request.getBatchNumber())
        .globalTxnNo(request.getGlobalTransactionNo())
        .entryUser(auditInformation.getEntryUser())
        .entryTerminal(auditInformation.getEntryTerminal())
        .entryTime(auditInformation.getEntryDate())
        .verifyUser(auditInformation.getVerifyUser())
        .verifyTerminal(auditInformation.getVerifyTerminal())
        .narration("Charge deducted from A/C " + request.getChargeAccountNumber())
        .approvalFlowInstanceId(auditInformation.getProcessId())
        .initiatorBranch(auditInformation.getUserBranch())
        .ownerBranch(auditInformation.getUserBranch())
        .initiatorModule("ID")
        .glCode(request.getChargeAccountNumber())
        .build();
  }
}
