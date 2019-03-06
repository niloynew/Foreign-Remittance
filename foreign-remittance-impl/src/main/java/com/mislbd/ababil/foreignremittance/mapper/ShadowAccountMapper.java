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

    public ShadowAccountMapper(ShadowAccountRepository shadowAccountRepository, IDProductRepository idProductRepository) {
        this.shadowAccountRepository = shadowAccountRepository;
        this.idProductRepository = idProductRepository;
    }

    public ResultMapper<ShadowAccountEntity, Account> entityToDomain(){
        return entity -> new Account()
                .setId(entity.getId())
                .setProductId(String.valueOf(entity.getProduct().getId()))
                .setShadowAccountNumber(entity.getNumber())
                .setCurrencyCode(entity.getCurrencyCode())
                .setBankId(entity.getBankId() != null ? String.valueOf(entity.getBankId()) : null)
                .setBranchId(entity.getBranchId()!= null ? String.valueOf(entity.getBranchId()) : null)
                .setAccountOpenDate(entity.getAccountOpenDate())
                .setBalanceCcy(entity.getBalanceCcy())
                .setBalanceLcy(entity.getBalanceLcy());


    }

    public ResultMapper<Account, ShadowAccountEntity> domainToEntity(){
        return domain ->
                shadowAccountRepository
                        .findById(domain.getId())
                        .orElseGet(ShadowAccountEntity::new)
                        .setProduct(domain.getProductId() != null ? idProductRepository.getOne(Long.valueOf(domain.getProductId())) : null)
                        .setNumber(domain.getShadowAccountNumber())
                        .setName(domain.getAccountTitle())
                        .setCurrencyCode(domain.getCurrencyCode())
                        .setAccountOpenDate(domain.getAccountOpenDate())
                        .setBankId(domain.getBankId() != null ? Long.valueOf(domain.getBankId()) : null)
                        .setBranchId(domain.getBranchId() != null ? Long.valueOf(domain.getBranchId()) : null)
                        .setBalanceCcy(domain.getBalanceCcy());
    }
}
