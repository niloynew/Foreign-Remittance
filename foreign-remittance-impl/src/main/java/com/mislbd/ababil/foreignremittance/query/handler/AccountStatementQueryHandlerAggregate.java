package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.domain.AccountStatement;
import com.mislbd.ababil.foreignremittance.query.AccountStatementQuery;
import com.mislbd.ababil.foreignremittance.service.AccountStatementService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;

@QueryAggregate
public class AccountStatementQueryHandlerAggregate {
  private AccountStatementService accountStatementService;

  public AccountStatementQueryHandlerAggregate(AccountStatementService accountStatementService) {
    this.accountStatementService = accountStatementService;
  }

  @QueryHandler
  public QueryResult<?> accountStatementSearch(AccountStatementQuery accountStatementQuery) {
    PagedResult<AccountStatement> accountStatementPage =
        accountStatementService.getAccountStatement(
            accountStatementQuery.getPageable(),
            accountStatementQuery.getAccountNumber(),
            accountStatementQuery.getFromDate(),
            accountStatementQuery.getToDate());

    return QueryResult.of(accountStatementPage);
  }
}
