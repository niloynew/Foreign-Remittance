package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.RemittanceCharge;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeEntity;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class RemittanceChargeMapper {

  private final RemittanceChargeSlabMapper remittanceChargeSlabMapper;
  private final RemittanceChargeRepository remittanceChargeRepository;

  public RemittanceChargeMapper(
      RemittanceChargeSlabMapper remittanceChargeSlabMapper,
      RemittanceChargeRepository remittanceChargeRepository) {
    this.remittanceChargeSlabMapper = remittanceChargeSlabMapper;
    this.remittanceChargeRepository = remittanceChargeRepository;
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
            .setChargeModifiable(entity.isChargeModifiable())
            .setVatModifiable(entity.isVatModifiable())
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
                        remittanceChargeSlabMapper.entityToDomain()))
            .setActive(entity.isActive());
  }

  public ResultMapper<RemittanceCharge, RemittanceChargeEntity> domainToEntity() {
    return domain ->
        remittanceChargeRepository
            .findById(domain.getId())
            .orElseGet(RemittanceChargeEntity::new)
            .setId(domain.getId())
            .setChargeName(domain.getChargeName())
            .setCurrencyCode(domain.getCurrencyCode())
            .setChargeAccountType(domain.getChargeAccountType())
            .setChargeAccountCode(domain.getChargeAccountCode())
            .setVatAccountType(domain.getVatAccountType())
            .setVatAccountCode(domain.getVatAccountCode())
            .setChargeModifiable(domain.isChargeModifiable())
            .setVatModifiable(domain.isVatModifiable())
            .setSlabBased(domain.isSlabBased())
            .setFixedCharge(domain.isFixedCharge())
            .setChargeAmount(domain.getChargeAmount())
            .setChargePercentage(domain.getChargePercentage())
            .setFixedVat(domain.isFixedVat())
            .setVatAmount(domain.getVatAmount())
            .setVatPercentage(domain.getVatPercentage())
            .setMaximumChargeAmount(domain.getMaximumChargeAmount())
            .setMinimumChargeAmount(domain.getMinimumChargeAmount())
            .setRemittanceChargeSlabs(
                domain.getRemittanceChargeSlabs() == null
                    ? null
                    : ListResultBuilder.build(
                        domain.getRemittanceChargeSlabs(),
                        remittanceChargeSlabMapper.domainToEntity()))
            .setActive(true);
  }
}
