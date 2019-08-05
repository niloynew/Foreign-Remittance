package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.IDProductByIdQuery;
import com.mislbd.ababil.foreignremittance.query.IDProductQuery;
import com.mislbd.ababil.foreignremittance.service.IDProductService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class IDProductQueryHandlerAggregate {

  private IDProductService idProductService;

  public IDProductQueryHandlerAggregate(IDProductService idProductService) {
    this.idProductService = idProductService;
  }

  @QueryHandler
  public QueryResult<?> porductSearch(IDProductQuery idProductQuery) {
    if (idProductQuery.isAsPage()) {
      PagedResult<?> pagedResult =
          idProductService.findIDProducts(
              idProductQuery.getPageable(),
              idProductQuery.getName(),
              idProductQuery.getCode(),
              idProductQuery.getCurrency());
      return QueryResult.of(pagedResult);
    } else {
      List<?> listResult =
          idProductService.findIDProducts(
              idProductQuery.getName(), idProductQuery.getCode(), idProductQuery.getCurrency());
      return QueryResult.of(listResult);
    }
  }

  @QueryHandler
  public QueryResult<?> iDProductByIdSearch(IDProductByIdQuery idProductByIdQuery) {
    return QueryResult.of(idProductService.findIDProduct(idProductByIdQuery.getId()));
  }
}
