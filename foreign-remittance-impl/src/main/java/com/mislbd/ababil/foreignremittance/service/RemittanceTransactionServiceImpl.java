package com.mislbd.ababil.foreignremittance.service;

import com.google.common.base.Strings;
import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.domain.AuditInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.IDProductRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.repository.specification.RemittanceTransactionSpecification;
import com.mislbd.ababil.organization.service.BranchService;
import com.mislbd.ababil.transaction.domain.TransactionCorrectionRequest;
import com.mislbd.ababil.transaction.service.TransactionService;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import com.mislbd.security.core.NgSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
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
  private final IDProductRepository productRepository;
  private final ConfigurationService configurationService;
  private final NgSession ngSession;
  private final BranchService branchService;

  public RemittanceTransactionServiceImpl(
      RemittanceTransactionRepository remittanceTransactionRepository,
      RemittanceTransactionMapper remittanceTransactionMapper,
      TransactionService transactionService,
      IDProductRepository productRepository,
      ConfigurationService configurationService,
      NgSession ngSession,
      BranchService branchService) {
    this.remittanceTransactionRepository = remittanceTransactionRepository;
    this.remittanceTransactionMapper = remittanceTransactionMapper;
    this.transactionService = transactionService;
    this.productRepository = productRepository;
    this.configurationService = configurationService;
    this.ngSession = ngSession;
    this.branchService = branchService;
  }

  @Override
  public PagedResult<RemittanceTransaction> getTransactions(
      Pageable pageable,
      String globalTransactionNo,
      RemittanceType remittanceType,
      String transactionReferenceNumber,
      String applicantName,
      String beneficiaryName,
      LocalDate fromDate,
      LocalDate toDate) {
    Page<RemittanceTransactionEntity> remittanceTransactions =
        remittanceTransactionRepository.findAll(
            RemittanceTransactionSpecification.searchSpecification(
                globalTransactionNo,
                remittanceType,
                transactionReferenceNumber,
                applicantName,
                beneficiaryName,
                fromDate,
                toDate),
            pageable);

    return PagedResultBuilder.build(
        remittanceTransactions, remittanceTransactionMapper.entityToDomain());
  }

  @Override
  public List<RemittanceTransaction> getTransactions(
      String globalTransactionNo,
      RemittanceType remittanceType,
      String transactionReferenceNumber,
      String applicantName,
      String beneficiaryName,
      LocalDate fromDate,
      LocalDate toDate) {
    List<RemittanceTransactionEntity> remittanceTransactions =
        remittanceTransactionRepository.findAll(
            RemittanceTransactionSpecification.searchSpecification(
                globalTransactionNo,
                remittanceType,
                transactionReferenceNumber,
                applicantName,
                beneficiaryName,
                fromDate,
                toDate));

    Collections.sort(
        remittanceTransactions,
        new Comparator<RemittanceTransactionEntity>() {
          @Override
          public int compare(RemittanceTransactionEntity rm1, RemittanceTransactionEntity rm2) {
            return rm2.getGlobalTransactionNo().compareTo(rm1.getGlobalTransactionNo());
          }
        });
    return ListResultBuilder.build(
        remittanceTransactions, remittanceTransactionMapper.entityToDomain());
  }

  @Override
  public Optional<RemittanceTransaction> findTransaction(Long id) {
    return remittanceTransactionRepository
        .findById(id)
        .map(remittanceTransactionMapper.entityToDomain()::map);
  }

  @Override
  public Optional<RemittanceTransaction> findTransaction(String referenceNumber) {
    return remittanceTransactionRepository
        .findByTransactionReferenceNumber(referenceNumber)
        .map(remittanceTransactionMapper.entityToDomain()::map);
  }

  @Override
  public void correctTransaction(AuditInformation auditInformation) {
    TransactionCorrectionRequest transactionCorrectionRequest =
        prepareTransactionCorrectionRequest(auditInformation);
    transactionService.correctTransaction(transactionCorrectionRequest);
  }

  @Override
  public String generateTransactionReferenceNumber(String remittanceCategory) {
    // IDProductEntity productEntity =
    //    productRepository.findById(productId).orElseThrow(IDProductNotFoundException::new);
    String branchCode = null;
    if (branchService.findBranch(ngSession.getUserBranch()).get().getCode() != null) {
      branchCode = branchService.findBranch(ngSession.getUserBranch()).get().getCode();
    }
    StringBuilder referenceNumber = new StringBuilder();
    referenceNumber
        .append(Strings.padStart(String.valueOf(branchCode), 3, '0'))
        .append(remittanceCategory)
        .append(
            String.valueOf(configurationService.getCurrentApplicationDate().getYear()).substring(2))
        .append(
            Strings.padStart(
                String.valueOf(
                    remittanceTransactionRepository.generateTransactionReferenceNumberSequence()),
                5,
                '0'));
    return referenceNumber.toString();
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
