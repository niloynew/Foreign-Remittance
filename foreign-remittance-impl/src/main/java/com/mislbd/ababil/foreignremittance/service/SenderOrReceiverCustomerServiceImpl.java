package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.SenderOrReceiverCustomer;
import com.mislbd.ababil.foreignremittance.mapper.SenderOrReceiverCustomerMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.SenderOrReceiverCustomerRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.SenderOrReceiverCustomerSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SenderOrReceiverCustomerServiceImpl implements SenderOrReceiverCustomerService {

  private final SenderOrReceiverCustomerRepository senderOrReceiverCustomerRepository;
  private final SenderOrReceiverCustomerMapper senderOrReceiverCustomerMapper;

  public SenderOrReceiverCustomerServiceImpl(
      SenderOrReceiverCustomerRepository senderOrReceiverCustomerRepository,
      SenderOrReceiverCustomerMapper senderOrReceiverCustomerMapper) {
    this.senderOrReceiverCustomerRepository = senderOrReceiverCustomerRepository;
    this.senderOrReceiverCustomerMapper = senderOrReceiverCustomerMapper;
  }

  @Override
  public PagedResult<SenderOrReceiverCustomer> getSenderOrReceiverCustomers(
      Pageable pageable,
      String name,
      String ownerName,
      String address,
      String country,
      String cpName,
      String cpEmail) {
    return PagedResultBuilder.build(
        senderOrReceiverCustomerRepository.findAll(
            SenderOrReceiverCustomerSpecification.findSpecificLcs(
                name, ownerName, address, country, cpName, cpEmail),
            pageable),
        senderOrReceiverCustomerMapper.entityToDomain());
  }

  @Override
  public List<SenderOrReceiverCustomer> getSenderOrReceiverCustomerList(
      String name,
      String ownerName,
      String address,
      String country,
      String cpName,
      String cpEmail) {
    return ListResultBuilder.build(
        senderOrReceiverCustomerRepository.findAll(
            SenderOrReceiverCustomerSpecification.findSpecificLcs(
                name, ownerName, address, country, cpName, cpEmail)),
        senderOrReceiverCustomerMapper.entityToDomain());
  }

  @Override
  public Optional<SenderOrReceiverCustomer> getSenderOrReceiverCustomer(Long id) {
    return senderOrReceiverCustomerRepository
        .findById(id)
        .map(senderOrReceiverCustomerMapper.entityToDomain()::map);
  }
}
