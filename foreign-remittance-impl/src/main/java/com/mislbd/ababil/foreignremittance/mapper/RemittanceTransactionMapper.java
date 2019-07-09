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
    IDTransactionRequest transactionRequest = new IDTransactionRequest();

    transactionRequest
        .setActivityId(activityId)
        .setAmountCcy(request.getAmountFcy() == null ? BigDecimal.ZERO : request.getAmountFcy())
        .setAmountLcy(hoAmountLcy)
        .setCurrencyCode(request.getCurrencyCode())
        .setExchangeRate(request.getHoRate())
        .setRateType(request.getHoRateTypeId())
        .setDebitTransaction(isDebit)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration(narration)
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorModule("ID")
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setAccNumber(isDebit ? request.getDebitAccountNumber() : request.getCreditAccountNumber());
    return transactionRequest;
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
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest
        .setActivityId(activityId)
        .setAmountLcy(clientAmount == null ? BigDecimal.ZERO : clientAmount)
        .setCurrencyCode(request.getCurrencyCode())
        .setExchangeRate(request.getClientRate())
        .setRateType(request.getExchangeRateType())
        .setDebitTransaction(isDebit)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setOwnerBranch(auditInformation.getUserBranch())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration(narration)
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorModule("ID")
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setGlCode(isDebit ? request.getDebitAccountNumber() : request.getCreditAccountNumber());
    return glRequest;
  }

  public CasaTransactionRequest getNetPayableCASAClientForForFcy(
      RemittanceTransactionEntity request, boolean isDebit, AuditInformation auditInformation) {
    String narration;
    if (isDebit) {
      narration = "Payment from A/C " + request.getCreditAccountNumber() + " for CASA ";
    } else {
      narration = "Disburse from A/C " + request.getCreditAccountNumber() + " for CASA ";
    }
    CasaTransactionRequest casaRequest = new CasaTransactionRequest();
    casaRequest
        .setInstrumentNo("V-")
        .setActivityId(activityId)
        .setAmountCcy(request.getAmountFcy() == null ? BigDecimal.ZERO : request.getAmountFcy())
        .setAmountLcy(request.getAmountFcy() == null ? BigDecimal.ZERO : request.getAmountFcy())
        .setCurrencyCode(request.getCurrencyCode())
        .setExchangeRate(request.getClientRate())
        .setRateType(request.getClientRateTypeId())
        .setDebitTransaction(isDebit)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration(narration)
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setInitiatorModule("ID")
        .setVerifyUser(auditInformation.getVerifyUser())
        .setAccNumber(isDebit ? request.getDebitAccountNumber() : request.getCreditAccountNumber());
    return casaRequest;
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
    CasaTransactionRequest casaRequest = new CasaTransactionRequest();
    casaRequest
        .setInstrumentNo("V-")
        .setActivityId(activityId)
        .setAmountCcy(clientAmount)
        .setAmountLcy(clientAmount)
        .setCurrencyCode(baseCurrency)
        .setExchangeRate(BigDecimal.ONE)
        .setRateType(1)
        .setRateType(request.getClientRateTypeId())
        .setDebitTransaction(isDebit)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration(narration)
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setInitiatorModule("ID")
        .setAccNumber(isDebit ? request.getDebitAccountNumber() : request.getCreditAccountNumber());
    return casaRequest;
  }

  public GlTransactionRequest getExchangeGainGL(
      RemittanceTransactionEntity request,
      String baseCurrency,
      String exchangeGainGLCode,
      AuditInformation auditInformation) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest
        .setActivityId(activityId)
        .setAmountCcy(
            request.getExchangeGainLoss() == null ? BigDecimal.ZERO : request.getExchangeGainLoss())
        .setAmountLcy(
            request.getExchangeGainLoss() == null ? BigDecimal.ZERO : request.getExchangeGainLoss())
        .setCurrencyCode(baseCurrency)
        .setExchangeRate(BigDecimal.ONE)
        .setRateType(1)
        .setDebitTransaction(false)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setOwnerBranch(auditInformation.getUserBranch())
        .setNarration("Exchange gain")
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorModule("ID")
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setGlCode(exchangeGainGLCode);
    return glRequest;
  }

  public GlTransactionRequest getChargeableGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    if (charge.getChargeAmountAfterWaived() == null) {
      chargeAmount = charge.getChargeAmount();
    } else {
      chargeAmount = charge.getChargeAmountAfterWaived();
    }
    glRequest
        .setActivityId(activityId)
        .setAmountCcy(chargeAmount)
        .setAmountLcy(chargeAmount)
        .setCurrencyCode(charge.getCurrency())
        .setExchangeRate(charge.getExchangeRate())
        .setRateType(1)
        .setDebitTransaction(false)
        .setOwnerBranch(auditInformation.getUserBranch())
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration(charge.getChargeName() + " from A/C " + request.getChargeAccountNumber())
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorModule("ID")
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setGlCode(charge.getChargeAccountCode());
    return glRequest;
  }

  public SubGlTransactionRequest getChargeableSubGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge) {
    SubGlTransactionRequest subGLRequest = new SubGlTransactionRequest();
    if (charge.getChargeAmountAfterWaived() == null) {
      chargeAmount = charge.getChargeAmount();
    } else {
      chargeAmount = charge.getChargeAmountAfterWaived();
    }
    subGLRequest
        .setActivityId(activityId)
        .setAmountCcy(chargeAmount)
        .setAmountLcy(chargeAmount)
        .setCurrencyCode(charge.getCurrency())
        .setExchangeRate(charge.getExchangeRate())
        //                .setRateType(charge.getR)
        .setCurrencyCode(charge.getCurrency())
        .setDebitTransaction(false)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration(charge.getChargeName() + " from A/C " + request.getChargeAccountNumber())
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setInitiatorModule("ID")
        .setAccNumber(charge.getChargeAccountCode());

    return subGLRequest;
  }

  public GlTransactionRequest getVATGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge) {
    GlTransactionRequest glRequest = new GlTransactionRequest();

    glRequest
        .setActivityId(activityId)
        .setAmountCcy(charge.getVatAmount())
        .setAmountLcy(charge.getVatAmount())
        .setCurrencyCode(charge.getCurrency())
        .setExchangeRate(charge.getExchangeRate())
        .setRateType(1)
        .setCurrencyCode(charge.getCurrency())
        .setDebitTransaction(false)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration("VAT on charge from A/C " + request.getChargeAccountNumber())
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setOwnerBranch(auditInformation.getUserBranch())
        .setInitiatorModule("ID")
        .setGlCode(charge.getVatAccountCode());

    return glRequest;
  }

  public SubGlTransactionRequest getVALSubGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge) {
    SubGlTransactionRequest subGLRequest = new SubGlTransactionRequest();
    subGLRequest
        .setActivityId(activityId)
        .setAmountCcy(charge.getVatAmount())
        .setAmountLcy(charge.getVatAmount())
        .setCurrencyCode(charge.getCurrency())
        .setExchangeRate(charge.getExchangeRate())
        //                .setRateType()
        .setCurrencyCode(charge.getCurrency())
        .setDebitTransaction(false)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration("VAT on charge from A/C " + request.getChargeAccountNumber())
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setInitiatorModule("ID")
        .setAccNumber(charge.getVatAccountCode());

    return subGLRequest;
  }

  public CasaTransactionRequest getChargeableCASADebit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      BigDecimal totalCharges,
      String baseCurrency) {
    CasaTransactionRequest casaRequest = new CasaTransactionRequest();
    casaRequest
        .setInstrumentNo("V-")
        .setActivityId(activityId)
        .setAmountCcy(totalCharges)
        .setAmountLcy(totalCharges)
        .setCurrencyCode(baseCurrency)
        .setExchangeRate(request.getExchangeRate())
        .setRateType(request.getExchangeRateType())
        .setDebitTransaction(true)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration("Charge deducted from A/C " + request.getChargeAccountNumber())
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setInitiatorModule("ID")
        .setAccNumber(request.getChargeAccountNumber());
    return casaRequest;
  }

  public GlTransactionRequest getChargeableGLDebit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      BigDecimal totalCharges,
      String baseCurrency) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest
        .setActivityId(activityId)
        .setAmountCcy(totalCharges)
        .setAmountLcy(totalCharges)
        .setExchangeRate(BigDecimal.ONE)
        .setRateType(1)
        .setCurrencyCode(baseCurrency)
        .setDebitTransaction(true)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration("Charge deducted from A/C " + request.getChargeAccountNumber())
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setOwnerBranch(auditInformation.getUserBranch())
        .setInitiatorModule("ID")
        .setGlCode(request.getChargeAccountNumber());
    return glRequest;
  }
}
