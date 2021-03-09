package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.domain.ShadowTransactionRecord;
import com.mislbd.ababil.foreignremittance.query.AccountStatementQuery;
import com.mislbd.ababil.foreignremittance.service.ShadowTransactionRecordService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;

@QueryAggregate
public class AccountStatementQueryHandlerAggregate {

  private final ShadowTransactionRecordService shadowTransactionRecordService;

  public AccountStatementQueryHandlerAggregate(
      ShadowTransactionRecordService shadowTransactionRecordService) {
    this.shadowTransactionRecordService = shadowTransactionRecordService;
  }

  @QueryHandler
  public QueryResult<?> accountStatementSearch(AccountStatementQuery accountStatementQuery) {
    PagedResult<ShadowTransactionRecord> accountStatementPage =
        shadowTransactionRecordService.getAccountStatement(
            accountStatementQuery.getPageable(),
            accountStatementQuery.getAccountNumber(),
            accountStatementQuery.getFromDate(),
            accountStatementQuery.getToDate());

    return QueryResult.of(accountStatementPage);
  }
}
