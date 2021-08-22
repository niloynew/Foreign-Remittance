package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
import com.mislbd.ababil.foreignremittance.repository.jpa.IDProductRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.ababil.organization.service.AllBankBranchesService;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import com.mislbd.security.core.NgSession;
import org.springframework.stereotype.Component;

@Component
public class ShadowAccountMapper {

  private final ShadowAccountRepository shadowAccountRepository;
  private final IDProductRepository idProductRepository;
  private final NgSession ngSession;
  private final AllBankBranchesService allBankBranchesService;

  public ShadowAccountMapper(
      ShadowAccountRepository shadowAccountRepository,
      IDProductRepository idProductRepository,
      NgSession ngSession,
      AllBankBranchesService allBankBranchesService) {
    this.shadowAccountRepository = shadowAccountRepository;
    this.idProductRepository = idProductRepository;
    this.ngSession = ngSession;
    this.allBankBranchesService = allBankBranchesService;
  }

  public ResultMapper<ShadowAccountEntity, Account> entityToDomain(boolean bicRequired) {
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
            .setSenderToReceiverInformation(entity.getSenderToReceiverInformation())
            .setActive(entity.isActive())
            .setBranchBIC(
                bicRequired
                    ? allBankBranchesService
                        .getBranchInfoByBankIdAndBranchId(entity.getBankId(), entity.getBranchId())
                        .orElseThrow(
                            () ->
                                new ForeignRemittanceBaseException(
                                    "Branch not found with id "
                                        + entity.getBranchId()
                                        + ", and bank id "
                                        + entity.getBankId()))
                        .getSwiftCode()
                    : null);
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
            .setSenderToReceiverInformation(domain.getSenderToReceiverInformation())
            .setActive(true);
  }
}
