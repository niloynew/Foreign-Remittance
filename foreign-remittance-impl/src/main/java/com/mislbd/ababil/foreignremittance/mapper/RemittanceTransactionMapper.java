package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionTypeRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RemittanceTransactionMapper {

  private final RemittanceTransactionRepository remittanceTransactionRepository;
  private final TransactionTypeRepository transactionTypeRepository;
  private final BankInformationMapper bankInformationMapper;
  private final AdditionInformationMapper additionInformationMapper;

  public RemittanceTransactionMapper(
      RemittanceTransactionRepository remittanceTransactionRepository,
      TransactionTypeRepository transactionTypeRepository,
      BankInformationMapper bankInformationMapper,
      AdditionInformationMapper additionInformationMapper) {
    this.remittanceTransactionRepository = remittanceTransactionRepository;
    this.transactionTypeRepository = transactionTypeRepository;
    this.bankInformationMapper = bankInformationMapper;
    this.additionInformationMapper = additionInformationMapper;
  }

  public ResultMapper<RemittanceTransactionEntity, RemittanceTransaction> entityToDomain() {
    return entity ->
        new RemittanceTransaction()
            .setId(entity.getId())
            .setRemittanceType(entity.getRemittanceType())
            .setTransactionTypeId(entity.getTransactionType().getId())
            .setPpCode(entity.getPpCode())
            .setTransactionReferenceNumber(entity.getTransactionReferenceNumber())
            .setInstrumentNumber(entity.getInstrumentNumber())
            .setCbFundSourceId(entity.getCbFundSourceId())
            .setBeneficiaryId(entity.getBeneficiaryId())
            .setApplicantId(entity.getApplicantId())
            .setApplicantAccountNumber(entity.getApplicantAccountNumber())
            .setValueDate(entity.getValueDate())
            .setBeneficiaryAccountNumber(entity.getBeneficiaryAccountNumber())
            .setShadowAccountType(entity.getShadowAccountType())
            .setShadowAccountNumber(entity.getShadowAccountNumber())
            .setShadowAccountCurrency(entity.getShadowAccountCurrency())
            .setOperatingAccountType(entity.getOperatingAccountType())
            .setOperatingAccountNumber(entity.getOperatingAccountNumber())
            .setOperatingAccountCurrency(entity.getOperatingAccountCurrency())
            .setOperatingRateTypeId(entity.getOperatingRateTypeId())
            .setOperatingRate(entity.getOperatingRate())
            .setAmountFcy(entity.getAmountFcy())
            .setAmountLcy(entity.getAmountLcy())
            .setAmountRcy(entity.getAmountRcy())
            .setSenderBIC(entity.getSenderBIC())
            .setReceiverBIC(entity.getReceiverBIC())
            .setCategoryId(entity.getCategoryId())
            .setAdditionalInformation(
                entity.getAdditionalInformationEntity() == null
                    ? null
                    : additionInformationMapper
                        .entityToDomain()
                        .map(entity.getAdditionalInformationEntity()))
            .setBankInformations(
                entity
                    .getBankMappingEntity()
                    .stream()
                    .map(bankEntity -> bankInformationMapper.entityToDomain().map(bankEntity))
                    .collect(Collectors.toList()));
  }

  public ResultMapper<RemittanceTransaction, RemittanceTransactionEntity> domainToEntity() {
    return domain ->
        remittanceTransactionRepository
            .findById(domain.getId())
            .orElseGet(RemittanceTransactionEntity::new)
            .setRemittanceType(domain.getRemittanceType())
            .setTransactionType(transactionTypeRepository.getOne(domain.getTransactionTypeId()))
            .setPpCode(domain.getPpCode())
            .setBeneficiaryId(domain.getBeneficiaryId())
            .setTransactionReferenceNumber(domain.getTransactionReferenceNumber())
            .setInstrumentNumber(domain.getInstrumentNumber())
            .setCbFundSourceId(domain.getCbFundSourceId())
            .setApplicantId(domain.getApplicantId())
            .setApplicantAccountNumber(domain.getApplicantAccountNumber())
            .setBeneficiaryAccountNumber(domain.getBeneficiaryAccountNumber())
            .setShadowAccountType(domain.getShadowAccountType())
            .setShadowAccountNumber(domain.getShadowAccountNumber())
            .setShadowAccountCurrency(domain.getShadowAccountCurrency())
            .setOperatingAccountType(domain.getOperatingAccountType())
            .setOperatingAccountNumber(domain.getOperatingAccountNumber())
            .setOperatingAccountCurrency(domain.getOperatingAccountCurrency())
            .setOperatingRateTypeId(domain.getOperatingRateTypeId())
            .setOperatingRate(domain.getOperatingRate())
            .setCategoryId(domain.getCategoryId())
            .setAmountFcy(domain.getAmountFcy())
            .setAmountLcy(domain.getAmountLcy())
            .setAmountRcy(domain.getAmountRcy())
            .setValueDate(domain.getValueDate())
            .setSenderBIC(domain.getSenderBIC())
            .setReceiverBIC(domain.getReceiverBIC())
            .setAdditionalInformationEntity(
                domain.getAdditionalInformation() == null
                    ? null
                    : additionInformationMapper
                        .domainToEntity()
                        .map(domain.getAdditionalInformation()))
            .setBankMappingEntity(
                domain.getBankInformations() == null || domain.getBankInformations().isEmpty()
                    ? null
                    : domain
                        .getBankInformations()
                        .stream()
                        .map(x -> bankInformationMapper.domainToEntity().map(x))
                        .collect(Collectors.toList()));
  }
}
