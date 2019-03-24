package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeSlab;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeSlabEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class RemittanceChargeSlabMapper {

  // ToDo
  /*
   *   Need to finalize by adding financial charge id
   * */
  public ResultMapper<RemittanceChargeSlabEntity, RemittanceChargeSlab> entityToDomain() {
    return entity ->
        new RemittanceChargeSlab()
            .setId(entity.getId())
            .setFromAmount(entity.getFromAmount())
            .setToAmount(entity.getToAmount())
            .setFixedCharge(entity.isFixedCharge())
            .setChargeAmount(entity.getChargeAmount())
            .setPercentage(entity.getPercentage())
            .setMinimumChargeAmount(entity.getMinimumChargeAmount())
            .setMaximumChargeAmount(entity.getMaximumChargeAmount());
  }
}
