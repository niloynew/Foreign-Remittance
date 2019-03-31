package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.HORateType;
import com.mislbd.ababil.foreignremittance.repository.schema.HORateTypeEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class HORateTypeMapper {

  public ResultMapper<HORateTypeEntity, HORateType> entityToDomain() {
    return entity ->
        new HORateType()
            .setId(entity.getId())
            .setName(entity.getName())
            .setDescription(entity.getDescription());
  }
}
