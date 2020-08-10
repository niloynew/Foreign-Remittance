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

@SuppressWarnings("Duplicates")
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
      narration = "Disburse";
    } else {
      narration = "Payment";
    }
    IDTransactionRequest transactionRequest = new IDTransactionRequest();

    transactionRequest.setActivityId(activityId);
    transactionRequest.setAmountCcy(
        request.getAmountFcy() == null ? BigDecimal.ZERO : request.getAmountFcy());
    transactionRequest.setAmountLcy(hoAmountLcy);
    transactionRequest.setCurrencyCode(request.getCurrencyCode());
    transactionRequest.setExchangeRate(request.getHoRate());
    transactionRequest.setRateType(request.getHoRateTypeId());
    transactionRequest.setDebitTransaction(isDebit);
    transactionRequest.setBatchNo(request.getBatchNumber());
    transactionRequest.setGlobalTxnNo(request.getGlobalTransactionNo());
    transactionRequest.setEntryUser(auditInformation.getEntryUser());
    transactionRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    transactionRequest.setEntryTime(auditInformation.getEntryDate());
    transactionRequest.setVerifyUser(auditInformation.getVerifyUser());
    transactionRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    transactionRequest.setNarration(narration);
    transactionRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    transactionRequest.setInitiatorModule("ID");
    transactionRequest.setInitiatorBranch(auditInformation.getUserBranch());
    transactionRequest.setAccNumber(
        isDebit ? request.getDebitAccountNumber() : request.getCreditAccountNumber());
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
      narration = "Payment";
    } else {
      narration = "Disburse";
    }
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest.setActivityId(activityId);
    glRequest.setAmountLcy(clientAmount == null ? BigDecimal.ZERO : clientAmount);
    glRequest.setCurrencyCode(request.getCurrencyCode());
    glRequest.setExchangeRate(request.getClientRate());
    glRequest.setRateType(request.getExchangeRateType());
    glRequest.setDebitTransaction(isDebit);
    glRequest.setBatchNo(request.getBatchNumber());
    glRequest.setGlobalTxnNo(request.getGlobalTransactionNo());
    glRequest.setOwnerBranch(auditInformation.getUserBranch());
    glRequest.setEntryUser(auditInformation.getEntryUser());
    glRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    glRequest.setEntryTime(auditInformation.getEntryDate());
    glRequest.setVerifyUser(auditInformation.getVerifyUser());
    glRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    glRequest.setNarration(narration);
    glRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    glRequest.setInitiatorModule("ID");
    glRequest.setInitiatorBranch(auditInformation.getUserBranch());
    glRequest.setGlCode(
        isDebit ? request.getDebitAccountNumber() : request.getCreditAccountNumber());
    return glRequest;
  }

  public CasaTransactionRequest getNetPayableCASAClientForForFcy(
      RemittanceTransactionEntity request, boolean isDebit, AuditInformation auditInformation) {
    String narration;
    if (isDebit) {
      narration = "Payment";
    } else {
      narration = "Disburse";
    }
    CasaTransactionRequest casaRequest = new CasaTransactionRequest();
    casaRequest.setInstrumentNo("V-");
    casaRequest.setActivityId(activityId);
    casaRequest.setAmountCcy(
        request.getAmountFcy() == null ? BigDecimal.ZERO : request.getAmountFcy());
    casaRequest.setAmountLcy(
        request.getAmountFcy() == null ? BigDecimal.ZERO : request.getAmountFcy());
    casaRequest.setCurrencyCode(request.getCurrencyCode());
    casaRequest.setExchangeRate(request.getClientRate());
    casaRequest.setRateType(request.getClientRateTypeId());
    casaRequest.setDebitTransaction(isDebit);
    casaRequest.setBatchNo(request.getBatchNumber());
    casaRequest.setGlobalTxnNo(request.getGlobalTransactionNo());
    casaRequest.setEntryUser(auditInformation.getEntryUser());
    casaRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    casaRequest.setEntryTime(auditInformation.getEntryDate());
    casaRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    casaRequest.setNarration(narration);
    casaRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    casaRequest.setInitiatorBranch(auditInformation.getUserBranch());
    casaRequest.setInitiatorModule("ID");
    casaRequest.setVerifyUser(auditInformation.getVerifyUser());
    casaRequest.setAccNumber(
        isDebit ? request.getDebitAccountNumber() : request.getCreditAccountNumber());
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
      narration = "Payment";
    } else {
      narration = "Disburse";
    }
    CasaTransactionRequest casaRequest = new CasaTransactionRequest();
    casaRequest.setInstrumentNo("V-");
    casaRequest.setActivityId(activityId);
    casaRequest.setAmountCcy(clientAmount);
    casaRequest.setAmountLcy(clientAmount);
    casaRequest.setCurrencyCode(baseCurrency);
    casaRequest.setExchangeRate(BigDecimal.ONE);
    casaRequest.setRateType(1);
    casaRequest.setRateType(request.getClientRateTypeId());
    casaRequest.setDebitTransaction(isDebit);
    casaRequest.setBatchNo(request.getBatchNumber());
    casaRequest.setGlobalTxnNo(request.getGlobalTransactionNo());
    casaRequest.setEntryUser(auditInformation.getEntryUser());
    casaRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    casaRequest.setEntryTime(auditInformation.getEntryDate());
    casaRequest.setVerifyUser(auditInformation.getVerifyUser());
    casaRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    casaRequest.setNarration(narration);
    casaRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    casaRequest.setInitiatorBranch(auditInformation.getUserBranch());
    casaRequest.setInitiatorModule("ID");
    casaRequest.setAccNumber(
        isDebit ? request.getDebitAccountNumber() : request.getCreditAccountNumber());
    return casaRequest;
  }

  public GlTransactionRequest getExchangeGainGL(
      RemittanceTransactionEntity request,
      String baseCurrency,
      String exchangeGainGLCode,
      AuditInformation auditInformation) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest.setActivityId(activityId);
    glRequest.setAmountCcy(
        request.getExchangeGainLoss() == null ? BigDecimal.ZERO : request.getExchangeGainLoss());
    glRequest.setAmountLcy(
        request.getExchangeGainLoss() == null ? BigDecimal.ZERO : request.getExchangeGainLoss());
    glRequest.setCurrencyCode(baseCurrency);
    glRequest.setExchangeRate(BigDecimal.ONE);
    glRequest.setRateType(1);
    glRequest.setDebitTransaction(false);
    glRequest.setBatchNo(request.getBatchNumber());
    glRequest.setGlobalTxnNo(request.getGlobalTransactionNo());
    glRequest.setEntryUser(auditInformation.getEntryUser());
    glRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    glRequest.setEntryTime(auditInformation.getEntryDate());
    glRequest.setVerifyUser(auditInformation.getVerifyUser());
    glRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    glRequest.setOwnerBranch(auditInformation.getUserBranch());
    glRequest.setNarration("Exchange gain");
    glRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    glRequest.setInitiatorModule("ID");
    glRequest.setInitiatorBranch(auditInformation.getUserBranch());
    glRequest.setGlCode(exchangeGainGLCode);
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
    glRequest.setActivityId(activityId);
    glRequest.setAmountCcy(chargeAmount);
    glRequest.setAmountLcy(chargeAmount);
    glRequest.setCurrencyCode(charge.getCurrency());
    glRequest.setExchangeRate(BigDecimal.ONE);
    glRequest.setRateType(1);
    glRequest.setDebitTransaction(false);
    glRequest.setOwnerBranch(auditInformation.getUserBranch());
    glRequest.setBatchNo(request.getBatchNumber());
    glRequest.setGlobalTxnNo(request.getGlobalTransactionNo());
    glRequest.setEntryUser(auditInformation.getEntryUser());
    glRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    glRequest.setEntryTime(auditInformation.getEntryDate());
    glRequest.setVerifyUser(auditInformation.getVerifyUser());
    glRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    glRequest.setNarration(
        charge.getChargeName() + " from A/C " + request.getChargeAccountNumber());
    glRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    glRequest.setInitiatorModule("ID");
    glRequest.setInitiatorBranch(auditInformation.getUserBranch());
    glRequest.setGlCode(charge.getChargeAccountCode());
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
    subGLRequest.setActivityId(activityId);
    subGLRequest.setAmountCcy(chargeAmount);
    subGLRequest.setAmountLcy(chargeAmount);
    subGLRequest.setCurrencyCode(charge.getCurrency());
    subGLRequest.setExchangeRate(BigDecimal.ONE);
    subGLRequest.setRateType(1);
    subGLRequest.setCurrencyCode(charge.getCurrency());
    subGLRequest.setDebitTransaction(false);
    subGLRequest.setBatchNo(request.getBatchNumber());
    subGLRequest.setGlobalTxnNo(request.getGlobalTransactionNo());
    subGLRequest.setEntryUser(auditInformation.getEntryUser());
    subGLRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    subGLRequest.setEntryTime(auditInformation.getEntryDate());
    subGLRequest.setVerifyUser(auditInformation.getVerifyUser());
    subGLRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    subGLRequest.setNarration(
        charge.getChargeName() + " from A/C " + request.getChargeAccountNumber());
    subGLRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    subGLRequest.setInitiatorBranch(auditInformation.getUserBranch());
    subGLRequest.setInitiatorModule("ID");
    subGLRequest.setAccNumber(charge.getChargeAccountCode());

    return subGLRequest;
  }

  public GlTransactionRequest getVATGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge) {
    GlTransactionRequest glRequest = new GlTransactionRequest();

    glRequest.setActivityId(activityId);
    glRequest.setAmountCcy(charge.getVatAmount());
    glRequest.setAmountLcy(charge.getVatAmount());
    glRequest.setCurrencyCode(charge.getCurrency());
    glRequest.setExchangeRate(charge.getExchangeRate());
    glRequest.setRateType(1);
    glRequest.setCurrencyCode(charge.getCurrency());
    glRequest.setDebitTransaction(false);
    glRequest.setBatchNo(request.getBatchNumber());
    glRequest.setGlobalTxnNo(request.getGlobalTransactionNo());
    glRequest.setEntryUser(auditInformation.getEntryUser());
    glRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    glRequest.setEntryTime(auditInformation.getEntryDate());
    glRequest.setVerifyUser(auditInformation.getVerifyUser());
    glRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    glRequest.setNarration("VAT on charge from A/C " + request.getChargeAccountNumber());
    glRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    glRequest.setInitiatorBranch(auditInformation.getUserBranch());
    glRequest.setOwnerBranch(auditInformation.getUserBranch());
    glRequest.setInitiatorModule("ID");
    glRequest.setGlCode(charge.getVatAccountCode());

    return glRequest;
  }

  public SubGlTransactionRequest getVALSubGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge) {
    SubGlTransactionRequest subGLRequest = new SubGlTransactionRequest();

    subGLRequest.setActivityId(activityId);
    subGLRequest.setAmountCcy(charge.getVatAmount());
    subGLRequest.setAmountLcy(charge.getVatAmount());
    subGLRequest.setCurrencyCode(charge.getCurrency());
    subGLRequest.setExchangeRate(charge.getExchangeRate());
    //                .setRateType()
    subGLRequest.setCurrencyCode(charge.getCurrency());
    subGLRequest.setDebitTransaction(false);
    subGLRequest.setBatchNo(request.getBatchNumber());
    subGLRequest.setGlobalTxnNo(request.getGlobalTransactionNo());
    subGLRequest.setEntryUser(auditInformation.getEntryUser());
    subGLRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    subGLRequest.setEntryTime(auditInformation.getEntryDate());
    subGLRequest.setVerifyUser(auditInformation.getVerifyUser());
    subGLRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    subGLRequest.setNarration("VAT on charge from A/C " + request.getChargeAccountNumber());
    subGLRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    subGLRequest.setInitiatorBranch(auditInformation.getUserBranch());
    subGLRequest.setInitiatorModule("ID");
    subGLRequest.setAccNumber(charge.getVatAccountCode());

    return subGLRequest;
  }

  public CasaTransactionRequest getChargeableCASADebit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      BigDecimal totalCharges,
      String baseCurrency) {
    CasaTransactionRequest casaRequest = new CasaTransactionRequest();

    casaRequest.setInstrumentNo("V-");
    casaRequest.setAmountCcy(totalCharges);
    casaRequest.setAmountLcy(totalCharges);
    casaRequest.setCurrencyCode(baseCurrency);
    casaRequest.setExchangeRate(BigDecimal.ONE);
    casaRequest.setRateType(1);
    casaRequest.setDebitTransaction(true);
    casaRequest.setBatchNo(request.getBatchNumber());
    casaRequest.setGlobalTxnNo(request.getGlobalTransactionNo());
    casaRequest.setEntryUser(auditInformation.getEntryUser());
    casaRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    casaRequest.setEntryTime(auditInformation.getEntryDate());
    casaRequest.setVerifyUser(auditInformation.getVerifyUser());
    casaRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    casaRequest.setNarration("Charge deducted");
    casaRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    casaRequest.setInitiatorBranch(auditInformation.getUserBranch());
    casaRequest.setInitiatorModule("ID");
    casaRequest.setAccNumber(request.getChargeAccountNumber());
    return casaRequest;
  }

  public GlTransactionRequest getChargeableGLDebit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      BigDecimal totalCharges,
      String baseCurrency) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest.setActivityId(activityId);
    glRequest.setAmountCcy(totalCharges);
    glRequest.setAmountLcy(totalCharges);
    glRequest.setExchangeRate(BigDecimal.ONE);
    glRequest.setRateType(1);
    glRequest.setCurrencyCode(baseCurrency);
    glRequest.setDebitTransaction(true);
    glRequest.setBatchNo(request.getBatchNumber());
    glRequest.setGlobalTxnNo(request.getGlobalTransactionNo());
    glRequest.setEntryUser(auditInformation.getEntryUser());
    glRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    glRequest.setEntryTime(auditInformation.getEntryDate());
    glRequest.setVerifyUser(auditInformation.getVerifyUser());
    glRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    glRequest.setNarration("Charge deducted");
    glRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    glRequest.setInitiatorBranch(auditInformation.getUserBranch());
    glRequest.setOwnerBranch(auditInformation.getUserBranch());
    glRequest.setInitiatorModule("ID");
    glRequest.setGlCode(request.getChargeAccountNumber());
    return glRequest;
  }
}
