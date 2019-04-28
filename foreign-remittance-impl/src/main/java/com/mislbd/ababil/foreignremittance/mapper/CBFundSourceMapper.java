package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.CBFundSource;
import com.mislbd.ababil.foreignremittance.repository.schema.CBFundSourceEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class CBFundSourceMapper {
  public ResultMapper<CBFundSourceEntity, CBFundSource> entityToDomain() {
    return entity ->
        new CBFundSource()
            .setId(entity.getId())
            .setCode(entity.getCode())
            .setDescription(entity.getDescription());
  }
}
