package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.NostroReconcileQuery;
import com.mislbd.ababil.foreignremittance.service.NostroReconcileService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class NostroReconcileQueryHandlerAggregate {

  private NostroReconcileService nostroReconcileService;

  public NostroReconcileQueryHandlerAggregate(NostroReconcileService nostroReconcileService) {
    this.nostroReconcileService = nostroReconcileService;
  }

  @QueryHandler
  public QueryResult<?> nostroReconcileSearch(NostroReconcileQuery nostroReconcileQuery) {
    if (nostroReconcileQuery.isAsPage()) {
      PagedResult<?> pagedResult =
          nostroReconcileService.getMessages(
              nostroReconcileQuery.getPageable(),
              nostroReconcileQuery.getId(),
              nostroReconcileQuery.getAccountNo(),
              nostroReconcileQuery.isSelected(),
              nostroReconcileQuery.getValueDate());
      return QueryResult.of(pagedResult);
    } else {
      List<?> listResult =
          nostroReconcileService.getMessages(
              nostroReconcileQuery.getId(),
              nostroReconcileQuery.getAccountNo(),
              nostroReconcileQuery.isSelected(),
              nostroReconcileQuery.getValueDate());
      return QueryResult.of(listResult);
    }
  }
}
