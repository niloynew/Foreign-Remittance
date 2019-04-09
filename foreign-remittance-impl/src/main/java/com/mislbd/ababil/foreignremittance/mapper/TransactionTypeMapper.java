package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.TransactionType;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionTypeRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.TransactionTypeEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionTypeMapper {

  private final TransactionTypeRepository transactionTypeRepository;

  public TransactionTypeMapper(TransactionTypeRepository transactionTypeRepository) {
    this.transactionTypeRepository = transactionTypeRepository;
  }

  public ResultMapper<TransactionTypeEntity, TransactionType> entityToDomain() {
    return entity ->
        new TransactionType()
            .setId(entity.getId())
            .setName(entity.getName())
            .setRemittanceType(entity.getRemittanceType())
            .setDescription(entity.getDescription());
  }

  public ResultMapper<TransactionType, TransactionTypeEntity> domainToEntity() {
    return domain ->
        transactionTypeRepository
            .findById(domain.getId())
            .orElseGet(TransactionTypeEntity::new)
            .setName(domain.getName())
            .setDescription(domain.getDescription())
            .setRemittanceType(domain.getRemittanceType());
  }
}
