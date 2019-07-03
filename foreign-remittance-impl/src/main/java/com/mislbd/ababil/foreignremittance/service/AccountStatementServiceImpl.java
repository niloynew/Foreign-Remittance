package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.AccountStatement;
import com.mislbd.ababil.foreignremittance.mapper.AccountStatementMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.AccountStatementRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.AccountStatementEntity;
import com.mislbd.ababil.foreignremittance.repository.specification.AccountStatementSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountStatementServiceImpl implements AccountStatementService {

    private final AccountStatementRepository accountStatementRepository;
    private final AccountStatementMapper accountStatementMapper;

    public AccountStatementServiceImpl(AccountStatementRepository accountStatementRepository, AccountStatementMapper accountStatementMapper) {
        this.accountStatementRepository = accountStatementRepository;
        this.accountStatementMapper = accountStatementMapper;
    }


    @Override
    public PagedResult<AccountStatement> getAccountStatements(Pageable pageable, Long accountId, LocalDate fromDate, LocalDate toDate) {
        Page<AccountStatementEntity> accountStatement = accountStatementRepository.findAll(AccountStatementSpecification.searchSpecification(accountId, fromDate, toDate), pageable);
        return PagedResultBuilder.build(accountStatement, accountStatementMapper.entityToDomain());
    }

    @Override
    public List<AccountStatement> getAccountStatements(Long accountId, LocalDate fromDate, LocalDate toDate) {
        List<AccountStatementEntity> accountStatement = accountStatementRepository.findAll(AccountStatementSpecification.searchSpecification(accountId, fromDate, toDate));
        return ListResultBuilder.build(accountStatement, accountStatementMapper.entityToDomain());
    }
}
