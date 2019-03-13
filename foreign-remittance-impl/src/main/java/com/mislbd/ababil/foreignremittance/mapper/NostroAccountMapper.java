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
                        .setId(Long.valueOf(entity.getId()))
                        .setNostroAccountNumber(entity.getNumber())
                        .setAccountTitle(entity.getName())
                        .setCurrencyCode(entity.getCurrencyCode())
                        .setProductId(String.valueOf(entity.getProduct().getId()))
                        .setBankId(entity.getBankId() != null ? String.valueOf(entity.getBankId()) : null)
                        .setBranchId(entity.getBranchId() != null ? String.valueOf(entity.getBranchId()) : null)
                        .setAccountOpenDate(entity.getAccOpenDate())
                        .setBalanceCcy(entity.getBalanceCcy())
                        .setBalanceLcy(entity.getBalanceLcy());
    }

    public ResultMapper<Account, NostroAccountEntity> domainToEntity() {
        return domain ->
                nostroAccountRepository
                        .findByNumber(domain.getNostroAccountNumber())
                        .orElseGet(NostroAccountEntity::new)
                        .setProduct(
                                domain.getProductId() != null
                                        ? idProductRepository.getOne(Long.valueOf(domain.getProductId()))
                                        : null)
                        .setNumber(domain.getNostroAccountNumber())
                        .setName(domain.getAccountTitle())
                        .setCurrencyCode(domain.getCurrencyCode())
                        .setAccOpenDate(domain.getAccountOpenDate())
                        .setBankId(domain.getBankId() != null ? Long.valueOf(domain.getBankId()) : null)
                        .setBranchId(domain.getBranchId() != null ? Long.valueOf(domain.getBranchId()) : null)
                        .setBalanceCcy(domain.getBalanceCcy());
    }
}
