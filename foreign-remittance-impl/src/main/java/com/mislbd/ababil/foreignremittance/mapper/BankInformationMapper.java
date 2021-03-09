package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.BankInformation;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionBankMappingEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class BankInformationMapper {

  public ResultMapper<BankInformation, RemittanceTransactionBankMappingEntity> domainToEntity() {
    return domain ->
        new RemittanceTransactionBankMappingEntity()
            .setSwiftCode(domain.getSwiftCode())
            .setBankTypeId(domain.getBankTypeId());
  }

  public ResultMapper<RemittanceTransactionBankMappingEntity, BankInformation> entityToDomain() {
    return entity ->
        new BankInformation()
            .setSwiftCode(entity.getSwiftCode())
            .setBankTypeId(entity.getBankTypeId());
  }
}
