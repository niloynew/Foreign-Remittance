package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeSlab;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeSlabRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeSlabEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class RemittanceChargeSlabMapper {

  private final RemittanceChargeSlabRepository remittanceChargeSlabRepository;

  public RemittanceChargeSlabMapper(RemittanceChargeSlabRepository remittanceChargeSlabRepository) {
    this.remittanceChargeSlabRepository = remittanceChargeSlabRepository;
  }

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

  public ResultMapper<RemittanceChargeSlab, RemittanceChargeSlabEntity> domainToEntity() {

    return domain ->
        remittanceChargeSlabRepository
            .findById(domain.getId())
            .orElseGet(RemittanceChargeSlabEntity::new)
            .setFromAmount(domain.getFromAmount())
            .setToAmount(domain.getToAmount())
            .setFixedCharge(domain.isFixedCharge())
            .setChargeAmount(domain.getChargeAmount())
            .setPercentage(domain.getPercentage())
            .setMinimumChargeAmount(domain.getMinimumChargeAmount())
            .setMaximumChargeAmount(domain.getMaximumChargeAmount());
  }
}
