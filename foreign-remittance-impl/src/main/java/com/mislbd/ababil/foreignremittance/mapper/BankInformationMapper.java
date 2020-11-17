package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.BankInformation;
import com.mislbd.ababil.foreignremittance.repository.schema.BankInformationEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class BankInformationMapper {

  public ResultMapper<BankInformation, BankInformationEntity> domainToEntity() {
    return domain ->
        new BankInformationEntity()
            .setSwiftCode(domain.getSwiftCode())
            .setBankTypeId(domain.getBankTypeId());
  }

  public ResultMapper<BankInformationEntity, BankInformation> entityToDomain() {
    return entity ->
        new BankInformation()
            .setSwiftCode(entity.getSwiftCode())
            .setBankTypeId(entity.getBankTypeId());
  }
}
