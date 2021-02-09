package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.domain.SwiftRegister;
import com.mislbd.ababil.foreignremittance.exception.RemittanceTransactionNotFoundException;
import com.mislbd.ababil.foreignremittance.exception.SwiftRegisterNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.mapper.SwiftRegisterMapper;
import com.mislbd.ababil.foreignremittance.mapper.TransactionToRequestMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.SwiftRegisterRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.SwiftRegisterEntity;
import com.mislbd.ababil.foreignremittance.repository.specification.SwiftRegisterSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import com.mislbd.swift.broker.model.RoutingStatus;
import com.mislbd.swift.broker.model.raw.mt1xx.MT103MessageRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SwiftRegisterServiceImpl implements SwiftRegisterService {

  private final SwiftRegisterRepository swiftRegisterRepository;
  private final SwiftRegisterMapper swiftRegisterMapper;
  private final RemittanceTransactionRepository remittanceTransactionRepository;
  private final RemittanceTransactionMapper remittanceTransactionMapper;
  private final TransactionToRequestMapper transactionToRequestMapper;

  public SwiftRegisterServiceImpl(
      SwiftRegisterRepository swiftRegisterRepository,
      SwiftRegisterMapper swiftRegisterMapper,
      RemittanceTransactionRepository remittanceTransactionRepository,
      RemittanceTransactionMapper remittanceTransactionMapper,
      TransactionToRequestMapper transactionToRequestMapper) {
    this.swiftRegisterRepository = swiftRegisterRepository;
    this.swiftRegisterMapper = swiftRegisterMapper;
    this.remittanceTransactionRepository = remittanceTransactionRepository;
    this.remittanceTransactionMapper = remittanceTransactionMapper;
    this.transactionToRequestMapper = transactionToRequestMapper;
  }

  @Override
  public PagedResult<SwiftRegister> getSwiftRegisters(
      Pageable pageable,
      String referenceNo,
      String senderAddress,
      String receiverAddress,
      RoutingStatus status,
      Date messageRoutingDateTimeFrom,
      Date messageRoutingDateTimeTo) {
    return PagedResultBuilder.build(
        swiftRegisterRepository.findAll(
            SwiftRegisterSpecification.searchSwiftRegisters(
                referenceNo,
                senderAddress,
                receiverAddress,
                status,
                messageRoutingDateTimeFrom,
                messageRoutingDateTimeTo),
            pageable),
        swiftRegisterMapper.entityToDomain());
  }

  @Override
  public List<SwiftRegister> getSwiftRegisters(
      String referenceNo,
      String senderAddress,
      String receiverAddress,
      RoutingStatus status,
      Date messageRoutingDateTimeFrom,
      Date messageRoutingDateTimeTo) {
    return ListResultBuilder.build(
        swiftRegisterRepository.findAll(
            SwiftRegisterSpecification.searchSwiftRegisters(
                referenceNo,
                senderAddress,
                receiverAddress,
                status,
                messageRoutingDateTimeFrom,
                messageRoutingDateTimeTo)),
        swiftRegisterMapper.entityToDomain());
  }

  @Override
  public SwiftRegister findByReferenceNumber(String referenceNumber) {
    return swiftRegisterMapper
        .entityToDomain()
        .map(
            swiftRegisterRepository
                .findByReferenceNo(referenceNumber)
                .orElseThrow(SwiftRegisterNotFoundException::new));
  }

  @Override
  public void saveRegister(SwiftRegister swiftRegister) {
    swiftRegisterRepository.save(swiftRegisterMapper.domainToEntity().map(swiftRegister));
  }

  @Override
  public void updateRegister(SwiftRegister swiftRegister) {
    Optional<SwiftRegisterEntity> register =
        swiftRegisterRepository.findById(swiftRegister.getId());
    if (!register.isPresent()) {
      throw new SwiftRegisterNotFoundException();
    }
    swiftRegisterRepository.save(swiftRegisterMapper.domainToEntity().map(swiftRegister));
  }

  @Override
  public void registerMessage(
      String sendersReference, String senderAddress, String receiverAddress, String message) {
    SwiftRegister register =
        new SwiftRegister()
            .setReferenceNo(sendersReference)
            .setSenderAddress(senderAddress)
            .setReceiverAddress(receiverAddress)
            .setTextBlock(message)
            .setMessageRoutingDateTime(new Date())
            .setStatus(RoutingStatus.Queued);
    if (swiftRegisterRepository.findByReferenceNo(sendersReference).isPresent()) {
      register.setId(
          swiftRegisterRepository
              .findByReferenceNo(sendersReference)
              .orElseThrow(SwiftRegisterNotFoundException::new)
              .getId());
    }
    swiftRegisterRepository.save(swiftRegisterMapper.domainToEntity().map(register));
  }

  @Override
  public Optional<SwiftRegister> findRegisterById(Long id) {
    return swiftRegisterRepository.findById(id).map(swiftRegisterMapper.entityToDomain()::map);
  }

  @Override
  public MT103MessageRequest getMessageRequestByRegisterId(Long id) {
    SwiftRegister swiftRegister =
        swiftRegisterMapper
            .entityToDomain()
            .map(
                swiftRegisterRepository
                    .findById(id)
                    .orElseThrow(SwiftRegisterNotFoundException::new));

    RemittanceTransaction remittanceTransaction =
        remittanceTransactionRepository
            .findByTransactionReferenceNumber(swiftRegister.getReferenceNo())
            .map(remittanceTransactionMapper.entityToDomain()::map)
            .orElseThrow(RemittanceTransactionNotFoundException::new);

    return transactionToRequestMapper.mapTransactionToMessageRequest(
        remittanceTransaction,
        swiftRegister.getSenderAddress(),
        swiftRegister.getReceiverAddress());
  }
}
