package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.AuditInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionTypeRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.transaction.domain.CasaTransactionRequest;
import com.mislbd.ababil.transaction.domain.GlTransactionRequest;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class RemittanceTransactionMapper {

  private static final Long activityId = Long.valueOf(501);
  private final RemittanceTransactionRepository remittanceTransactionRepository;
  private final TransactionTypeRepository transactionTypeRepository;

  public RemittanceTransactionMapper(
      RemittanceTransactionRepository remittanceTransactionRepository,
      TransactionTypeRepository transactionTypeRepository) {
    this.remittanceTransactionRepository = remittanceTransactionRepository;
    this.transactionTypeRepository = transactionTypeRepository;
  }

  public ResultMapper<RemittanceTransactionEntity, RemittanceTransaction> entityToDomain() {
    return entity ->
        new RemittanceTransaction()
            .setId(entity.getId())
            //            .setTransactionTypeId(entity.getTransactionTypeId())
            //            .setRemittanceType(entity.getRemittanceType())
            //            .setOperationId(entity.getOperationId())
            //            .setPaymentPurposeId(entity.getPaymentPurposeId())
            //            .setCommodityDescription(entity.getCommodityDescription())
            //            .setTransactionReferenceNumber(entity.getTransactionReferenceNumber())
            //            .setInstructionNumber(entity.getInstructionNumber())
            //            .setCbFundSourceId(entity.getCbFundSourceId())
            //            .setDeliveryTerm(entity.getDeliveryTerm())
            //            .setApplicantId(entity.getApplicantId())
            //            .setApplicantAccountNumber(entity.getApplicantAccountNumber())
            //            .setBeneficiaryId(entity.getBeneficiaryId())
            //            .setBeneficiaryAccountNumber(entity.getBeneficiaryAccountNumber())
            //            .setB2bInformation(entity.getB2bInformation())
            //
            // .setBankInformation(getConvertedBankInformation(entity.getBankInformationEntity()))
            //            .setDebitAccountTypeId(entity.getDebitAccountType())
            //            .setDebitAccountNumber(entity.getDebitAccountNumber())
            //            .setCreditAccountTypeId(entity.getCreditAccountType())
            //            .setCreditAccountNumber(entity.getCreditAccountNumber())
            //            .setCurrencyCode(entity.getCurrencyCode())
            //            .setClientRateTypeId(entity.getClientRateTypeId())
            //            .setHoRateTypeId(entity.getHoRateTypeId())
            //            .setAmountFcy(entity.getAmountFcy())
            //            .setAmountLcy(entity.getAmountLcy())
            .setExchangeGainLoss(entity.getExchangeGainLoss());
  }

  public ResultMapper<RemittanceTransaction, RemittanceTransactionEntity> domainToEntity() {
    return domain ->
        remittanceTransactionRepository
            .findById(Long.valueOf(domain.getId()))
            .orElseGet(RemittanceTransactionEntity::new)
            .setTransactionType(transactionTypeRepository.getOne(domain.getTransactionTypeId()))
            .setPaymentPurposeId(domain.getPaymentPurposeId())
            .setCommodityDescription(domain.getCommodityDescription())
            .setTransactionReferenceNumber(domain.getTransactionReferenceNumber())
            .setInstructionNumber(domain.getInstructionNumber())
            .setCbFundSourceId(domain.getCbFundSourceId())
            .setDeliveryTerm(domain.getDeliveryTerm())
            .setApplicantId(domain.getApplicantId())
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
            .setExchangeGainLoss(domain.getExchangeGainLoss());
  }

  public CasaTransactionRequest getNetPayableCASACredit(
      RemittanceTransactionEntity request, AuditInformation auditInformation) {
    CasaTransactionRequest casaRequest = new CasaTransactionRequest();
    casaRequest
        .setInstrumentNo("V-")
        .setActivityId(activityId)
        .setAmountCcy(request.getAmountFcy() == null ? BigDecimal.ZERO : request.getAmountFcy())
        .setAmountLcy(request.getAmountLcy() == null ? BigDecimal.ZERO : request.getAmountLcy())
        .setCurrencyCode(request.getCurrencyCode())
        .setExchangeRate(request.getExchangeRate())
        .setRateType(request.getExchangeRateType())
        .setDebitTransaction(false)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration(
            "Disburse from A/C "
                + request.getCreditAccountNumber()
                + "\r\n"
                + request.getB2bInformation())
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setInitiatorModule("ID")
        .setAccNumber(request.getCreditAccountNumber());
    return casaRequest;
  }

  public GlTransactionRequest getNetPayableGLCredit(
      RemittanceTransactionEntity request, AuditInformation auditInformation) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest
        .setActivityId(activityId)
        .setAmountCcy(request.getAmountFcy() == null ? BigDecimal.ZERO : request.getAmountFcy())
        .setAmountLcy(request.getAmountLcy() == null ? BigDecimal.ZERO : request.getAmountLcy())
        .setCurrencyCode(request.getCurrencyCode())
        .setExchangeRate(request.getExchangeRate())
        .setRateType(request.getExchangeRateType())
        .setDebitTransaction(false)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration(
            "Disburse from A/C "
                + request.getCreditAccountNumber()
                + "\r\n"
                + request.getB2bInformation())
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorModule("ID")
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setGlCode(request.getCreditAccountNumber());
    return glRequest;
  }

  // .setNarration("VAT on charge deducted from A/C " + request.getAccount().getCode())
  public CasaTransactionRequest getChargableCASACredit(
      RemittanceTransactionEntity request, AuditInformation auditInformation) {
    return null;
  }

  public GlTransactionRequest getChargableGLCredit(
      RemittanceTransactionEntity request, AuditInformation auditInformation) {
    return null;
  }

  public GlTransactionRequest getNetPayableGLDebit(
      RemittanceTransactionEntity request,
      String productGLCode,
      AuditInformation auditInformation) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest
        .setActivityId(activityId)
        .setAmountCcy(request.getAmountFcy() == null ? BigDecimal.ZERO : request.getAmountFcy())
        .setAmountLcy(request.getAmountLcy() == null ? BigDecimal.ZERO : request.getAmountLcy())
        .setCurrencyCode(request.getCurrencyCode())
        .setExchangeRate(request.getExchangeRate())
        .setRateType(request.getExchangeRateType())
        .setDebitTransaction(false)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration(
            "Disburse from A/C "
                + request.getCreditAccountNumber()
                + "\r\n"
                + request.getB2bInformation())
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorModule("ID")
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setGlCode(productGLCode);
    return glRequest;
  }

  public GlTransactionRequest getExchangeGainGL(
      RemittanceTransactionEntity request,
      String exchangeGainGLCode,
      AuditInformation auditInformation) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest
        .setActivityId(activityId)
        .setAmountCcy(
            request.getExchangeGainLoss() == null ? BigDecimal.ZERO : request.getExchangeGainLoss())
        .setAmountLcy(
            request.getExchangeGainLoss() == null ? BigDecimal.ZERO : request.getExchangeGainLoss())
        .setCurrencyCode(request.getCurrencyCode())
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
        .setNarration(
            "Disburse from A/C "
                + request.getCreditAccountNumber()
                + "\r\n"
                + request.getB2bInformation())
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorModule("ID")
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setGlCode(exchangeGainGLCode);
    return glRequest;
  }
}
