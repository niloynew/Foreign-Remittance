package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.AdditionalInformation;
import com.mislbd.ababil.foreignremittance.repository.schema.AdditionalInformationEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class AdditionInformationMapper {

  public ResultMapper<AdditionalInformation, AdditionalInformationEntity>
      domainToEntity() {

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
            .setSenderToReceiverInformation(domain.getSenderToReceiverInformation())
            .setSendingInstitutePartyIdentifier(domain.getSendingInstitutePartyIdentifier())
            .setSendingInstituteIdentifierCode(domain.getSendingInstituteIdentifierCode())
            .setSelectedOrderingInstitutionOption(domain.getSelectedOrderingInstitutionOption())
            .setOrderingInstitutionPartyIdentifier(domain.getOrderingInstitutionPartyIdentifier())
            .setOrderingInstitutionIdentifierCode(domain.getOrderingInstitutionIdentifierCode())
            .setOrderingInstitutionPartyNameAndAddress(
                domain.getOrderingInstitutionPartyNameAndAddress())
            .setSelectedSendersCorrespondentOption(domain.getSelectedSendersCorrespondentOption())
            .setSendersCorrespondentPartyIdentifier(domain.getSendersCorrespondentPartyIdentifier())
            .setSendersCorrespondentIdentifierCode(domain.getSendersCorrespondentIdentifierCode())
            .setSendersCorrespondentLocation(domain.getSendersCorrespondentLocation())
            .setSendersCorrespondentNameAndAddress(domain.getSendersCorrespondentNameAndAddress())
            .setSelectedReceiversCorrespondentOption(
                domain.getSelectedReceiversCorrespondentOption())
            .setReceiversCorrespondentPartyIdentifier(
                domain.getReceiversCorrespondentPartyIdentifier())
            .setReceiversCorrespondentIdentifierCode(
                domain.getReceiversCorrespondentIdentifierCode())
            .setReceiversCorrespondentLocation(domain.getReceiversCorrespondentLocation())
            .setReceiversCorrespondentNameAndAddress(
                domain.getReceiversCorrespondentNameAndAddress())
            .setSelectedThirdReimbursementInstitutionOption(
                domain.getSelectedThirdReimbursementInstitutionOption())
            .setThirdReimbursementInstitutionPartyIdentifier(
                domain.getThirdReimbursementInstitutionPartyIdentifier())
            .setThirdReimbursementInstitutionIdentifierCode(
                domain.getThirdReimbursementInstitutionIdentifierCode())
            .setThirdReimbursementInstitutionLocation(
                domain.getThirdReimbursementInstitutionLocation())
            .setThirdReimbursementInstitutionNameAndAddress(
                domain.getThirdReimbursementInstitutionNameAndAddress())
            .setSelectedIntermediaryInstitutionOption(
                domain.getSelectedIntermediaryInstitutionOption())
            .setIntermediaryInstitutionPartyIdentifier(
                domain.getIntermediaryInstitutionPartyIdentifier())
            .setIntermediaryInstitutionIdentifierCode(
                domain.getIntermediaryInstitutionIdentifierCode())
            .setIntermediaryInstitutionIdentifierNameAndAddress(
                domain.getIntermediaryInstitutionIdentifierNameAndAddress())
            .setSelectedAccountWithInstitutionOption(
                domain.getSelectedAccountWithInstitutionOption())
            .setAccountWithInstitutionPartyIdentifier(
                domain.getAccountWithInstitutionPartyIdentifier())
            .setAccountWithInstitutionIdentifierCode(
                domain.getAccountWithInstitutionIdentifierCode())
            .setAccountWithInstitutionPartyLocation(domain.getAccountWithInstitutionPartyLocation())
            .setAccountWithInstitutionPartyNameAndAddress(
                domain.getAccountWithInstitutionPartyNameAndAddress());
  }

  public ResultMapper<AdditionalInformationEntity, AdditionalInformation>
      entityToDomain() {
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
            .setSenderToReceiverInformation(entity.getSenderToReceiverInformation())
            .setSendingInstitutePartyIdentifier(entity.getSendingInstitutePartyIdentifier())
            .setSendingInstituteIdentifierCode(entity.getSendingInstituteIdentifierCode())
            .setSelectedOrderingInstitutionOption(entity.getSelectedOrderingInstitutionOption())
            .setOrderingInstitutionPartyIdentifier(entity.getOrderingInstitutionPartyIdentifier())
            .setOrderingInstitutionIdentifierCode(entity.getOrderingInstitutionIdentifierCode())
            .setOrderingInstitutionPartyNameAndAddress(
                entity.getOrderingInstitutionPartyNameAndAddress())
            .setSelectedSendersCorrespondentOption(entity.getSelectedSendersCorrespondentOption())
            .setSendersCorrespondentPartyIdentifier(entity.getSendersCorrespondentPartyIdentifier())
            .setSendersCorrespondentIdentifierCode(entity.getSendersCorrespondentIdentifierCode())
            .setSendersCorrespondentLocation(entity.getSendersCorrespondentLocation())
            .setSendersCorrespondentNameAndAddress(entity.getSendersCorrespondentNameAndAddress())
            .setSelectedReceiversCorrespondentOption(
                entity.getSelectedReceiversCorrespondentOption())
            .setReceiversCorrespondentPartyIdentifier(
                entity.getReceiversCorrespondentPartyIdentifier())
            .setReceiversCorrespondentIdentifierCode(
                entity.getReceiversCorrespondentIdentifierCode())
            .setReceiversCorrespondentLocation(entity.getReceiversCorrespondentLocation())
            .setReceiversCorrespondentNameAndAddress(
                entity.getReceiversCorrespondentNameAndAddress())
            .setSelectedThirdReimbursementInstitutionOption(
                entity.getSelectedThirdReimbursementInstitutionOption())
            .setThirdReimbursementInstitutionPartyIdentifier(
                entity.getThirdReimbursementInstitutionPartyIdentifier())
            .setThirdReimbursementInstitutionIdentifierCode(
                entity.getThirdReimbursementInstitutionIdentifierCode())
            .setThirdReimbursementInstitutionLocation(
                entity.getThirdReimbursementInstitutionLocation())
            .setThirdReimbursementInstitutionNameAndAddress(
                entity.getThirdReimbursementInstitutionNameAndAddress())
            .setSelectedIntermediaryInstitutionOption(
                entity.getSelectedIntermediaryInstitutionOption())
            .setIntermediaryInstitutionPartyIdentifier(
                entity.getIntermediaryInstitutionPartyIdentifier())
            .setIntermediaryInstitutionIdentifierCode(
                entity.getIntermediaryInstitutionIdentifierCode())
            .setIntermediaryInstitutionIdentifierNameAndAddress(
                entity.getIntermediaryInstitutionIdentifierNameAndAddress())
            .setSelectedAccountWithInstitutionOption(
                entity.getSelectedAccountWithInstitutionOption())
            .setAccountWithInstitutionPartyIdentifier(
                entity.getAccountWithInstitutionPartyIdentifier())
            .setAccountWithInstitutionIdentifierCode(
                entity.getAccountWithInstitutionIdentifierCode())
            .setAccountWithInstitutionPartyLocation(entity.getAccountWithInstitutionPartyLocation())
            .setAccountWithInstitutionPartyNameAndAddress(
                entity.getAccountWithInstitutionPartyNameAndAddress());
  }
}
