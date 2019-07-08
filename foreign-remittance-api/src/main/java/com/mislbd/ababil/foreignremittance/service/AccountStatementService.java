package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.AccountStatement;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface AccountStatementService {
  PagedResult<AccountStatement> getAccountStatements(
      Pageable pageable, Long accountId, LocalDate fromDate, LocalDate toDate);

  List<AccountStatement> getAccountStatements(Long accountId, LocalDate fromDate, LocalDate toDate);

  PagedResult<AccountStatement> getAccountStatement(
      Pageable pageable, String shadowAccountNumber, LocalDate fromDate, LocalDate toDate);
}
