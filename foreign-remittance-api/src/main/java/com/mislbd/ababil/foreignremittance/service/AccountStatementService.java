package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.AccountStatement;
import com.mislbd.asset.commons.data.domain.PagedResult;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface AccountStatementService {
    PagedResult<AccountStatement> getAccountStatements(
            Pageable pageable,
            Long accountId,
            LocalDate fromDate,
            LocalDate toDate
    );

    List<AccountStatement> getAccountStatements(
            Long accountId,
            LocalDate fromDate,
            LocalDate toDate
    );
}
