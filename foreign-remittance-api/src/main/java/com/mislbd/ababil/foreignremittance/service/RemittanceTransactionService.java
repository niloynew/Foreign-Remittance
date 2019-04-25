package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface RemittanceTransactionService {
  PagedResult<RemittanceTransaction> getTransactions(
      Pageable pageable,
      String transactionReferenceNumber,
      String applicantName,
      String beneficiaryName,
      LocalDate fromDate,
      LocalDate toDate);

  List<RemittanceTransaction> getTransactions(
      String transactionReferenceNumber,
      String applicantName,
      String beneficiaryName,
      LocalDate fromDate,
      LocalDate toDate);
}
