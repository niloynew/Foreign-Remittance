package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.mapper.ShadowAccountMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ShadowAccountServiceImpl implements ShadowAccountService {

    private final ShadowAccountRepository shadowAccountRepository;
    private final ShadowAccountMapper shadowAccountMapper;

    public ShadowAccountServiceImpl(ShadowAccountRepository shadowAccountRepository, ShadowAccountMapper shadowAccountMapper) {
        this.shadowAccountRepository = shadowAccountRepository;
        this.shadowAccountMapper = shadowAccountMapper;
    }

    @Override
    public PagedResult<Account> findActiveAccounts(Pageable pageable) {
        return PagedResultBuilder.build(shadowAccountRepository.findAll(pageable), shadowAccountMapper.entityToDomain());
    }

    @Override
    public List<Account> findActiveAccounts() {
        return ListResultBuilder.build(shadowAccountRepository.findAll(), shadowAccountMapper.entityToDomain());
    }

}
