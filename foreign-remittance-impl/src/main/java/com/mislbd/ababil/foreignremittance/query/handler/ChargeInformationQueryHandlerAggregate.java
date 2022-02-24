package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.service.RemittanceChargeInformationService;
import com.mislbd.asset.query.annotation.QueryAggregate;

@QueryAggregate
public class ChargeInformationQueryHandlerAggregate {
  private RemittanceChargeInformationService remittanceChargeInformationService;

  public ChargeInformationQueryHandlerAggregate(
      RemittanceChargeInformationService remittanceChargeInformationService) {
    this.remittanceChargeInformationService = remittanceChargeInformationService;
  }

  //  @QueryHandler
  //  public QueryResult<?> chargeInformationSearch(ChargeInformationQuery chargeInformationQuery) {
  //    return QueryResult.of(
  //        remittanceChargeInformationService.getChargeInfo(
  //            chargeInformationQuery.getRemittanceType(),
  //            chargeInformationQuery.getTypeId(),
  //            chargeInformationQuery.getAccountNumber(),
  //            chargeInformationQuery.getAmount()));
  //  }
}
