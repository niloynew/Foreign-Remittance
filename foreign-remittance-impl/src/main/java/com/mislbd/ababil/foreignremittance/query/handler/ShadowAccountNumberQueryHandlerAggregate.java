package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.ShadowAccountNumberQuery;
import com.mislbd.ababil.foreignremittance.service.IDProductService;
import com.mislbd.ababil.foreignremittance.service.ShadowAccountNumberProviderService;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import com.mislbd.security.core.NgSession;

@QueryAggregate
public class ShadowAccountNumberQueryHandlerAggregate {
  private final ShadowAccountNumberProviderService shadowAccountNumberProviderService;
  private final IDProductService idProductService;
  private final NgSession ngSession;

  public ShadowAccountNumberQueryHandlerAggregate(
      ShadowAccountNumberProviderService shadowAccountNumberProviderService,
      IDProductService idProductService,
      NgSession ngSession) {
    this.shadowAccountNumberProviderService = shadowAccountNumberProviderService;
    this.idProductService = idProductService;
    this.ngSession = ngSession;
  }

  @QueryHandler
  public QueryResult<?> shadowAccountSearch(ShadowAccountNumberQuery shadowAccountNumberQuery) {
    if (idProductService.isExists(shadowAccountNumberQuery.getProductId())) {
      String queryResult =
          shadowAccountNumberProviderService.getAccountNumber(
              shadowAccountNumberQuery.getProductId(),
              ngSession.getUserBranch(),
              ngSession.getUsername());
      return QueryResult.of(queryResult);
    } else {
      return QueryResult.of("NOT FOUND");
    }
  }
}
