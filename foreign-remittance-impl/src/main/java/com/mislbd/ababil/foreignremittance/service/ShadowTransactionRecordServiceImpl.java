package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.OtherCbsSystemSettlementStatus;
import com.mislbd.ababil.foreignremittance.domain.ShadowTransactionRecord;
import com.mislbd.ababil.foreignremittance.exception.AccountNotFoundException;
import com.mislbd.ababil.foreignremittance.external.domain.QueryTransactionStatus;
import com.mislbd.ababil.foreignremittance.external.domain.TransactionQueryResponse;
import com.mislbd.ababil.foreignremittance.external.service.TransactionQueryService;
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
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShadowTransactionRecordServiceImpl implements ShadowTransactionRecordService {

  private final ShadowAccountRepository shadowAccountRepository;
  private final ShadowTransactionRecordRepository shadowTransactionRecordRepository;
  private final ShadowTransactionRecordMapper shadowTransactionRecordMapper;
  private final TransactionQueryService queryService;

  public ShadowTransactionRecordServiceImpl(
      ShadowAccountRepository shadowAccountRepository,
      ShadowTransactionRecordRepository shadowTransactionRecordRepository,
      ShadowTransactionRecordMapper shadowTransactionRecordMapper,
      TransactionQueryService queryService) {
    this.shadowAccountRepository = shadowAccountRepository;
    this.shadowTransactionRecordRepository = shadowTransactionRecordRepository;
    this.shadowTransactionRecordMapper = shadowTransactionRecordMapper;
    this.queryService = queryService;
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

    // Fetch all the transactions with selected criteria
    if (reconcileStatus != null && reconcileStatus != OtherCbsSystemSettlementStatus.Pending) {
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

    List<ShadowTransactionRecordEntity> shadowTransactionRecordEntities =
        shadowTransactionRecordRepository.findAll(
            NostroTransactionRecordSpecification.searchSpecification(
                (accountEntity != null ? accountEntity.getId() : null),
                fromDate,
                toDate,
                    OtherCbsSystemSettlementStatus.Pending));

    if (!shadowTransactionRecordEntities.isEmpty()) {
      // Mapping to List<String> from the transactions
      List<String> records =
          ListResultBuilder.build(
              shadowTransactionRecordEntities,
              shadowTransactionRecordMapper.entityToStringVoucher());
      // query to check the
      List<TransactionQueryResponse> responses =
          queryService
              .getTransactionQueryResponse(records)
              .stream()
              .filter(x -> x.getTransactionStatus() != QueryTransactionStatus.PENDING)
              .collect(Collectors.toList());

      List<ShadowTransactionRecordEntity> transactionsToBeProcessed =
          shadowTransactionRecordEntities
              .stream()
              .filter(
                  entity ->
                      responses
                          .stream()
                          .anyMatch(
                              res ->
                                  Long.valueOf(res.getVoucherNumber())
                                      .equals(entity.getGlobalTxnNo())))
              .collect(Collectors.toList());

      final int start = (int) pageable.getOffset();
      final int end = Math.min((start + pageable.getPageSize()), transactionsToBeProcessed.size());
      final Page<ShadowTransactionRecordEntity> page =
          new PageImpl<>(
              transactionsToBeProcessed.subList(start, end),
              pageable,
              transactionsToBeProcessed.size());

      return PagedResultBuilder.build(page, shadowTransactionRecordMapper.entityToDomain());
    }
    return PagedResult.EmptyPage();
  }
}
