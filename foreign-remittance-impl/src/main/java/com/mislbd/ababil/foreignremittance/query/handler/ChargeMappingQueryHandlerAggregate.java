package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.ChargeMappingQuery;
import com.mislbd.ababil.foreignremittance.service.ChargeMappingService;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;

@QueryAggregate
public class ChargeMappingQueryHandlerAggregate {

  private final ChargeMappingService chargeMappingService;

  public ChargeMappingQueryHandlerAggregate(ChargeMappingService chargeMappingService) {
    this.chargeMappingService = chargeMappingService;
  }

  @QueryHandler
  public QueryResult<?> chargeMappingSearch(ChargeMappingQuery chargeMappingQuery) {

    if (chargeMappingQuery.isAsPage()) {
      return QueryResult.of(
          chargeMappingService.findAll(
              chargeMappingQuery.getPageable(),
              chargeMappingQuery.getRemittanceType(),
              chargeMappingQuery.getTypeId(),
              chargeMappingQuery.getChargeId(),
              chargeMappingQuery.getChargeModifiable(),
              chargeMappingQuery.getVatModifiable()));

    } else {
      return QueryResult.of(
          chargeMappingService.findAll(
              chargeMappingQuery.getRemittanceType(),
              chargeMappingQuery.getTypeId(),
              chargeMappingQuery.getChargeId(),
              chargeMappingQuery.getChargeModifiable(),
              chargeMappingQuery.getVatModifiable()));
    }
  }
}
