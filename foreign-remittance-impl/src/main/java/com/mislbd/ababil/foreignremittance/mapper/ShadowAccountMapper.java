package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.repository.jpa.IDProductRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import com.mislbd.security.core.NgSession;
import org.springframework.stereotype.Component;

@Component
public class ShadowAccountMapper {

  private final ShadowAccountRepository shadowAccountRepository;
  private final IDProductRepository idProductRepository;
  private final NgSession ngSession;

  public ShadowAccountMapper(
      ShadowAccountRepository shadowAccountRepository,
      IDProductRepository idProductRepository,
      NgSession ngSession) {
    this.shadowAccountRepository = shadowAccountRepository;
    this.idProductRepository = idProductRepository;
    this.ngSession = ngSession;
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
            .setBalance(entity.getBalance())
            .setBlockAmount(entity.getBlockAmount())
            .setActive(entity.isActive());
  }

  public ResultMapper<Account, ShadowAccountEntity> domainToEntity() {
    return domain ->
        shadowAccountRepository
            .findByNumber(domain.getShadowAccountNumber())
            .orElseGet(ShadowAccountEntity::new)
            .setId(domain.getId())
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
            .setBalance(domain.getBalance())
            .setBlockAmount(domain.getBlockAmount())
            .setOwnerBranchId(ngSession.getUserBranch())
            .setActive(true);
  }
}
