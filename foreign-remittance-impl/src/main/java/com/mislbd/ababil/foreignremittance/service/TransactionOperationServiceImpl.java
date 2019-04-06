package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.domain.TransactionOperation;
import com.mislbd.ababil.foreignremittance.mapper.TransactionOperationMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionOperationRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.TransactionOperationSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionOperationServiceImpl implements TransactionOperationService {

  private final TransactionOperationMapper transactionOperationMapper;
  private final TransactionOperationRepository transactionOperationRepository;

  public TransactionOperationServiceImpl(
      TransactionOperationMapper transactionOperationMapper,
      TransactionOperationRepository transactionOperationRepository) {
    this.transactionOperationMapper = transactionOperationMapper;
    this.transactionOperationRepository = transactionOperationRepository;
  }

  @Override
  public PagedResult<TransactionOperation> getOperations(
      Pageable pageable, long typeId, RemittanceType remittanceType) {
    return PagedResultBuilder.build(
        transactionOperationRepository.findAll(
            TransactionOperationSpecification.findOperations(typeId, remittanceType), pageable),
        transactionOperationMapper.entityToDomain());
  }

  @Override
  public List<TransactionOperation> getOperations(long typeId, RemittanceType remittanceType) {
    return ListResultBuilder.build(
        transactionOperationRepository.findAll(
            TransactionOperationSpecification.findOperations(typeId, remittanceType)),
        transactionOperationMapper.entityToDomain());
  }
}
