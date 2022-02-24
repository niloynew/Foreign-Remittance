package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.AdditionalInformation;
import com.mislbd.ababil.foreignremittance.repository.jpa.AdditionalInformationRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.AdditionalInformationEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class AdditionInformationMapper {

  private final AdditionalInformationRepository additionalInformationRepository;

  public AdditionInformationMapper(
      AdditionalInformationRepository additionalInformationRepository) {
    this.additionalInformationRepository = additionalInformationRepository;
  }

  public ResultMapper<AdditionalInformation, AdditionalInformationEntity> domainToEntity() {

    return domain ->
        additionalInformationRepository
            .findById(domain.getId())
            .orElseGet(AdditionalInformationEntity::new)
            .setCode(domain.getCode())
            .setTimeIndication(domain.getTimeIndication())
            .setSign(domain.getSign())
            .setOffset(domain.getOffset())
            .setInstructedCurrency(
                domain.getInstructedCurrency() == null ? null : domain.getInstructedCurrency())
            .setInstructedAmount(
                domain.getInstructedAmount() == null ? null : domain.getInstructedAmount())
            .setExchangeRate(domain.getExchangeRate())
            .setDetailsOfCharges(domain.getDetailsOfCharges())
            .setSendersChargeCurrency(domain.getSendersChargeCurrency())
            .setSendersChargeAmount(domain.getSendersChargeAmount())
            .setReceiversChargeCurrency(domain.getReceiversChargeCurrency())
            .setReceiversChargeAmount(domain.getSendersChargeAmount())
            .setBankOperationCode(domain.getBankOperationCode())
            .setTransactionTypeCode(domain.getTransactionTypeCode())
            .setInstructionCode(domain.getInstructionCode())
            .setInstructionCodeAdditionalInformation(
                domain.getInstructionCodeAdditionalInformation())
            .setRemittanceInformation(domain.getRemittanceInformation())
            .setSenderToReceiverInformation(domain.getSenderToReceiverInformation())
            .setRegulatoryReportingCode(domain.getRegulatoryReportingCode())
            .setRegulatoryReportingCountryCode(domain.getRegulatoryReportingCountryCode())
            .setRegulatoryReportingCNarrative(domain.getRegulatoryReportingCNarrative())
            .setEnvelopContents(domain.getEnvelopContents());
  }

  public ResultMapper<AdditionalInformationEntity, AdditionalInformation> entityToDomain() {
    return entity ->
        new AdditionalInformation()
            .setId(entity.getId())
            .setCode(entity.getCode())
            .setTimeIndication(entity.getTimeIndication())
            .setSign(entity.getSign())
            .setOffset(entity.getOffset())
            .setInstructedCurrency(
                entity.getInstructedCurrency() == null ? null : entity.getInstructedCurrency())
            .setInstructedAmount(
                entity.getInstructedAmount() == null ? null : entity.getInstructedAmount())
            .setExchangeRate(entity.getExchangeRate())
            .setDetailsOfCharges(entity.getDetailsOfCharges())
            .setSendersChargeCurrency(entity.getSendersChargeCurrency())
            .setSendersChargeAmount(entity.getSendersChargeAmount())
            .setReceiversChargeCurrency(entity.getReceiversChargeCurrency())
            .setReceiversChargeAmount(entity.getSendersChargeAmount())
            .setBankOperationCode(entity.getBankOperationCode())
            .setTransactionTypeCode(entity.getTransactionTypeCode())
            .setInstructionCode(entity.getInstructionCode())
            .setInstructionCodeAdditionalInformation(
                entity.getInstructionCodeAdditionalInformation())
            .setRemittanceInformation(entity.getRemittanceInformation())
            .setSenderToReceiverInformation(entity.getSenderToReceiverInformation())
            .setRegulatoryReportingCode(entity.getRegulatoryReportingCode())
            .setRegulatoryReportingCountryCode(entity.getRegulatoryReportingCountryCode())
            .setRegulatoryReportingCNarrative(entity.getRegulatoryReportingCNarrative())
            .setEnvelopContents(entity.getEnvelopContents());
  }
}
