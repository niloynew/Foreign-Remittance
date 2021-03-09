package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.NostroReconcileQuery;
import com.mislbd.ababil.foreignremittance.service.NostroTransactionRecordService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class NostroReconcileQueryHandlerAggregate {

  private final NostroTransactionRecordService nostroTransactionRecordService;

  public NostroReconcileQueryHandlerAggregate(
      NostroTransactionRecordService nostroTransactionRecordService) {
    this.nostroTransactionRecordService = nostroTransactionRecordService;
  }

  @QueryHandler
  public QueryResult<?> nostroReconcileSearch(NostroReconcileQuery nostroReconcileQuery) {
    if (nostroReconcileQuery.isAsPage()) {
      PagedResult<?> pagedResult =
          nostroTransactionRecordService.getMessages(
              nostroReconcileQuery.getPageable(),
              nostroReconcileQuery.getId(),
              nostroReconcileQuery.getAccountNo(),
              nostroReconcileQuery.getAdvBranch(),
              nostroReconcileQuery.isSelected(),
              nostroReconcileQuery.getValueDate());
      return QueryResult.of(pagedResult);
    } else {
      List<?> listResult =
          nostroTransactionRecordService.getMessages(
              nostroReconcileQuery.getId(),
              nostroReconcileQuery.getAccountNo(),
              nostroReconcileQuery.getAdvBranch(),
              nostroReconcileQuery.isSelected(),
              nostroReconcileQuery.getValueDate());
      return QueryResult.of(listResult);
    }
  }
}
