package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.RemittanceCharge;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class RemittanceChargeMapper {

  public ResultMapper<RemittanceChargeEntity, RemittanceCharge> entityToDomain() {
    return null;
  }

  public ResultMapper<RemittanceCharge, RemittanceChargeEntity> domainToEntity() {
    return null;
  }
}
