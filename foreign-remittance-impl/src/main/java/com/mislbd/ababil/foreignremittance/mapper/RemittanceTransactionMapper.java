package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.contacts.domain.Country;
import com.mislbd.ababil.contacts.service.CountryService;
import com.mislbd.ababil.foreignremittance.domain.ExportRelatedRemittanceInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.SenderOrReceiverCustomerRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionRegisterRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionTypeRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.SenderOrReceiverCustomerEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RemittanceTransactionMapper {

  private final RemittanceTransactionRepository remittanceTransactionRepository;
  private final TransactionTypeRepository transactionTypeRepository;
  private final BankInformationMapper bankInformationMapper;
  private final AdditionInformationMapper additionInformationMapper;
  private final TransactionRegisterRepository registerRepository;
  private final SenderOrReceiverCustomerRepository senderOrReceiverCustomerRepository;
  private final CountryService countryService;

  public RemittanceTransactionMapper(
      RemittanceTransactionRepository remittanceTransactionRepository,
      TransactionTypeRepository transactionTypeRepository,
      BankInformationMapper bankInformationMapper,
      AdditionInformationMapper additionInformationMapper,
      TransactionRegisterRepository registerRepository,
      SenderOrReceiverCustomerRepository senderOrReceiverCustomerRepository,
      CountryService countryService) {
    this.remittanceTransactionRepository = remittanceTransactionRepository;
    this.transactionTypeRepository = transactionTypeRepository;
    this.bankInformationMapper = bankInformationMapper;
    this.additionInformationMapper = additionInformationMapper;
    this.registerRepository = registerRepository;
    this.senderOrReceiverCustomerRepository = senderOrReceiverCustomerRepository;
    this.countryService = countryService;
  }

  public ResultMapper<RemittanceTransactionEntity, RemittanceTransaction> entityToDomain(
      boolean voucherNumberRequired) {
    return entity ->
        new RemittanceTransaction()
            .setId(entity.getId())
            .setRemittanceType(entity.getRemittanceType())
            .setTransactionTypeId(entity.getTransactionType().getId())
            .setPpCode(entity.getPpCode())
            .setTransactionReferenceNumber(entity.getTransactionReferenceNumber())
            .setInternalReferenceNumber(entity.getInternalReferenceNumber())
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
            .setTransactionStatus(entity.getTransactionStatus())
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
                    .collect(Collectors.toList()))
            .setGlobalTransactionNo(
                voucherNumberRequired
                    ? registerRepository
                        .getDistinctFirstByRemittanceTransactionId(entity.getId())
                        .getVoucherNumber()
                    : null)
            .setSalesContractNumber(entity.getSalesContractNumber())
            .setArvNumber(entity.getArvNumber());
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
            .setInternalReferenceNumber(
                domain.getInternalReferenceNumber() != null
                    ? domain.getInternalReferenceNumber()
                    : null)
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
                        .collect(Collectors.toList()))
            .setSalesContractNumber(domain.getSalesContractNumber())
            .setArvNumber(domain.getArvNumber());
  }

  public ResultMapper<RemittanceTransactionEntity, ExportRelatedRemittanceInformation>
      entityToExportInformation() {
    return entity -> {
      SenderOrReceiverCustomerEntity applicant =
          senderOrReceiverCustomerRepository
              .findById(entity.getApplicantId())
              .orElseThrow(
                  () ->
                      new ForeignRemittanceBaseException(
                          "Applicant not found with id: " + entity.getApplicantId()));
      Country country =
          countryService
              .getCountry(Long.valueOf(applicant.getCountry()))
              .orElseThrow(
                  () ->
                      new ForeignRemittanceBaseException(
                          "Country not found with id: " + applicant.getCountry()));
      return new ExportRelatedRemittanceInformation()
          .setTransactionReferenceNumber(entity.getTransactionReferenceNumber())
          .setInternalReferenceNumber(entity.getInternalReferenceNumber())
          .setSalesContractNumber(entity.getSalesContractNumber())
          .setArvNumber(entity.getArvNumber())
          .setAmount(entity.getAmountFcy())
          .setCustomerId(entity.getBeneficiaryId())
          .setCurrency(entity.getOperatingAccountCurrency())
          .setApplicantName(applicant.getName())
          .setApplicantCountry(country.getName())
          .setValueDate(entity.getValueDate())
          .setInitiator(entity.getCreatedBy())
          .setVerifier(entity.getVerifyBy());
    };
  }
}
