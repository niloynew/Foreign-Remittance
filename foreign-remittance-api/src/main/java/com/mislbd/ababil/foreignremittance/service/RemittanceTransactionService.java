package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.*;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface RemittanceTransactionService {
  PagedResult<RemittanceTransaction> getTransactions(
      Pageable pageable,
      RemittanceTransactionStatus status,
      RemittanceType remittanceType,
      String transactionReferenceNumber,
      LocalDate fromDate,
      LocalDate toDate);

  List<RemittanceTransaction> getTransactions(
      RemittanceTransactionStatus status,
      RemittanceType remittanceType,
      String transactionReferenceNumber,
      LocalDate fromDate,
      LocalDate toDate);

  Optional<RemittanceTransaction> findTransaction(Long id);

  Optional<RemittanceTransaction> findTransaction(String referenceNumber);

  Boolean isExistInwardRemittanceByReferenceNumber(String referenceNumber);

  void correctTransaction(AuditInformation auditInformation);

  String generateTransactionReferenceNumber(Long branch, Long categoryId);

  // String generateInwardTransactionReferenceNumber(Long branch, Long categoryId);

  List getRemittanceCategories(RemittanceType remittanceType);

  RemittanceCategory getRemittanceCategoryById(Long id);

  List<ExportRelatedRemittanceInformation> getRemittanceInformationForTf(
      Long customerId, String currency);
}
