package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeInformation;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeInformationEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class RemittanceChargeInformationMapper {

  public ResultMapper<RemittanceChargeInformation, RemittanceChargeInformationEntity>
      domainToEntity() {
    return domain ->
        new RemittanceChargeInformationEntity()
            .setChargeId(domain.getChargeId())
            .setChargeName(domain.getChargeName())
            .setChargeAmount(domain.getChargeAmount())
            .setVatAmount(domain.getVatAmount())
            .setCurrency(domain.getCurrency());
  }
}
