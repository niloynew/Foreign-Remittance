package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.BankType;
import com.mislbd.ababil.foreignremittance.repository.schema.BankTypeEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class BankTypeMapper {

    public ResultMapper<BankTypeEntity, BankType> entityToDomain() {
        return entity ->
                new BankType()
                        .setId(entity.getId())
                        .setName(entity.getName())
                        .setDescription(entity.getDescription());
    }
}
