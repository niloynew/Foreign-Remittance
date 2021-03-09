package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.SenderOrReceiverCustomerByIdQuery;
import com.mislbd.ababil.foreignremittance.query.SenderOrReceiverCustomerQuery;
import com.mislbd.ababil.foreignremittance.service.SenderOrReceiverCustomerService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class SenderOrReceiverQueryHandlerAggregate {

  private final SenderOrReceiverCustomerService senderOrReceiverCustomerService;

  public SenderOrReceiverQueryHandlerAggregate(
      SenderOrReceiverCustomerService senderOrReceiverCustomerService) {
    this.senderOrReceiverCustomerService = senderOrReceiverCustomerService;
  }

  @QueryHandler
  public QueryResult<?> senderOrReceiverCustomerSearch(SenderOrReceiverCustomerQuery query) {
    if (query.isAsPage()) {
      PagedResult<?> pagedResult =
          senderOrReceiverCustomerService.getSenderOrReceiverCustomers(
              query.getPageable(),
              query.getName(),
              query.getOwnerName(),
              query.getAddress(),
              query.getCountry(),
              query.getCpName(),
              query.getCpEmail());
      return QueryResult.of(pagedResult);
    } else {
      List<?> listResult =
          senderOrReceiverCustomerService.getSenderOrReceiverCustomerList(
              query.getName(),
              query.getOwnerName(),
              query.getAddress(),
              query.getCountry(),
              query.getCpName(),
              query.getCpEmail());

      return QueryResult.of(listResult);
    }
  }

  @QueryHandler
  public QueryResult<?> senderOrReceiverCustomerByIdSearch(
      SenderOrReceiverCustomerByIdQuery query) {
    return QueryResult.of(
        senderOrReceiverCustomerService.getSenderOrReceiverCustomer(query.getId()));
  }
}
