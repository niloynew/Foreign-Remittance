package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.NostroReconcileQuery;
import com.mislbd.ababil.foreignremittance.query.UnreconciledTransactionQuery;
import com.mislbd.ababil.foreignremittance.service.NostroTransactionRecordService;
import com.mislbd.ababil.foreignremittance.service.ShadowTransactionService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class NostroReconcileQueryHandlerAggregate {

  private final ShadowTransactionService shadowTransactionService;
  private final NostroTransactionRecordService nostroTransactionRecordService;

  public NostroReconcileQueryHandlerAggregate(
      ShadowTransactionService shadowTransactionService,
      NostroTransactionRecordService nostroTransactionRecordService) {
    this.shadowTransactionService = shadowTransactionService;
    this.nostroTransactionRecordService = nostroTransactionRecordService;
  }

  @QueryHandler
  public QueryResult<?> nostroReconcileSearch(NostroReconcileQuery nostroReconcileQuery) {
    if (nostroReconcileQuery.isAsPage()) {
      PagedResult<?> pagedResult =
          shadowTransactionService.getMessages(
              nostroReconcileQuery.getPageable(),
              nostroReconcileQuery.getId(),
              nostroReconcileQuery.getAccountNo(),
              nostroReconcileQuery.getAdvBranch(),
              nostroReconcileQuery.isSelected(),
              nostroReconcileQuery.getValueDate());
      return QueryResult.of(pagedResult);
    } else {
      List<?> listResult =
          shadowTransactionService.getMessages(
              nostroReconcileQuery.getId(),
              nostroReconcileQuery.getAccountNo(),
              nostroReconcileQuery.getAdvBranch(),
              nostroReconcileQuery.isSelected(),
              nostroReconcileQuery.getValueDate());
      return QueryResult.of(listResult);
    }
  }

  @QueryHandler
  public QueryResult<?> unreconciledTransactionSearch(
      UnreconciledTransactionQuery transactionQuery) {
    PagedResult<?> pagedResult =
        nostroTransactionRecordService.getUnreconciledTransactionData(
            transactionQuery.getPageable(),
            transactionQuery.getAccountNumber(),
            transactionQuery.getFromDate(),
            transactionQuery.getToDate(),
            transactionQuery.getStatus());
    return QueryResult.of(pagedResult);
  }
}
