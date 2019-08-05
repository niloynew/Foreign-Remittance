package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.PaymentPurposeQuery;
import com.mislbd.ababil.foreignremittance.service.PaymentPurposeService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class PaymentPurposeQueryHandlerAggregate {
  private PaymentPurposeService paymentPurposeService;

  public PaymentPurposeQueryHandlerAggregate(PaymentPurposeService paymentPurposeService) {
    this.paymentPurposeService = paymentPurposeService;
  }

  @QueryHandler
  public QueryResult<?> paymentPurposeSearch(PaymentPurposeQuery paymentPurposeQuery) {
    if (paymentPurposeQuery.isAsPage()) {
      PagedResult<?> pagedResult =
          paymentPurposeService.getPaymentPurposes(
              paymentPurposeQuery.getPageable(),
              paymentPurposeQuery.getId(),
              paymentPurposeQuery.getCode(),
              paymentPurposeQuery.getDescription());
      return QueryResult.of(pagedResult);
    } else {
      List<?> listResult =
          paymentPurposeService.getPaymentPurposes(
              paymentPurposeQuery.getId(),
              paymentPurposeQuery.getCode(),
              paymentPurposeQuery.getDescription());
      return QueryResult.of(listResult);
    }
  }
}
