package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.exception.AccountNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.NostroAccountMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class NostroAccountServiceImpl implements NostroAccountService {

  private final NostroAccountRepository repository;
  private final NostroAccountMapper mapper;

  public NostroAccountServiceImpl(NostroAccountRepository repository, NostroAccountMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public Account findById(Long id) {
    return mapper
        .entityToDomain()
        .map(repository.findById(id).orElseThrow(AccountNotFoundException::new));
  }
}
