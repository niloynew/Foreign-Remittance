package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.RemittanceMsgDto;
import com.mislbd.ababil.foreignremittance.repository.jpa.SwiftMsgRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceMsgEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class RemittanceMsgDtoMapper {
  private final SwiftMsgRepository swiftMsgRepository;

  public RemittanceMsgDtoMapper(SwiftMsgRepository swiftMsgRepository) {
    this.swiftMsgRepository = swiftMsgRepository;
  }

  public ResultMapper<RemittanceMsgEntity, RemittanceMsgDto> entityToDomain() {
    return entity ->
        new RemittanceMsgDto()
            .setId(entity.getId())
            .setAccountWithInstitutionAcc(entity.getAccountWithInstitutionAcc())
            .setAccountWithInstitutionBic(entity.getAccountWithInstitutionBic())
            .setAccountWithInstitutionNameAndAddress(
                entity.getAccountWithInstitutionNameAndAddress())
            .setAmount(entity.getAmount())
            .setValueDate(entity.getValueDate())
            .setCurrency(entity.getCurrency())
            .setInstructedAmount(entity.getInstructedAmount())
            .setInstructedCurrency(entity.getInstructedCurrency())
            .setIntermediaryAcc(entity.getIntermediaryAcc())
            .setIntermediaryBic(entity.getIntermediaryBic())
            .setIntermediaryNameAndAddress(entity.getIntermediaryNameAndAddress())
            .setMessageType(entity.getMessageType())
            .setLcNumber(entity.getLcNumber())
            .setOrderingCustomerAcc(entity.getOrderingCustomerAcc())
            .setOrderingCustomerBic(entity.getOrderingCustomerBic())
            .setOrderingCustomerNameAndAddress(entity.getOrderingCustomerNameAndAddress())
            .setSendersCorrespondentAcc(entity.getSendersCorrespondentAcc())
            .setSendersCorrespondentBic(entity.getSendersCorrespondentBic())
            .setSendersCorrespondentNameAndAddress(entity.getSendersCorrespondentNameAndAddress())
            .setOrderingInsAcc(entity.getOrderingInsAcc())
            .setOrderingInsBic(entity.getOrderingInsBic())
            .setOrderingInsNameAndAddress(entity.getOrderingInsNameAndAddress())
            .setReceiversCorrespondentAcc(entity.getReceiversCorrespondentAcc())
            .setReceiversCorrespondentBic(entity.getReceiversCorrespondentBic())
            .setReceiversCorrespondentNameAndAddress(
                entity.getReceiversCorrespondentNameAndAddress())
            .setRelatedReference(entity.getRelatedReference())
            .setRemittanceInfo(entity.getRemittanceInfo());
  }

  public ResultMapper<RemittanceMsgDto, RemittanceMsgEntity> domainToEntity() {
    return domain ->
        swiftMsgRepository
            .findById(domain.getId())
            .orElseGet(RemittanceMsgEntity::new)
            .setAccountWithInstitutionBic(domain.getAccountWithInstitutionBic())
            .setAccountWithInstitutionNameAndAddress(
                domain.getAccountWithInstitutionNameAndAddress())
            .setAmount(domain.getAmount())
            .setValueDate(domain.getValueDate())
            .setCurrency(domain.getCurrency())
            .setInstructedAmount(domain.getInstructedAmount())
            .setInstructedCurrency(domain.getInstructedCurrency())
            .setIntermediaryAcc(domain.getIntermediaryAcc())
            .setIntermediaryBic(domain.getIntermediaryBic())
            .setIntermediaryNameAndAddress(domain.getIntermediaryNameAndAddress())
            .setMessageType(domain.getMessageType())
            .setLcNumber(domain.getLcNumber())
            .setOrderingCustomerAcc(domain.getOrderingCustomerAcc())
            .setOrderingCustomerBic(domain.getOrderingCustomerBic())
            .setOrderingCustomerNameAndAddress(domain.getOrderingCustomerNameAndAddress())
            .setSendersCorrespondentAcc(domain.getSendersCorrespondentAcc())
            .setSendersCorrespondentBic(domain.getSendersCorrespondentBic())
            .setSendersCorrespondentNameAndAddress(domain.getSendersCorrespondentNameAndAddress())
            .setOrderingInsAcc(domain.getOrderingInsAcc())
            .setOrderingInsBic(domain.getOrderingInsBic())
            .setOrderingInsNameAndAddress(domain.getOrderingInsNameAndAddress())
            .setReceiversCorrespondentAcc(domain.getReceiversCorrespondentAcc())
            .setReceiversCorrespondentBic(domain.getReceiversCorrespondentBic())
            .setReceiversCorrespondentNameAndAddress(
                domain.getReceiversCorrespondentNameAndAddress())
            .setRelatedReference(domain.getRelatedReference())
            .setRemittanceInfo(domain.getRemittanceInfo());
  }
}
