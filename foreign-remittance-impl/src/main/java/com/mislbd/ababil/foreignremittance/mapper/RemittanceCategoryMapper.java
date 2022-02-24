package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.RemittanceCategory;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceCategoryEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class RemittanceCategoryMapper {

  public ResultMapper<RemittanceCategoryEntity, RemittanceCategory> entityToDomain() {
    return entity ->
        new RemittanceCategory()
            .setId(entity.getId())
            .setName(entity.getName())
            .setDescription(entity.getDescription());
  }
}
