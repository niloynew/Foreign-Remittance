package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.AccountStatement;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileStatus;
import com.mislbd.ababil.foreignremittance.exception.AccountNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.AccountStatementMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.AccountStatementRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.AccountStatementEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.ababil.foreignremittance.repository.specification.AccountStatementSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountStatementServiceImpl implements AccountStatementService {

  private final AccountStatementRepository accountStatementRepository;
  private final ShadowAccountRepository shadowAccountRepository;
  private final AccountStatementMapper accountStatementMapper;

  public AccountStatementServiceImpl(
      AccountStatementRepository accountStatementRepository,
      ShadowAccountRepository shadowAccountRepository,
      AccountStatementMapper accountStatementMapper) {
    this.accountStatementRepository = accountStatementRepository;
    this.shadowAccountRepository = shadowAccountRepository;
    this.accountStatementMapper = accountStatementMapper;
  }

  @Override
  public PagedResult<AccountStatement> getAccountStatements(
      Pageable pageable, Long accountId, LocalDate fromDate, LocalDate toDate) {
    Page<AccountStatementEntity> accountStatement =
        accountStatementRepository.findAll(
            AccountStatementSpecification.searchSpecification(accountId, fromDate, toDate, null),
            pageable);
    return PagedResultBuilder.build(accountStatement, accountStatementMapper.entityToDomain());
  }

  @Override
  public List<AccountStatement> getAccountStatements(
      Long accountId, LocalDate fromDate, LocalDate toDate) {
    List<AccountStatementEntity> accountStatement =
        accountStatementRepository.findAll(
            AccountStatementSpecification.searchSpecification(accountId, fromDate, toDate, null));
    return ListResultBuilder.build(accountStatement, accountStatementMapper.entityToDomain());
  }

  @Override
  public PagedResult<AccountStatement> getAccountStatement(
      Pageable pageable, String shadowAccountNumber, LocalDate fromDate, LocalDate toDate) {
    ShadowAccountEntity accountEntity =
        shadowAccountRepository
            .findByNumber(shadowAccountNumber)
            .orElseThrow(AccountNotFoundException::new);
    return PagedResultBuilder.build(
        accountStatementRepository.findAll(
            AccountStatementSpecification.searchSpecification(
                accountEntity.getId(), fromDate, toDate, null),
            pageable),
        accountStatementMapper.entityToDomain());
  }

  @Override
  public PagedResult<AccountStatement> getUnreconciledTransactionData(
      Pageable pageable,
      String accountNumber,
      LocalDate fromDate,
      LocalDate toDate,
      NostroReconcileStatus reconcileStatus) {
    ShadowAccountEntity accountEntity = null;
    if (accountNumber != null) {
      accountEntity =
          shadowAccountRepository
              .findByNumber(accountNumber)
              .orElseThrow(AccountNotFoundException::new);
    }
    if (reconcileStatus == null) {
      reconcileStatus = NostroReconcileStatus.Unreconciled;
    }
    return PagedResultBuilder.build(
        accountStatementRepository.findAll(
            AccountStatementSpecification.searchSpecification(
                (accountEntity != null ? accountEntity.getId() : null),
                fromDate,
                toDate,
                reconcileStatus),
            pageable),
        accountStatementMapper.entityToDomain());
  }
}
