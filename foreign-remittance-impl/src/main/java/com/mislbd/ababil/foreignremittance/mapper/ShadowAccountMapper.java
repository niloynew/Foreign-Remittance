package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.repository.jpa.IDProductRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class ShadowAccountMapper {

  private final ShadowAccountRepository shadowAccountRepository;
  private final IDProductRepository idProductRepository;

  public ShadowAccountMapper(
      ShadowAccountRepository shadowAccountRepository, IDProductRepository idProductRepository) {
    this.shadowAccountRepository = shadowAccountRepository;
    this.idProductRepository = idProductRepository;
  }

  public ResultMapper<ShadowAccountEntity, Account> entityToDomain() {
    return entity ->
        new Account()
            .setId(entity.getId())
            .setProductId(entity.getProduct().getId())
            .setProductName(entity.getProduct().getName())
            .setShadowAccountNumber(entity.getNumber())
            .setAccountTitle(entity.getName())
            .setNostroAccountNumber(entity.getNostroAccountNumber())
            .setCurrencyCode(entity.getCurrencyCode())
            .setBankId(entity.getBankId())
            .setBranchId(entity.getBranchId())
            .setAccountOpenDate(entity.getAccountOpenDate())
            .setBalanceCcy(entity.getBalanceCcy())
            .setBalanceLcy(entity.getBalanceLcy());
  }

  public ResultMapper<Account, ShadowAccountEntity> domainToEntity() {
    return domain ->
        shadowAccountRepository
            .findByNumber(domain.getShadowAccountNumber())
            .orElseGet(ShadowAccountEntity::new)
            .setProduct(
                domain.getProductId() != null
                    ? idProductRepository.getOne(domain.getProductId())
                    : null)
            .setNumber(domain.getShadowAccountNumber())
            .setName(domain.getAccountTitle())
            .setNostroAccountNumber(domain.getNostroAccountNumber())
            .setCurrencyCode(domain.getCurrencyCode())
            .setAccountOpenDate(domain.getAccountOpenDate())
            .setBankId(domain.getBankId())
            .setBranchId(domain.getBranchId())
            .setBalanceCcy(domain.getBalanceCcy())
            .setActive(true);
  }
}
