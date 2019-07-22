package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.ExportCByIdQuery;
import com.mislbd.ababil.foreignremittance.query.ExportCQuery;
import com.mislbd.ababil.foreignremittance.service.ExportLCService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class ExportCQueryHandlerAggregate {

  private final ExportLCService exportLCService;

  public ExportCQueryHandlerAggregate(ExportLCService exportLCService) {
    this.exportLCService = exportLCService;
  }

  @QueryHandler
  public QueryResult<?> exportCSearch(ExportCQuery exportCQuery) {
    if (exportCQuery.isAsPage()) {
      PagedResult<?> pagedResult =
          exportLCService.getLcs(
              exportCQuery.getPageable(),
              exportCQuery.getName(),
              exportCQuery.getOwnerName(),
              exportCQuery.getAddress(),
              exportCQuery.getCountry(),
              exportCQuery.getCpName(),
              exportCQuery.getCpEmail());
      return QueryResult.of(pagedResult);
    } else {
      List<?> listResult =
          exportLCService.getLcList(
              exportCQuery.getName(),
              exportCQuery.getOwnerName(),
              exportCQuery.getAddress(),
              exportCQuery.getCountry(),
              exportCQuery.getCpName(),
              exportCQuery.getCpEmail());

      return QueryResult.of(listResult);
    }
  }

  @QueryHandler
  public QueryResult<?> exportCByIdSearch(ExportCByIdQuery exportCByIdQuery) {
    return QueryResult.of(exportLCService.getLc(exportCByIdQuery.getId()));
  }
}
