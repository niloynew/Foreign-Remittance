package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.repository.jpa.IDProductRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroAccountEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class NostroAccountMapper {

  private final NostroAccountRepository nostroAccountRepository;
  private final IDProductRepository idProductRepository;

  public NostroAccountMapper(
      NostroAccountRepository nostroAccountRepository, IDProductRepository idProductRepository) {
    this.nostroAccountRepository = nostroAccountRepository;
    this.idProductRepository = idProductRepository;
  }

  public ResultMapper<NostroAccountEntity, Account> entityToDomain() {
    return entity ->
        new Account()
            .setId(entity.getId())
            .setNostroAccountNumber(entity.getNostroAccountNumber())
            .setShadowAccountNumber(entity.getShadowAccountNumber())
            .setAccountTitle(entity.getName())
            .setCurrencyCode(entity.getCurrencyCode())
            .setProductId(entity.getProduct().getId())
            .setProductName(entity.getProduct().getName())
            .setBankId(entity.getBankId())
            .setBranchId(entity.getBranchId())
            .setAccountOpenDate(entity.getAccOpenDate())
            .setBalanceCcy(entity.getBalanceCcy())
            .setBalanceLcy(entity.getBalanceLcy());
  }

  public ResultMapper<Account, NostroAccountEntity> domainToEntity() {
    return domain ->
        nostroAccountRepository
            .findByNostroAccountNumber(domain.getNostroAccountNumber())
            .orElseGet(NostroAccountEntity::new)
            .setProduct(
                domain.getProductId() != null
                    ? idProductRepository.getOne(domain.getProductId())
                    : null)
            .setNostroAccountNumber(domain.getNostroAccountNumber())
            .setShadowAccountNumber(domain.getShadowAccountNumber())
            .setName(domain.getAccountTitle())
            .setCurrencyCode(domain.getCurrencyCode())
            .setAccOpenDate(domain.getAccountOpenDate())
            .setBankId(domain.getBankId())
            .setBranchId(domain.getBranchId())
            .setBalanceCcy(domain.getBalanceCcy())
            .setActive(true);
  }
}
