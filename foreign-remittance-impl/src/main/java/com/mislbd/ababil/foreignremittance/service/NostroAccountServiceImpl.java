package com.mislbd.ababil.foreignremittance.service;

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

  //    @Override
  //    public PagedResult<NostroAccount> getAccounts(Pageable pageable) {
  //        Page<NostroAccountEntity> pagedNostroAccountEntities =
  //                repository.findAll(pageable);
  //        return PagedResultBuilder.build(
  //                pagedNostroAccountEntities, mapper.entityToDomain());
  //
  //    }
  //
  //    @Override
  //    public Optional<NostroAccount> getIdAccount(Long id) {
  //        Optional<NostroAccountEntity> nostroAccountEntity = repository.findById(id);
  //        return nostroAccountEntity.map(mapper.entityToDomain()::map);
  //
  //    }
  //
  //    @Override
  //    public boolean isExists(Long id) {
  //        return false;
  //    }
  //
  //    @Override
  //    public NostroAccount findByNumber(String accNumber) {
  //        return null;
  //    }
  //
  //    @Override
  //    public BigDecimal getBalanceCcy(String accNum) {
  //        return null;
  //    }
  //
  //    @Override
  //    public BigDecimal getBalanceLimits(String accNum) {
  //        return null;
  //    }
}
