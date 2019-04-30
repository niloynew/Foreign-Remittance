package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.RemittanceTransactionSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RemittanceTransactionServiceImpl implements RemittanceTransactionService {

  private final RemittanceTransactionRepository remittanceTransactionRepository;
  private final RemittanceTransactionMapper remittanceTransactionMapper;

  public RemittanceTransactionServiceImpl(
      RemittanceTransactionRepository remittanceTransactionRepository,
      RemittanceTransactionMapper remittanceTransactionMapper) {
    this.remittanceTransactionRepository = remittanceTransactionRepository;
    this.remittanceTransactionMapper = remittanceTransactionMapper;
  }

  @Override
  public PagedResult<RemittanceTransaction> getTransactions(
      Pageable pageable,
      String globalTransactionNo,
      RemittanceType remittanceType,
      String transactionReferenceNumber,
      String applicantName,
      String beneficiaryName,
      LocalDate fromDate,
      LocalDate toDate) {
    return PagedResultBuilder.build(
        remittanceTransactionRepository.findAll(
            RemittanceTransactionSpecification.searchSpecification(
                globalTransactionNo,
                remittanceType,
                transactionReferenceNumber,
                applicantName,
                beneficiaryName,
                fromDate,
                toDate),
            pageable),
        remittanceTransactionMapper.entityToDomain());
  }

  @Override
  public List<RemittanceTransaction> getTransactions(
      String globalTransactionNo,
      RemittanceType remittanceType,
      String transactionReferenceNumber,
      String applicantName,
      String beneficiaryName,
      LocalDate fromDate,
      LocalDate toDate) {
    return ListResultBuilder.build(
        remittanceTransactionRepository.findAll(
            RemittanceTransactionSpecification.searchSpecification(
                globalTransactionNo,
                remittanceType,
                transactionReferenceNumber,
                applicantName,
                beneficiaryName,
                fromDate,
                toDate)),
        remittanceTransactionMapper.entityToDomain());
  }
}
