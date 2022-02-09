package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.BankInformation;
import com.mislbd.ababil.foreignremittance.repository.schema.BankMappingEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class BankInformationMapper {

  public ResultMapper<BankInformation, BankMappingEntity> domainToEntity() {
    return domain -> new BankMappingEntity();
  }

  public ResultMapper<BankMappingEntity, BankInformation> entityToDomain() {
    return entity -> new BankInformation();
  }
}
