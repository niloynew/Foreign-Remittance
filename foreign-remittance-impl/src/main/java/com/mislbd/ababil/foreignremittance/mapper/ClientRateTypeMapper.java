package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.ClientRateType;
import com.mislbd.ababil.foreignremittance.repository.schema.ClientRateTypeEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientRateTypeMapper {

  public ResultMapper<ClientRateTypeEntity, ClientRateType> entityToDomain() {
    return entity ->
        new ClientRateType()
            .setId(entity.getId())
            .setName(entity.getName())
            .setDescription(entity.getDescription());
  }
}
