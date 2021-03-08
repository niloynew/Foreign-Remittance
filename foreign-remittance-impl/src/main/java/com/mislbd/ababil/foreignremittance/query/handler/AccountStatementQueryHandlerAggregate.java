package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.domain.ShadowTransactionRecord;
import com.mislbd.ababil.foreignremittance.query.AccountStatementQuery;
import com.mislbd.ababil.foreignremittance.service.NostroTransactionRecordService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;

@QueryAggregate
public class AccountStatementQueryHandlerAggregate {
  private NostroTransactionRecordService nostroTransactionRecordService;

  public AccountStatementQueryHandlerAggregate(NostroTransactionRecordService nostroTransactionRecordService) {
    this.nostroTransactionRecordService = nostroTransactionRecordService;
  }

  @QueryHandler
  public QueryResult<?> accountStatementSearch(AccountStatementQuery accountStatementQuery) {
    PagedResult<ShadowTransactionRecord> accountStatementPage =
        nostroTransactionRecordService.getAccountStatement(
            accountStatementQuery.getPageable(),
            accountStatementQuery.getAccountNumber(),
            accountStatementQuery.getFromDate(),
            accountStatementQuery.getToDate());

    return QueryResult.of(accountStatementPage);
  }
}
