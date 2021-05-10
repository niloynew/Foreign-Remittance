package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.BankInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceAdditionalInformation;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceAdditionalInformationEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionBankMappingEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class AdditionInformationMapper {

    public ResultMapper<RemittanceAdditionalInformation, RemittanceAdditionalInformationEntity> domainToEntity() {
        return domain ->
                new RemittanceAdditionalInformationEntity()

                        .setInstructedCurrency(domain.getInstructedCurrency())
                        .setInstructedAmount(domain.getInstructedAmount());
    }

    public ResultMapper<RemittanceAdditionalInformationEntity, RemittanceAdditionalInformation> entityToDomain() {
        return entity ->
                new RemittanceAdditionalInformation()
                        .setInstructedCurrency(entity.getInstructedCurrency())
                        .setInstructedAmount(entity.getInstructedAmount());
    }
}
