package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.AuditInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.external.service.GLAccountService;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionTypeRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.transaction.domain.CasaTransactionRequest;
import com.mislbd.ababil.transaction.domain.GlTransactionRequest;
import com.mislbd.ababil.transaction.domain.IDTransactionRequest;
import com.mislbd.ababil.transaction.domain.SubGlTransactionRequest;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@SuppressWarnings("Duplicates")
@Component
public class RemittanceTransactionMapper {

  private final RemittanceTransactionRepository remittanceTransactionRepository;
  private final TransactionTypeRepository transactionTypeRepository;
  private BigDecimal chargeAmount = null;
  private BigDecimal vatAmount = null;
  private final BankInformationMapper bankInformationMapper;
  private final AdditionInformationMapper additionInformationMapper;
  private final GLAccountService glAccountService;

  public RemittanceTransactionMapper(
      RemittanceTransactionRepository remittanceTransactionRepository,
      TransactionTypeRepository transactionTypeRepository,
      BankInformationMapper bankInformationMapper,
      AdditionInformationMapper additionInformationMapper,
      GLAccountService glAccountService) {
    this.remittanceTransactionRepository = remittanceTransactionRepository;
    this.transactionTypeRepository = transactionTypeRepository;
    this.bankInformationMapper = bankInformationMapper;
    this.additionInformationMapper = additionInformationMapper;
    this.glAccountService = glAccountService;
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
            .setShadowAccountType(entity.getShadowAccountType())
            .setShadowAccountNumber(entity.getShadowAccountNumber())
            .setShadowAccountCurrency(entity.getShadowAccountCurrency())
            .setOperatingAccountType(entity.getOperatingAccountType())
            .setOperatingAccountNumber(entity.getOperatingAccountNumber())
            .setOperatingAccountCurrency(entity.getOperatingAccountCurrency())
            .setOperatingRateTypeId(entity.getOperatingRateTypeId())
            .setOperatingRate(entity.getOperatingRate())
            .setChargeAccountType(entity.getChargeAccountType())
            .setChargeAccountNumber(entity.getChargeAccountNumber())
            .setClientRateTypeId(entity.getClientRateTypeId())
            .setClientRate(entity.getClientRate())
            .setAmountFcy(entity.getAmountFcy())
            .setAmountLcy(entity.getAmountLcy())
            .setAmountRcy(entity.getAmountRcy())
            .setGlobalTransactionNo(entity.getGlobalTransactionNo())
            .setTotalChargeAmount(entity.getTotalChargeAmount())
            .setTotalVatAmount(entity.getTotalVatAmount())
            .setPublishedToXmm(entity.isPublishedToXmm())
            .setAuthorizedToXmm(entity.isAuthorizedToXmm())
            .setRemittanceCategory(entity.getRemittanceCategory())
            .setTotalChargeAmountAfterWaived(entity.getTotalChargeAmountAfterWaived())
            .setTotalVatAmountAfterWaived(entity.getTotalVatAmountAfterWaived())
            .setRemittanceAdditionalInformation(
                entity.getRemittanceAdditionalInformation() == null
                    ? null
                    : additionInformationMapper
                        .entityToDomain()
                        .map(entity.getRemittanceAdditionalInformation()))
            .setBankInformation(
                entity
                    .getRemittanceTransactionBankMappingEntity()
                    .stream()
                    .map(bankEntity -> bankInformationMapper.entityToDomain().map(bankEntity))
                    .collect(Collectors.toList()));
  }

  public ResultMapper<RemittanceTransaction, RemittanceTransactionEntity> domainToEntity() {
    return domain ->
        remittanceTransactionRepository
            .findById(domain.getId())
            .orElseGet(RemittanceTransactionEntity::new)
            .setRemittanceType(domain.getRemittanceType())
            .setTransactionType(transactionTypeRepository.getOne(domain.getTransactionTypeId()))
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
            .setShadowAccountType(domain.getShadowAccountType())
            .setShadowAccountNumber(domain.getShadowAccountNumber())
            .setShadowAccountCurrency(domain.getShadowAccountCurrency())
            .setOperatingAccountType(domain.getOperatingAccountType())
            .setOperatingAccountNumber(domain.getOperatingAccountNumber())
            .setOperatingAccountCurrency(domain.getOperatingAccountCurrency())
            .setOperatingRateTypeId(domain.getOperatingRateTypeId())
            .setOperatingRate(domain.getOperatingRate())
            .setAdjustmentRefIdForOperation(domain.getAdjustmentRefIdForOperation())
            .setChargeAccountType(domain.getChargeAccountType())
            .setChargeAccountNumber(domain.getChargeAccountNumber())
            .setAdjustmentRefIdForCharge(domain.getAdjustmentRefIdForCharge())
            .setClientRateTypeId(domain.getClientRateTypeId())
            .setRemittanceCategory(domain.getRemittanceCategory())
            .setClientRate(domain.getClientRate())
            .setAmountFcy(domain.getAmountFcy())
            .setAmountLcy(domain.getAmountLcy())
            .setAmountRcy(domain.getAmountRcy())
            .setValueDate(domain.getValueDate())
            .setPublishedToXmm(domain.isPublishedToXmm())
            .setAuthorizedToXmm(domain.isAuthorizedToXmm())
            .setTotalChargeAmount(domain.getTotalChargeAmount())
            .setTotalChargeAmountAfterWaived(domain.getTotalChargeAmountAfterWaived())
            .setTotalVatAmount(domain.getTotalVatAmount())
            .setRemittanceAdditionalInformation(
                domain.getRemittanceAdditionalInformation() == null
                    ? null
                    : additionInformationMapper
                        .domainToEntity()
                        .map(domain.getRemittanceAdditionalInformation()))
            .setTotalVatAmountAfterWaived(domain.getTotalVatAmountAfterWaived());
  }

  public IDTransactionRequest getNetPayableShadow(
      RemittanceTransactionEntity request,
      boolean isDebit,
      AuditInformation auditInformation,
      String narration,
      Long activityId) {
    IDTransactionRequest transactionRequest = new IDTransactionRequest();
    transactionRequest.setActivityId(activityId);
    transactionRequest.setAmountCcy(request.getAmountFcy());
    transactionRequest.setReferenceAmount(request.getAmountFcy());
    transactionRequest.setRefCurrencyCode(request.getShadowAccountCurrency());
    transactionRequest.setExchangeRate(BigDecimal.ONE);
    transactionRequest.setRateType(1L);
    transactionRequest.setDebitTransaction(isDebit);
    transactionRequest.setBatchNo(request.getBatchNumber());
    transactionRequest.setActivityId(activityId);
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
    transactionRequest.setAccNumber(request.getShadowAccountNumber());
    return transactionRequest;
  }

  public GlTransactionRequest getNetPayableClientGL(
      RemittanceTransactionEntity request,
      boolean isDebit,
      AuditInformation auditInformation,
      String narration,
      Long activityId,
      String baseCurrency) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest.setActivityId(activityId);
    glRequest.setAmountCcy(request.getAmountRcy());
    glRequest.setReferenceAmount(request.getAmountFcy());
    glRequest.setAccountCurrencyCode(baseCurrency);
    glRequest.setRefCurrencyCode(request.getShadowAccountCurrency());
    glRequest.setExchangeRate(request.getOperatingRate());
    glRequest.setRateType(request.getOperatingRateTypeId());
    glRequest.setDebitTransaction(isDebit);
    glRequest.setBatchNo(request.getBatchNumber());
    glRequest.setGlobalTxnNo(request.getGlobalTransactionNo());
    glRequest.setOwnerBranch(auditInformation.getUserBranch());
    glRequest.setEntryUser(auditInformation.getEntryUser());
    glRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    glRequest.setEntryTime(auditInformation.getEntryDate());
    glRequest.setVerifyUser(auditInformation.getVerifyUser());
    glRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    glRequest.setValueDate(request.getValueDate());
    glRequest.setNarration(narration);
    glRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    glRequest.setAdjustmentRefId(request.getAdjustmentRefIdForOperation());
    glRequest.setInitiatorModule("ID");
    glRequest.setInitiatorBranch(auditInformation.getUserBranch());
    glRequest.setGlCode(request.getOperatingAccountNumber());
    return glRequest;
  }

  public CasaTransactionRequest getNetPayableCASAClient(
      RemittanceTransactionEntity request,
      boolean isDebit,
      AuditInformation auditInformation,
      String narration,
      Long activityId) {
    CasaTransactionRequest casaRequest = new CasaTransactionRequest();
    casaRequest.setInstrumentNo("V-");
    casaRequest.setActivityId(activityId);
    casaRequest.setAmountCcy(request.getAmountRcy());
    casaRequest.setReferenceAmount(request.getAmountFcy());
    casaRequest.setRefCurrencyCode(request.getShadowAccountCurrency());
    casaRequest.setExchangeRate(request.getOperatingRate());
    casaRequest.setRateType(request.getOperatingRateTypeId());
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
    casaRequest.setAccNumber(request.getOperatingAccountNumber());
    casaRequest.setValueDate(request.getValueDate());
    return casaRequest;
  }

  public GlTransactionRequest getChargeableGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge,
      Long activityId) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    if (charge.getChargeAmountAfterWaived() == null) {
      chargeAmount = charge.getChargeAmount();
    } else {
      chargeAmount = charge.getChargeAmountAfterWaived();
    }
    glRequest.setActivityId(activityId);
    glRequest.setAmountCcy(chargeAmount);
    glRequest.setReferenceAmount(chargeAmount);
    glRequest.setRefCurrencyCode(charge.getCurrency());
    glRequest.setAccountCurrencyCode(charge.getCurrency());
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
        "Charges against "
            + request.getTransactionType().getName()
            + "#"
            + request.getTransactionReferenceNumber());
    glRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    glRequest.setInitiatorModule("ID");
    glRequest.setInitiatorBranch(auditInformation.getUserBranch());
    glRequest.setGlCode(charge.getChargeAccountCode());
    glRequest.setAdjustmentRefId(request.getAdjustmentRefIdForCharge());
    glRequest.setValueDate(request.getValueDate());
    return glRequest;
  }

  public SubGlTransactionRequest getChargeableSubGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge,
      Long activityId) {
    SubGlTransactionRequest subGLRequest = new SubGlTransactionRequest();
    if (charge.getChargeAmountAfterWaived() == null) {
      chargeAmount = charge.getChargeAmount();
    } else {
      chargeAmount = charge.getChargeAmountAfterWaived();
    }
    subGLRequest.setActivityId(activityId);
    subGLRequest.setAmountCcy(chargeAmount);
    subGLRequest.setReferenceAmount(chargeAmount);
    subGLRequest.setRefCurrencyCode(charge.getCurrency());
    subGLRequest.setExchangeRate(BigDecimal.ONE);
    subGLRequest.setRateType(1);
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
    subGLRequest.setValueDate(request.getValueDate());
    return subGLRequest;
  }

  public GlTransactionRequest getVATGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge,
      Long activityId) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest.setActivityId(activityId);
    glRequest.setAmountCcy(charge.getVatAmountAfterWaived());
    glRequest.setReferenceAmount(charge.getVatAmountAfterWaived());
    glRequest.setAccountCurrencyCode(charge.getCurrency());
    glRequest.setRefCurrencyCode(charge.getCurrency());
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
    glRequest.setNarration(
        "Vat against "
            + request.getTransactionType().getName()
            + "#"
            + request.getTransactionReferenceNumber());
    glRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    glRequest.setInitiatorBranch(auditInformation.getUserBranch());
    glRequest.setOwnerBranch(auditInformation.getUserBranch());
    glRequest.setInitiatorModule("ID");
    glRequest.setGlCode(charge.getVatAccountCode());
    glRequest.setValueDate(request.getValueDate());
    return glRequest;
  }

  public SubGlTransactionRequest getVALSubGLCredit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      RemittanceChargeInformation charge,
      Long activityId) {
    SubGlTransactionRequest subGLRequest = new SubGlTransactionRequest();
    if (charge.getVatAmountAfterWaived() == null) {
      vatAmount = charge.getVatAmount();
    } else {
      vatAmount = charge.getVatAmountAfterWaived();
    }
    subGLRequest.setActivityId(activityId);
    subGLRequest.setAmountCcy(vatAmount);
    subGLRequest.setReferenceAmount(vatAmount);
    subGLRequest.setRefCurrencyCode(charge.getCurrency());
    subGLRequest.setExchangeRate(BigDecimal.ONE);
    subGLRequest.setRateType(1L);
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
    subGLRequest.setValueDate(request.getValueDate());
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
    casaRequest.setReferenceAmount(totalCharges);
    casaRequest.setRefCurrencyCode(baseCurrency);
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
    casaRequest.setNarration(
        "Charges against "
            + request.getTransactionType().getName()
            + "#"
            + request.getTransactionReferenceNumber());
    casaRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    casaRequest.setInitiatorBranch(auditInformation.getUserBranch());
    casaRequest.setInitiatorModule("ID");
    casaRequest.setAccNumber(request.getChargeAccountNumber());
    casaRequest.setValueDate(request.getValueDate());
    return casaRequest;
  }

  public GlTransactionRequest getChargeableGLDebit(
      RemittanceTransactionEntity request,
      AuditInformation auditInformation,
      BigDecimal totalCharges,
      String baseCurrency,
      Long activityId) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest.setActivityId(activityId);
    glRequest.setAmountCcy(totalCharges);
    glRequest.setAccountCurrencyCode(baseCurrency);
    glRequest.setReferenceAmount(totalCharges);
    glRequest.setExchangeRate(BigDecimal.ONE);
    glRequest.setRateType(1);
    glRequest.setRefCurrencyCode(baseCurrency);
    glRequest.setDebitTransaction(true);
    glRequest.setBatchNo(request.getBatchNumber());
    glRequest.setGlobalTxnNo(request.getGlobalTransactionNo());
    glRequest.setEntryUser(auditInformation.getEntryUser());
    glRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    glRequest.setEntryTime(auditInformation.getEntryDate());
    glRequest.setVerifyUser(auditInformation.getVerifyUser());
    glRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    glRequest.setNarration(
        "Charges against "
            + request.getTransactionType().getName()
            + "#"
            + request.getTransactionReferenceNumber());
    glRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    glRequest.setInitiatorBranch(auditInformation.getUserBranch());
    glRequest.setOwnerBranch(auditInformation.getUserBranch());
    glRequest.setInitiatorModule("ID");
    glRequest.setGlCode(request.getChargeAccountNumber());
    glRequest.setAdjustmentRefId(request.getAdjustmentRefIdForCharge());
    glRequest.setValueDate(request.getValueDate());
    return glRequest;
  }
}
