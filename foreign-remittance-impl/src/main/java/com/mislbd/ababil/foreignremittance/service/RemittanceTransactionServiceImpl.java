package com.mislbd.ababil.foreignremittance.service;

import com.google.common.base.Strings;
import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.domain.*;
import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceCategoryMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceCategoryRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceCategoryEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.repository.specification.RemittanceTransactionSpecification;
import com.mislbd.ababil.transaction.domain.TransactionCorrectionRequest;
import com.mislbd.ababil.transaction.service.TransactionService;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RemittanceTransactionServiceImpl implements RemittanceTransactionService {

  private final RemittanceTransactionRepository remittanceTransactionRepository;
  private final RemittanceTransactionMapper remittanceTransactionMapper;
  private final TransactionService transactionService;
  private final TransactionTypeService transactionTypeService;
  private final RemittanceCategoryMapper categoryMapper;
  private final ConfigurationService configurationService;
  private final RemittanceCategoryRepository categoryRepository;

  public RemittanceTransactionServiceImpl(
      RemittanceTransactionRepository remittanceTransactionRepository,
      RemittanceTransactionMapper remittanceTransactionMapper,
      TransactionService transactionService,
      TransactionTypeService transactionTypeService,
      RemittanceCategoryMapper categoryMapper,
      ConfigurationService configurationService,
      RemittanceCategoryRepository categoryRepository) {
    this.remittanceTransactionRepository = remittanceTransactionRepository;
    this.remittanceTransactionMapper = remittanceTransactionMapper;
    this.transactionService = transactionService;
    this.transactionTypeService = transactionTypeService;
    this.categoryMapper = categoryMapper;
    this.configurationService = configurationService;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public PagedResult<RemittanceTransaction> getTransactions(
      Pageable pageable,
      RemittanceTransactionStatus status,
      RemittanceType remittanceType,
      String remittanceTypeName,
      String transactionReferenceNumber,
      LocalDate fromDate,
      LocalDate toDate,
      String salesContractNumber) {
    Page<RemittanceTransactionEntity> remittanceTransactions =
        remittanceTransactionRepository.findAll(
            RemittanceTransactionSpecification.searchSpecification(
                status,
                remittanceType,
                remittanceTypeName,
                transactionReferenceNumber,
                fromDate,
                toDate,
                salesContractNumber,
                null,
                null,
                null),
            pageable);

    return PagedResultBuilder.build(
        remittanceTransactions, remittanceTransactionMapper.entityToDomain(false));
  }

  @Override
  public List<RemittanceTransaction> getTransactions(
      RemittanceTransactionStatus status,
      RemittanceType remittanceType,
      String remittanceTypeName,
      String transactionReferenceNumber,
      LocalDate fromDate,
      LocalDate toDate,
      String salesContractNumber) {
    List<RemittanceTransactionEntity> remittanceTransactions =
        remittanceTransactionRepository.findAll(
            RemittanceTransactionSpecification.searchSpecification(
                status,
                remittanceType,
                remittanceTypeName,
                transactionReferenceNumber,
                fromDate,
                toDate,
                salesContractNumber,
                null,
                null,
                null));
    return ListResultBuilder.build(
        remittanceTransactions, remittanceTransactionMapper.entityToDomain(false));
  }

  @Override
  public Optional<RemittanceTransaction> findTransaction(Long id) {
    return remittanceTransactionRepository
        .findById(id)
        .map(remittanceTransactionMapper.entityToDomain(true)::map);
  }

  @Override
  public Boolean isExistInwardRemittanceByReferenceNumber(String referenceNumber) {

    return remittanceTransactionRepository.existsByTransactionReferenceNumber(referenceNumber);
  }

  @Override
  public Optional<RemittanceTransaction> findTransaction(String referenceNumber) {
    return remittanceTransactionRepository
        .findByTransactionReferenceNumber(referenceNumber)
        .map(remittanceTransactionMapper.entityToDomain(true)::map);
  }

  @Override
  public void correctTransaction(AuditInformation auditInformation) {
    TransactionCorrectionRequest transactionCorrectionRequest =
        prepareTransactionCorrectionRequest(auditInformation);
    transactionService.correctTransaction(transactionCorrectionRequest);
  }

  @Override
  public String generateTransactionReferenceNumber(Long branch, Long categoryId) {
    RemittanceCategoryEntity category =
        categoryRepository
            .findById(categoryId)
            .orElseThrow(
                () ->
                    new ForeignRemittanceBaseException(
                        "Remittance category not found with id- " + categoryId));
    Long transactionReferenceNumber =
        category.getRemittanceType().equals(RemittanceType.OUTWARD_REMITTANCE)
            ? remittanceTransactionRepository.generateTransactionReferenceNumberSequence()
            : remittanceTransactionRepository.generateInwardTransactionReferenceNumberSequence();
    return Strings.padStart(String.valueOf(branch), 3, '0')
        + category.getDescription()
        + String.valueOf(configurationService.getCurrentApplicationDate().getYear()).substring(2)
        + Strings.padStart(String.valueOf(transactionReferenceNumber), 5, '0');
  }

  @Override
  public List<RemittanceCategory> getRemittanceCategories(RemittanceType remittanceType) {
    return ListResultBuilder.build(
        categoryRepository.findAllByRemittanceType(remittanceType),
        categoryMapper.entityToDomain());
  }

  @Override
  public RemittanceCategory getRemittanceCategoryById(Long id) {
    return categoryMapper
        .entityToDomain()
        .map(
            categoryRepository
                .findById(id)
                .orElseThrow(
                    () ->
                        new ForeignRemittanceBaseException(
                            "Remittance category not found with id - " + id)));
  }

  @Override
  public List<ExportRelatedRemittanceInformation> getRemittanceInformationForTf(
      Long customerId, String currency, String salesContractNUmber, RemittanceType remittanceType) {
    String transactionTypeName;
    Long applicantId, beneficiaryId;
    if (remittanceType == RemittanceType.INWARD_REMITTANCE) {
      applicantId = null;
      beneficiaryId = customerId;
      transactionTypeName = "Advanced Receive Agt. Export";
    } else {
      beneficiaryId = null;
      applicantId = customerId;
      transactionTypeName = "Advanced Payment Agt. Import";
    }

    return ListResultBuilder.build(
        remittanceTransactionRepository.findAll(
            RemittanceTransactionSpecification.searchSpecification(
                null,
                remittanceType,
                transactionTypeName,
                null,
                null,
                null,
                salesContractNUmber,
                applicantId,
                beneficiaryId,
                currency)),
        remittanceTransactionMapper.entityToExportInformation(remittanceType));
  }

  private TransactionCorrectionRequest prepareTransactionCorrectionRequest(
      AuditInformation auditInformation) {
    TransactionCorrectionRequest request = new TransactionCorrectionRequest();
    request.setEntryUser(auditInformation.getEntryUser());
    request.setEntryTerminal(auditInformation.getEntryTerminal());
    request.setEntryTime(auditInformation.getEntryDate());
    request.setGlobalTransactionNumber(auditInformation.getGlobalTxnNumber());
    request.setInitiatorBranch(Long.valueOf(auditInformation.getUserBranch()));
    request.setVerifyUser(auditInformation.getVerifyUser());
    request.setVerifyTerminal(auditInformation.getVerifyTerminal());
    return request;
  }
}
