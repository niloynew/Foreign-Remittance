package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.domain.TransactionType;
import com.mislbd.ababil.foreignremittance.mapper.TransactionTypeMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionTypeRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.TransactionTypeSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

  private final TransactionTypeRepository transactionTypeRepository;
  private final TransactionTypeMapper transactionTypeMapper;

  public TransactionTypeServiceImpl(
      TransactionTypeRepository transactionTypeRepository,
      TransactionTypeMapper transactionTypeMapper) {
    this.transactionTypeRepository = transactionTypeRepository;
    this.transactionTypeMapper = transactionTypeMapper;
  }

  @Override
  public PagedResult<TransactionType> getTypes(Pageable pageable, RemittanceType remittanceType) {
    return PagedResultBuilder.build(
        transactionTypeRepository.findAll(
            TransactionTypeSpecification.findTransactionTypes(remittanceType), pageable),
        transactionTypeMapper.entityToDomain());
  }

  @Override
  public List<TransactionType> getTypes(RemittanceType remittanceType) {
    return ListResultBuilder.build(
        transactionTypeRepository.findAll(
            TransactionTypeSpecification.findTransactionTypes(remittanceType)),
        transactionTypeMapper.entityToDomain());
  }
}
