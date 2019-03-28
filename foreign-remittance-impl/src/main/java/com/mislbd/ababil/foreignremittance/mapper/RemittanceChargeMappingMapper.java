package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeMapping;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeMappingRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionOperationRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeMappingEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class RemittanceChargeMappingMapper {

  private final RemittanceChargeMappingRepository remittanceChargeMappingRepository;
  private final RemittanceChargeRepository remittanceChargeRepository;
  private final TransactionOperationRepository transactionOperationRepository;

  public RemittanceChargeMappingMapper(
      RemittanceChargeMappingRepository remittanceChargeMappingRepository,
      RemittanceChargeRepository remittanceChargeRepository,
      TransactionOperationRepository transactionOperationRepository) {
    this.remittanceChargeMappingRepository = remittanceChargeMappingRepository;
    this.remittanceChargeRepository = remittanceChargeRepository;
    this.transactionOperationRepository = transactionOperationRepository;
  }

  public ResultMapper<RemittanceChargeMappingEntity, RemittanceChargeMapping> entityToDomain() {
    return entity ->
        new RemittanceChargeMapping()
            .setId(entity.getId())
            .setChargeId(entity.getRemittanceCharge().getId())
            .setChargeName(entity.getRemittanceCharge().getChargeName())
            .setOperationId(entity.getTransactionOperation().getId())
            .setOperationName(entity.getTransactionOperation().getName())
            .setChargeModifiable(entity.isChargeModifiable())
            .setTypeName(entity.getTransactionOperation().getTransactionTypeEntity().getName());
  }

  public ResultMapper<RemittanceChargeMapping, RemittanceChargeMappingEntity> domainToEntity() {
    return domain ->
        remittanceChargeMappingRepository
            .findById(domain.getId())
            .orElseGet(RemittanceChargeMappingEntity::new)
            .setId(domain.getId())
            .setChargeModifiable(domain.getChargeModifiable())
            .setRemittanceCharge(remittanceChargeRepository.findById(domain.getChargeId()).get())
            .setTransactionOperation(
                transactionOperationRepository.findById(domain.getOperationId()).get());
  }
}
