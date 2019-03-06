package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.IdAccount;
import com.mislbd.ababil.foreignremittance.mapper.IdAccountMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.IdAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.IdAccountEntity;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IdAccountServiceImpl implements IdAccountService {

  private final IdAccountRepository repository;
  private final IdAccountMapper idAccountMapper;

  public IdAccountServiceImpl(IdAccountRepository repository, IdAccountMapper idAccountMapper) {
    this.repository = repository;
    this.idAccountMapper = idAccountMapper;
  }

  @Override
  public PagedResult<IdAccount> getAccounts(Pageable pageable) {
    Page<IdAccountEntity> pagedIdAccountEntities = repository.findAll(pageable);
    return PagedResultBuilder.build(pagedIdAccountEntities, idAccountMapper.entityToDomain());
  }

  @Override
  public Optional<IdAccount> getIdAccount(Long id) {
    return Optional.empty();
  }

  @Override
  public boolean isExists(Long id) {
    return repository.existsById(id);
  }

  @Override
  public IdAccount findByNumber(String accNumber) {
    return (idAccountMapper.entityToDomain().map(repository.findByNumber(accNumber)));
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
