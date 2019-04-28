package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.PaymentPurpose;
import com.mislbd.ababil.foreignremittance.repository.schema.PaymentPurposeEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentPurposeMapper {

  public ResultMapper<PaymentPurposeEntity, PaymentPurpose> entityToDomain() {
    return entity ->
        new PaymentPurpose()
            .setId(entity.getId())
            .setLevel(entity.getLevel())
            .setCode(entity.getCode())
            .setDescription(entity.getDescription());
  }
}
