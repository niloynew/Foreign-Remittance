package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.RateType;
import com.mislbd.ababil.foreignremittance.repository.schema.RateTypeEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class RateTypeMapper {

  public ResultMapper<RateTypeEntity, RateType> entityToDomain() {
    return entity ->
        new RateType()
            .setId(entity.getId())
            .setCode(entity.getCode())
            .setName(entity.getName())
            .setDescription(entity.getDescription());
  }
}
