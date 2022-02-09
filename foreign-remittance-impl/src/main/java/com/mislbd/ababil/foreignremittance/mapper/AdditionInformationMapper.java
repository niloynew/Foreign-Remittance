package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.AdditionalInformation;
import com.mislbd.ababil.foreignremittance.repository.schema.AdditionalInformationEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class AdditionInformationMapper {

  public ResultMapper<AdditionalInformation, AdditionalInformationEntity> domainToEntity() {

    return domain ->
        new AdditionalInformationEntity()
            .setId(domain.getId())
            .setInstructedCurrency(
                domain.getInstructedCurrency() == null ? null : domain.getInstructedCurrency())
            .setInstructedAmount(
                domain.getInstructedAmount() == null ? null : domain.getInstructedAmount())
            .setCode(domain.getCode())
            .setTimeIndication(domain.getTimeIndication())
            .setSign(domain.getSign())
            .setOffset(domain.getOffset())
            .setExchangeRate(domain.getExchangeRate())
            .setSendersChargeCurrency(domain.getSendersChargeCurrency())
            .setSendersChargeAmount(domain.getSendersChargeAmount())
            .setReceiversChargeCurrency(domain.getReceiversChargeCurrency())
            .setReceiversChargeAmount(domain.getSendersChargeAmount())
            .setTransactionTypeCode(domain.getTransactionTypeCode())
            .setInstructionCode(domain.getInstructionCode())
            .setInstructionCodeAdditionalInformation(
                domain.getInstructionCodeAdditionalInformation())
            .setRegulatoryReportingCode(domain.getRegulatoryReportingCode())
            .setRegulatoryReportingCountryCode(domain.getRegulatoryReportingCountryCode())
            .setEnvelopContents(domain.getEnvelopContents())
            .setRegulatoryReportingCNarrative(domain.getRegulatoryReportingCNarrative())
            .setRemittanceInformation(domain.getRemittanceInformation())
            .setSenderToReceiverInformation(domain.getSenderToReceiverInformation());
  }

  public ResultMapper<AdditionalInformationEntity, AdditionalInformation> entityToDomain() {
    return entity ->
        new AdditionalInformation()
            .setId(entity.getId())
            .setInstructedCurrency(
                entity.getInstructedCurrency() == null ? null : entity.getInstructedCurrency())
            .setInstructedAmount(
                entity.getInstructedAmount() == null ? null : entity.getInstructedAmount())
            .setTimeIndication(entity.getTimeIndication())
            .setSign(entity.getSign())
            .setOffset(entity.getOffset())
            .setExchangeRate(entity.getExchangeRate())
            .setSendersChargeCurrency(entity.getSendersChargeCurrency())
            .setSendersChargeAmount(entity.getSendersChargeAmount())
            .setReceiversChargeCurrency(entity.getReceiversChargeCurrency())
            .setReceiversChargeAmount(entity.getSendersChargeAmount())
            .setTransactionTypeCode(entity.getTransactionTypeCode())
            .setInstructionCode(entity.getInstructionCode())
            .setInstructionCodeAdditionalInformation(
                entity.getInstructionCodeAdditionalInformation())
            .setRegulatoryReportingCode(entity.getRegulatoryReportingCode())
            .setRegulatoryReportingCountryCode(entity.getRegulatoryReportingCountryCode())
            .setEnvelopContents(entity.getEnvelopContents())
            .setRegulatoryReportingCNarrative(entity.getRegulatoryReportingCNarrative())
            .setRemittanceInformation(entity.getRemittanceInformation())
            .setSenderToReceiverInformation(entity.getSenderToReceiverInformation());
  }
}
