package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.domain.CBFundSource;
import com.mislbd.ababil.foreignremittance.query.CBFundSourceQuery;
import com.mislbd.ababil.foreignremittance.service.CBFundSourceService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class CBFundSourceQueryHandlerAggregate {
  private CBFundSourceService cbFundSourceService;

  public CBFundSourceQueryHandlerAggregate(CBFundSourceService cbFundSourceService) {
    this.cbFundSourceService = cbFundSourceService;
  }

  @QueryHandler
  public QueryResult<?> cBFundsourceSearch(CBFundSourceQuery cbFundSourceQuery) {
    if (cbFundSourceQuery.isAsPage()) {
      PagedResult<CBFundSource> pagedResult =
          cbFundSourceService.getFundSources(
              cbFundSourceQuery.getPageable(), cbFundSourceQuery.getId());
      return QueryResult.of(pagedResult);
    } else {
      List<CBFundSource> listResult = cbFundSourceService.getFundSources(cbFundSourceQuery.getId());
      return QueryResult.of(listResult);
    }
  }
}
