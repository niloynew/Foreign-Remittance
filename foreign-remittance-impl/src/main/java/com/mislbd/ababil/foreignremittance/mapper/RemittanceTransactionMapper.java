package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.BankInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.BankInformationEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RemittanceTransactionMapper {

  private final RemittanceTransactionRepository remittanceTransactionRepository;

  public RemittanceTransactionMapper(
      RemittanceTransactionRepository remittanceTransactionRepository) {
    this.remittanceTransactionRepository = remittanceTransactionRepository;
  }

  public ResultMapper<RemittanceTransactionEntity, RemittanceTransaction> entityToDomain() {
    return entity ->
        new RemittanceTransaction()
            .setId(entity.getId())
            .setTransactionTypeId(entity.getTransactionTypeId())
            .setRemittanceType(entity.getRemittanceType())
            .setOperationId(entity.getOperationId())
            .setPaymentPurposeId(entity.getPaymentPurposeId())
            .setCommodityDescription(entity.getCommodityDescription())
            .setTransactionReferenceNumber(entity.getTransactionReferenceNumber())
            .setInstructionNumber(entity.getInstructionNumber())
            .setCbFundSourceId(entity.getCbFundSourceId())
            .setDeliveryTerm(entity.getDeliveryTerm())
            .setApplicantId(entity.getApplicantId())
            .setApplicantAccountNumber(entity.getApplicantAccountNumber())
            .setBeneficiaryId(entity.getBeneficiaryId())
            .setBeneficiaryAccountNumber(entity.getBeneficiaryAccountNumber())
            .setB2bInformation(entity.getB2bInformation())
            .setBankInformation(getConvertedBankInformation(entity.getBankInformationEntity()))
            .setDebitAccountTypeId(entity.getDebitAccountTypeId())
            .setDebitAccountNumber(entity.getDebitAccountNumber())
            .setCreditAccountTypeId(entity.getCreditAccountTypeId())
            .setCreditAccountNumber(entity.getCreditAccountNumber())
            .setCurrencyCode(entity.getCurrencyCode())
            .setClientRateTypeId(entity.getClientRateTypeId())
            .setHoRateTypeId(entity.getHoRateTypeId())
            .setAmountFcy(entity.getAmountFcy())
            .setAmountLcy(entity.getAmountLcy())
            .setExchangeGain(entity.getExchangeGain())
            .setStatus(entity.getStatus());
  }

  private List<BankInformation> getConvertedBankInformation(
      List<BankInformationEntity> bankInformationEntities) {
    List<BankInformation> bankInformationList = new ArrayList<>();
    if (bankInformationEntities != null && !bankInformationEntities.isEmpty()) {
      for (BankInformationEntity entity : bankInformationEntities) {
        BankInformation bankInformation = new BankInformation();
        bankInformation.setBankTypeId(entity.getBankTypeId());
        bankInformation.setSwiftCode(entity.getSwiftCode());
        bankInformationList.add(bankInformation);
      }
    }
    return bankInformationList;
  }

  public ResultMapper<RemittanceTransaction, RemittanceTransactionEntity> domainToEntity() {
    return domain ->
        remittanceTransactionRepository
            .findById(Long.valueOf(domain.getId()))
            .orElseGet(RemittanceTransactionEntity::new)
            .setTransactionTypeId(domain.getTransactionTypeId())
            .setRemittanceType(domain.getRemittanceType())
            .setOperationId(domain.getOperationId())
            .setPaymentPurposeId(domain.getPaymentPurposeId())
            .setCommodityDescription(domain.getCommodityDescription())
            .setTransactionReferenceNumber(domain.getTransactionReferenceNumber())
            .setInstructionNumber(domain.getInstructionNumber())
            .setCbFundSourceId(domain.getCbFundSourceId())
            .setDeliveryTerm(domain.getDeliveryTerm())
            .setApplicantId(domain.getApplicantId())
            .setApplicantAccountNumber(domain.getApplicantAccountNumber())
            .setBeneficiaryId(domain.getBeneficiaryId())
            .setBeneficiaryAccountNumber(domain.getBeneficiaryAccountNumber())
            .setB2bInformation(domain.getB2bInformation())
            .setBankInformationEntity(
                getConvertedBankInformationEntity(domain.getBankInformation()))
            .setDebitAccountTypeId(domain.getDebitAccountTypeId())
            .setDebitAccountNumber(domain.getDebitAccountNumber())
            .setCreditAccountTypeId(domain.getCreditAccountTypeId())
            .setCreditAccountNumber(domain.getCreditAccountNumber())
            .setCurrencyCode(domain.getCurrencyCode())
            .setClientRateTypeId(domain.getClientRateTypeId())
            .setHoRateTypeId(domain.getHoRateTypeId())
            .setAmountFcy(domain.getAmountFcy())
            .setAmountLcy(domain.getAmountLcy())
            .setExchangeGain(domain.getExchangeGain())
            .setStatus(domain.getStatus());
  }

  private List<BankInformationEntity> getConvertedBankInformationEntity(
      List<BankInformation> bankInformations) {
    List<BankInformationEntity> bankInformationEntities = new ArrayList<>();
    if (bankInformations != null && !bankInformations.isEmpty()) {
      for (BankInformation bankInformation : bankInformations) {
        BankInformationEntity entity = new BankInformationEntity();
        entity.setBankTypeId(bankInformation.getBankTypeId());
        entity.setSwiftCode(bankInformation.getSwiftCode());
        bankInformationEntities.add(entity);
      }
    }
    return bankInformationEntities;
  }
}
