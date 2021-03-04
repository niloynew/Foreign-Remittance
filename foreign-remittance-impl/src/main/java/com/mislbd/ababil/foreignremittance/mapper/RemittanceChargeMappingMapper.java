package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeMapping;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeMappingRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionTypeRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeMappingEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class RemittanceChargeMappingMapper {

  private final RemittanceChargeMappingRepository remittanceChargeMappingRepository;
  private final RemittanceChargeRepository remittanceChargeRepository;
  private final TransactionTypeRepository transactionTypeRepository;

  public RemittanceChargeMappingMapper(
      RemittanceChargeMappingRepository remittanceChargeMappingRepository,
      RemittanceChargeRepository remittanceChargeRepository,
      TransactionTypeRepository transactionTypeRepository) {
    this.remittanceChargeMappingRepository = remittanceChargeMappingRepository;
    this.remittanceChargeRepository = remittanceChargeRepository;
    this.transactionTypeRepository = transactionTypeRepository;
  }

  public ResultMapper<RemittanceChargeMappingEntity, RemittanceChargeMapping> entityToDomain() {
    return entity ->
        new RemittanceChargeMapping()
            .setId(entity.getId())
            .setRemittanceType(entity.getRemittanceType())
            .setChargeId(entity.getRemittanceCharge().getId())
            .setChargeName(entity.getRemittanceCharge().getChargeName())
            .setChargeModifiable(entity.isChargeModifiable())
            .setVatModifiable(entity.isVatModifiable())
            .setTypeName(
                entity.getTransactionType() != null ? entity.getTransactionType().getName() : null)
            .setTypeId(
                entity.getTransactionType() != null ? entity.getTransactionType().getId() : null);
  }

  public ResultMapper<RemittanceChargeMapping, RemittanceChargeMappingEntity> domainToEntity() {
    return domain ->
        remittanceChargeMappingRepository
            .findById(domain.getId())
            .orElseGet(RemittanceChargeMappingEntity::new)
            .setId(domain.getId())
            .setRemittanceType(domain.getRemittanceType())
            .setChargeModifiable(domain.getChargeModifiable())
            .setVatModifiable(domain.getVatModifiable())
            .setRemittanceCharge(remittanceChargeRepository.findById(domain.getChargeId()).get())
            .setTransactionType(transactionTypeRepository.getOne(domain.getTypeId()));
  }
}
