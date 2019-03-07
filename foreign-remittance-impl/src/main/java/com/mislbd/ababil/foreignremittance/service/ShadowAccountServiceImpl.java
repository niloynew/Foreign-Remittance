package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.mapper.ShadowAccountMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.ShadowAccountSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
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
  public PagedResult<Account> findActiveAccounts(Pageable pageable) {
    return PagedResultBuilder.build(
        shadowAccountRepository.findAll(ShadowAccountSpecification.searchSpecification(), pageable),
        shadowAccountMapper.entityToDomain());
  }

  @Override
  public List<Account> findActiveAccounts() {
    return ListResultBuilder.build(
        shadowAccountRepository.findAll(ShadowAccountSpecification.searchSpecification()),
        shadowAccountMapper.entityToDomain());
  }
}
