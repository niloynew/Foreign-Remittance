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
import com.mislbd.ababil.transaction.domain.SubGlTransactionRequest;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class RemittanceTransactionMapper {

  private static final Long activityId = Long.valueOf(501);
  private final RemittanceTransactionRepository remittanceTransactionRepository;
  private final TransactionTypeRepository transactionTypeRepository;
  private final ShadowAccountRepository shadowAccountRepository;

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
            .setRemittanceType(domain.getRemittanceType())
            .setTransactionType(transactionTypeRepository.getOne(domain.getTransactionTypeId()))
            .setPaymentPurposeId(domain.getPaymentPurposeId())
            .setCommodityDescription(domain.getCommodityDescription())
            .setTransactionReferenceNumber(domain.getTransactionReferenceNumber())
            .setInstrumentNumber(domain.getInstrumentNumber())
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

  public GlTransactionRequest getNetPayableGLDebit(
      RemittanceTransactionEntity request,
      String productGLCode,
      AuditInformation auditInformation) {

    Long ownerBranchId =
        shadowAccountRepository
            .findByNumber(request.getDebitAccountNumber())
            .get()
            .getOwnerBranchId();

    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest
        .setActivityId(activityId)
        .setAmountCcy(request.getAmountFcy() == null ? BigDecimal.ZERO : request.getAmountFcy())
        .setAmountLcy(request.getAmountLcy() == null ? BigDecimal.ZERO : request.getAmountLcy())
        .setCurrencyCode(request.getCurrencyCode())
        .setExchangeRate(request.getExchangeRate())
        .setRateType(request.getExchangeRateType())
        .setDebitTransaction(true)
        .setBatchNo(request.getBatchNumber())
        .setGlobalTxnNo(request.getGlobalTransactionNo())
        .setOwnerBranch(ownerBranchId)
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration("Disburse from A/C " + request.getCreditAccountNumber())
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorModule("ID")
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setGlCode(productGLCode);
    return glRequest;
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
        .setOwnerBranch(
            shadowAccountRepository
                .findByNumber(request.getDebitAccountNumber())
                .get()
                .getOwnerBranchId())
        .setEntryUser(auditInformation.getEntryUser())
        .setEntryTerminal(auditInformation.getEntryTerminal())
        .setEntryTime(auditInformation.getEntryDate())
        .setVerifyUser(auditInformation.getVerifyUser())
        .setVerifyTerminal(auditInformation.getVerifyTerminal())
        .setNarration("Disburse from A/C " + request.getCreditAccountNumber())
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorModule("ID")
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setGlCode(request.getCreditAccountNumber());
    return glRequest;
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
        .setNarration("Disburse from A/C " + request.getCreditAccountNumber())
        .setApprovalFlowInstanceId(auditInformation.getProcessId())
        .setInitiatorBranch(auditInformation.getUserBranch())
        .setInitiatorModule("ID")
        .setAccNumber(request.getCreditAccountNumber());
    return casaRequest;
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

  public GlTransactionRequest getChargeableGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest
        .setActivityId(activityId)
        .setAmountCcy(charge.getChargeAmount())
        .setAmountLcy(charge.getChargeAmount())
        .setCurrencyCode(charge.getCurrency())
        .setExchangeRate(request.getExchangeRate())
        .setRateType(request.getExchangeRateType() == null ? 1 : request.getExchangeRateType())
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
    subGLRequest
        .setActivityId(activityId)
        .setAmountCcy(charge.getChargeAmount())
        .setAmountLcy(charge.getChargeAmount())
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
        .setAmountCcy(charge.getChargeAmount())
        .setAmountLcy(charge.getChargeAmount())
        .setCurrencyCode(charge.getCurrency())
        .setExchangeRate(charge.getExchangeRate())
        //                .setRateType(charge.)
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
        .setAmountCcy(charge.getChargeAmount())
        .setAmountLcy(charge.getChargeAmount())
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
      BigDecimal totalCharges) {
    CasaTransactionRequest casaRequest = new CasaTransactionRequest();
    casaRequest
        .setInstrumentNo("V-")
        .setActivityId(activityId)
        .setAmountCcy(totalCharges)
        .setAmountLcy(totalCharges)
        //                .setCurrencyCode(request.getCurrencyCode())
        //                .setExchangeRate(request.getExchangeRate())
        //                .setRateType(request.getExchangeRateType())
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
      BigDecimal totalCharges) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest
        .setActivityId(activityId)
        .setAmountCcy(totalCharges)
        .setAmountLcy(totalCharges)
        //                .setCurrencyCode(request.getCurrencyCode())
        //                .setExchangeRate(request.getExchangeRate())
        //                .setRateType(request.getExchangeRateType())
        //                .setCurrencyCode(transactionCurrency)
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
