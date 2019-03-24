package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.RemittanceCharge;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeEntity;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class RemittanceChargeMapper {

  private final RemittanceChargeSlabMapper remittanceChargeSlabMapper;

  public RemittanceChargeMapper(RemittanceChargeSlabMapper remittanceChargeSlabMapper) {
    this.remittanceChargeSlabMapper = remittanceChargeSlabMapper;
  }

  public ResultMapper<RemittanceChargeEntity, RemittanceCharge> entityToDomain() {
    return entity ->
        new RemittanceCharge()
            .setId(entity.getId())
            .setChargeName(entity.getChargeName())
            .setCurrencyCode(entity.getCurrencyCode())
            .setChargeAccountType(entity.getChargeAccountType())
            .setChargeAccountCode(entity.getChargeAccountCode())
            .setVatAccountType(entity.getVatAccountType())
            .setVatAccountCode(entity.getVatAccountCode())
            .setSlabBased(entity.isSlabBased())
            .setFixedCharge(entity.isFixedCharge())
            .setChargeAmount(entity.getChargeAmount())
            .setChargePercentage(entity.getChargePercentage())
            .setFixedVat(entity.isFixedVat())
            .setVatAmount(entity.getVatAmount())
            .setVatPercentage(entity.getVatPercentage())
            .setMaximumChargeAmount(entity.getMaximumChargeAmount())
            .setMinimumChargeAmount(entity.getMinimumChargeAmount())
            .setRemittanceChargeSlabs(
                entity.getRemittanceChargeSlabs() == null
                    ? null
                    : ListResultBuilder.build(
                        entity.getRemittanceChargeSlabs(),
                        remittanceChargeSlabMapper.entityToDomain()));
  }

  public ResultMapper<RemittanceCharge, RemittanceChargeEntity> domainToEntity() {
    return null;
  }
}
