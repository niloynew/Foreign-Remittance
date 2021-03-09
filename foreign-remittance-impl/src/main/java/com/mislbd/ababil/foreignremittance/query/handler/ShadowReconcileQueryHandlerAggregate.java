package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.UnreconciledTransactionQuery;
import com.mislbd.ababil.foreignremittance.service.ShadowTransactionRecordService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;

@QueryAggregate
public class ShadowReconcileQueryHandlerAggregate {

  private final ShadowTransactionRecordService shadowTransactionRecordService;

  public ShadowReconcileQueryHandlerAggregate(
      ShadowTransactionRecordService shadowTransactionRecordService) {
    this.shadowTransactionRecordService = shadowTransactionRecordService;
  }

  @QueryHandler
  public QueryResult<?> unreconciledTransactionSearch(
      UnreconciledTransactionQuery transactionQuery) {
    PagedResult<?> pagedResult =
        shadowTransactionRecordService.getUnreconciledTransactionData(
            transactionQuery.getPageable(),
            transactionQuery.getAccountNumber(),
            transactionQuery.getFromDate(),
            transactionQuery.getToDate(),
            transactionQuery.getStatus());
    return QueryResult.of(pagedResult);
  }
}
