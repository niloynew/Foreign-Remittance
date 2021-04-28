package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.OtherCbsSystemSettlementStatus;
import com.mislbd.ababil.foreignremittance.domain.ShadowTransactionRecord;
import com.mislbd.ababil.foreignremittance.exception.AccountNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.ShadowTransactionRecordMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowTransactionRecordRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowTransactionRecordEntity;
import com.mislbd.ababil.foreignremittance.repository.specification.NostroTransactionRecordSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShadowTransactionRecordServiceImpl implements ShadowTransactionRecordService {

  private final ShadowAccountRepository shadowAccountRepository;
  private final ShadowTransactionRecordRepository shadowTransactionRecordRepository;
  private final ShadowTransactionRecordMapper shadowTransactionRecordMapper;

  public ShadowTransactionRecordServiceImpl(
      ShadowAccountRepository shadowAccountRepository,
      ShadowTransactionRecordRepository shadowTransactionRecordRepository,
      ShadowTransactionRecordMapper shadowTransactionRecordMapper) {
    this.shadowAccountRepository = shadowAccountRepository;
    this.shadowTransactionRecordRepository = shadowTransactionRecordRepository;
    this.shadowTransactionRecordMapper = shadowTransactionRecordMapper;
  }

  @Override
  public PagedResult<ShadowTransactionRecord> getAccountStatements(
      Pageable pageable, Long accountId, LocalDate fromDate, LocalDate toDate) {
    Page<ShadowTransactionRecordEntity> accountStatement =
        shadowTransactionRecordRepository.findAll(
            NostroTransactionRecordSpecification.searchSpecification(
                accountId, fromDate, toDate, null),
            pageable);
    return PagedResultBuilder.build(
        accountStatement, shadowTransactionRecordMapper.entityToDomain());
  }

  @Override
  public List<ShadowTransactionRecord> getAccountStatements(
      Long accountId, LocalDate fromDate, LocalDate toDate) {
    List<ShadowTransactionRecordEntity> accountStatement =
        shadowTransactionRecordRepository.findAll(
            NostroTransactionRecordSpecification.searchSpecification(
                accountId, fromDate, toDate, null));
    return ListResultBuilder.build(
        accountStatement, shadowTransactionRecordMapper.entityToDomain());
  }

  @Override
  public PagedResult<ShadowTransactionRecord> getAccountStatement(
      Pageable pageable, String shadowAccountNumber, LocalDate fromDate, LocalDate toDate) {
    ShadowAccountEntity accountEntity =
        shadowAccountRepository
            .findByNumber(shadowAccountNumber)
            .orElseThrow(AccountNotFoundException::new);
    return PagedResultBuilder.build(
        shadowTransactionRecordRepository.findAll(
            NostroTransactionRecordSpecification.searchSpecification(
                accountEntity.getId(), fromDate, toDate, null),
            pageable),
        shadowTransactionRecordMapper.entityToDomain());
  }

  @Override
  public PagedResult<ShadowTransactionRecord> getPendingTransactionData(
      Pageable pageable,
      String accountNumber,
      LocalDate fromDate,
      LocalDate toDate,
      OtherCbsSystemSettlementStatus reconcileStatus) {
    ShadowAccountEntity accountEntity = null;
    if (accountNumber != null) {
      accountEntity =
          shadowAccountRepository
              .findByNumber(accountNumber)
              .orElseThrow(AccountNotFoundException::new);
    }
    return PagedResultBuilder.build(
        shadowTransactionRecordRepository.findAll(
            NostroTransactionRecordSpecification.searchSpecification(
                (accountEntity != null ? accountEntity.getId() : null),
                fromDate,
                toDate,
                reconcileStatus),
            pageable),
        shadowTransactionRecordMapper.entityToDomain());
  }
}
