package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.mapper.ShadowAccountMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class ShadowAccountServiceImpl implements IdAccountService {

    private final ShadowAccountRepository repository;
    private final ShadowAccountMapper idAccountMapper;

    public ShadowAccountServiceImpl(ShadowAccountRepository repository, ShadowAccountMapper idAccountMapper) {
        this.repository = repository;
        this.idAccountMapper = idAccountMapper;
    }

    @Override
    public PagedResult<Account> getAccounts(Pageable pageable) {
        Page<ShadowAccountEntity> pagedIdAccountEntities =
                repository.findAll(pageable);
        return PagedResultBuilder.build(
                pagedIdAccountEntities, idAccountMapper.entityToDomain());

    }

    @Override
    public Optional<Account> getIdAccount(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean isExists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Account findByNumber(String accNumber) {
        return(idAccountMapper.entityToDomain().map(repository.findByNumber(accNumber))) ;
    }

    @Override
    public BigDecimal getBalanceCcy(String accNum) {
        return null;
    }

    @Override
    public BigDecimal getBalanceLcy(String accNum) {
        return null;
    }
}
