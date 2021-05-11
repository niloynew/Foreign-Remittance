package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.RemittanceAdditionalInformation;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceAdditionalInformationEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class AdditionInformationMapper {

  public ResultMapper<RemittanceAdditionalInformation, RemittanceAdditionalInformationEntity>
      domainToEntity() {

    return domain ->
        new RemittanceAdditionalInformationEntity()
            .setId(domain.getId())
            .setInstructedCurrency(
                domain.getInstructedCurrency() == null ? null : domain.getInstructedCurrency())
            .setInstructedAmount(
                domain.getInstructedAmount() == null ? null : domain.getInstructedAmount());
  }

  public ResultMapper<RemittanceAdditionalInformationEntity, RemittanceAdditionalInformation>
      entityToDomain() {
    return entity ->
        new RemittanceAdditionalInformation()
            .setId(entity.getId())
            .setInstructedCurrency(
                entity.getInstructedCurrency() == null ? null : entity.getInstructedCurrency())
            .setInstructedAmount(
                entity.getInstructedAmount() == null ? null : entity.getInstructedAmount());
  }
}
