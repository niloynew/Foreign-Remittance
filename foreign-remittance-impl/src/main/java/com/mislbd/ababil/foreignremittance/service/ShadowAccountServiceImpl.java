package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.exception.AccountNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.ShadowAccountMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.ShadowAccountSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShadowAccountServiceImpl implements ShadowAccountService {

  private final ShadowAccountRepository shadowAccountRepository;
  private final ShadowAccountMapper shadowAccountMapper;

  public ShadowAccountServiceImpl(
      ShadowAccountRepository shadowAccountRepository, ShadowAccountMapper shadowAccountMapper) {
    this.shadowAccountRepository = shadowAccountRepository;
    this.shadowAccountMapper = shadowAccountMapper;
  }

  @Override
  public PagedResult<Account> findActiveAccounts(
      Pageable pageable,
      String shadowAccountNumber,
      String name,
      String nostroAccountNumber,
      String bank,
      String branch,
      LocalDate accountOpenDate,
      String currency,
      String product,
      Boolean isActive) {
    return PagedResultBuilder.build(
        shadowAccountRepository.findAll(
            ShadowAccountSpecification.searchSpecification(
                shadowAccountNumber,
                name,
                nostroAccountNumber,
                bank,
                branch,
                accountOpenDate,
                currency,
                product,
                isActive),
            pageable),
        shadowAccountMapper.entityToDomain());
  }

  @Override
  public List<Account> findActiveAccounts(
      String shadowAccountNumber,
      String name,
      String nostroAccountNumber,
      String bank,
      String branch,
      LocalDate accountOpenDate,
      String currency,
      String product,
      Boolean isActive) {
    return ListResultBuilder.build(
        shadowAccountRepository.findAll(
            ShadowAccountSpecification.searchSpecification(
                shadowAccountNumber,
                name,
                nostroAccountNumber,
                bank,
                branch,
                accountOpenDate,
                currency,
                product,
                isActive)),
        shadowAccountMapper.entityToDomain());
  }

  @Override
  public Account findById(Long id) {
    return shadowAccountMapper
        .entityToDomain()
        .map(shadowAccountRepository.findById(id).orElseThrow(AccountNotFoundException::new));
  }

  @Override
  public List<Account> getAccountsByBICCode(Long branchId) {
    return ListResultBuilder.build(
        shadowAccountRepository.findAllByBranchId(branchId), shadowAccountMapper.entityToDomain());
  }

  @Override
  public Account findAccountByNumber(String accountNumber) {
    return shadowAccountMapper
        .entityToDomain()
        .map(
            shadowAccountRepository
                .findByNumber(accountNumber)
                .orElseThrow(AccountNotFoundException::new));
  }

  @Override
  public Account findAccountByNostroAccountNumber(String accountNumber) {
    return shadowAccountMapper
        .entityToDomain()
        .map(
            shadowAccountRepository
                .findByNostroAccountNumber(accountNumber)
                .orElseThrow(AccountNotFoundException::new));
  }
}
