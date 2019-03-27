package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.TransactionOperation;
import com.mislbd.ababil.foreignremittance.repository.schema.TransactionOperationEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionOperationMapper {

  public ResultMapper<TransactionOperationEntity, TransactionOperation> entityToDomain() {
    return entity ->
        new TransactionOperation()
            .setId(entity.getId())
            .setName(entity.getName())
            .setDescription(entity.getDescription())
            .setTypeId(entity.getTransactionTypeEntity().getId());
  }
}
