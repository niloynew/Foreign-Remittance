package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.domain.RemittanceCharge;
import com.mislbd.ababil.foreignremittance.exception.IDProductNotFoundException;
import com.mislbd.ababil.foreignremittance.query.ChargeConfigurationIdQuery;
import com.mislbd.ababil.foreignremittance.query.ChargeConfigurationQuery;
import com.mislbd.ababil.foreignremittance.service.RemittanceChargeService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class ChargeConfigurationQueryHandlerAggregate {
  private final RemittanceChargeService remittanceChargeService;

  public ChargeConfigurationQueryHandlerAggregate(RemittanceChargeService remittanceChargeService) {
    this.remittanceChargeService = remittanceChargeService;
  }

  @QueryHandler
  public QueryResult<?> chargeConfigurationSearch(
      ChargeConfigurationQuery chargeConfigurationQuery) {
    if (chargeConfigurationQuery.isAsPage()) {
      PagedResult<?> pagedResult =
          remittanceChargeService.getCharges(
              chargeConfigurationQuery.getPageable(),
              chargeConfigurationQuery.getChargeName(),
              chargeConfigurationQuery.getChargeAccountType(),
              chargeConfigurationQuery.getVatAccountType(),
              chargeConfigurationQuery.getStatus());
      return QueryResult.of(pagedResult);
    } else {
      List<RemittanceCharge> listResult =
          remittanceChargeService.getCharges(
              chargeConfigurationQuery.getChargeName(),
              chargeConfigurationQuery.getChargeAccountType(),
              chargeConfigurationQuery.getVatAccountType(),
              chargeConfigurationQuery.getStatus());
      return QueryResult.of(listResult);
    }
  }

  @QueryHandler
  public QueryResult<RemittanceCharge> chargeConfigurationIdSearch(
      ChargeConfigurationIdQuery chargeConfigurationIdQuery) {
    return QueryResult.of(
        remittanceChargeService
            .findRemittanceChargeById(chargeConfigurationIdQuery.getId())
            .orElseThrow(IDProductNotFoundException::new));
  }
}
