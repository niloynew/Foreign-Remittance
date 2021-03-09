package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.SenderOrReceiverCustomer;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface SenderOrReceiverCustomerService {

  PagedResult<SenderOrReceiverCustomer> getSenderOrReceiverCustomers(
      Pageable pageable,
      String name,
      String ownerName,
      String address,
      String country,
      String cpName,
      String cpEmail);

  List<SenderOrReceiverCustomer> getSenderOrReceiverCustomerList(
      String name, String ownerName, String address, String country, String cpName, String cpEmail);

  Optional<SenderOrReceiverCustomer> getSenderOrReceiverCustomer(Long id);
}
