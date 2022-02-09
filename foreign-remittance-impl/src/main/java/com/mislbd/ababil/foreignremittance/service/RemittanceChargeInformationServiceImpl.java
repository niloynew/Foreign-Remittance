package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.exception.RemittanceChargeAmountNotFoundException;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeMappingRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeMappingEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeSlabEntity;
import com.mislbd.ababil.foreignremittance.repository.specification.RemittanceChargeMappingSpecification;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import com.mislbd.transaction.api.transaction.model.Charge;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class RemittanceChargeInformationServiceImpl implements RemittanceChargeInformationService {

  private final RemittanceChargeMappingRepository remittanceChargeMappingRepository;
  private final RemittanceChargeRepository remittanceChargeRepository;

  public RemittanceChargeInformationServiceImpl(
      RemittanceChargeMappingRepository remittanceChargeMappingRepository,
      RemittanceChargeRepository remittanceChargeRepository) {
    this.remittanceChargeMappingRepository = remittanceChargeMappingRepository;
    this.remittanceChargeRepository = remittanceChargeRepository;
  }

  public List<Charge> getChargeInfo(
      RemittanceType remittanceType, long transactionTypeId, BigDecimal amount) {

    List<RemittanceChargeMappingEntity> chargeMappingList =
        remittanceChargeMappingRepository.findAll(
            RemittanceChargeMappingSpecification.findSpecificChargeMappings(
                remittanceType, transactionTypeId, null, null, null));
    List<RemittanceChargeEntity> chargeList = getChargeList(chargeMappingList);
    List<Charge> charges = new ArrayList<>();
    if (chargeList.isEmpty()) return charges;
    charges =
        chargeList
            .stream()
            .map(
                remittanceCharge -> {
                  BigDecimal chargeAmount = chargeCalculation(remittanceCharge, amount);
                  BigDecimal vatAmount = vatCalculation(remittanceCharge, chargeAmount);
                  Charge charge =
                      Charge.builder()
                          .chargeCode(remittanceCharge.getChargeAccountCode())
                          .chargeName(remittanceCharge.getChargeName())
                          .chargeAccountCode(remittanceCharge.getChargeAccountCode())
                          .currencyCode(remittanceCharge.getCurrencyCode())
                          .chargeAmountRCY(chargeAmount)
                          .vatAccountCode(remittanceCharge.getVatAccountCode())
                          .vatAmountRCY(vatAmount)
                          .build();
                  return charge;
                })
            .collect(Collectors.toList());
    return charges;
  }

  private BigDecimal vatCalculation(
      RemittanceChargeEntity remittanceCharge, BigDecimal chargeAmount) {
    BigDecimal vatAmount = BigDecimal.ZERO;
    if (chargeAmount != BigDecimal.ZERO) {
      if (remittanceCharge.isFixedVat()) {

        vatAmount =
            remittanceCharge.getVatAmount() == null
                ? BigDecimal.ZERO
                : remittanceCharge.getVatAmount();
      } else {
        vatAmount =
            remittanceCharge.getVatPercentage() == null
                ? BigDecimal.ZERO
                : BigDecimal.valueOf(0.01)
                    .multiply(remittanceCharge.getVatPercentage().multiply(chargeAmount));
      }
    }
    return vatAmount;
  }

  private BigDecimal chargeCalculation(RemittanceChargeEntity remittanceCharge, BigDecimal amount) {

    BigDecimal chargeAmount = BigDecimal.ZERO;

    if (remittanceCharge.isSlabBased()) {
      if (amount == null) {
        throw new RemittanceChargeAmountNotFoundException();
      }
      chargeAmount = slabBasedChargeCalculation(remittanceCharge, amount);
    } else if (remittanceCharge.isFixedCharge()) {
      chargeAmount = fixedChargeCalculation(remittanceCharge);
    } else if (!remittanceCharge.isFixedCharge()
        && !remittanceCharge.isSlabBased()
        && amount != null) {
      chargeAmount = percentageChargeCalculation(remittanceCharge, amount);
    }

    return chargeAmount;
  }

  private BigDecimal fixedChargeCalculation(RemittanceChargeEntity remittanceCharge) {
    BigDecimal chargeAmount = BigDecimal.ZERO;
    if (remittanceCharge.isFixedCharge()) {
      chargeAmount = remittanceCharge.getChargeAmount();
    }
    return chargeAmount;
  }

  private BigDecimal percentageChargeCalculation(
      RemittanceChargeEntity remittanceCharge, BigDecimal amount) {
    BigDecimal chargeAmount = BigDecimal.ZERO;
    BigDecimal percentageChargeAmount =
        BigDecimal.valueOf(0.01).multiply(remittanceCharge.getChargePercentage().multiply(amount));
    if (percentageChargeAmount.compareTo(remittanceCharge.getMinimumChargeAmount()) == 1
        && percentageChargeAmount.compareTo(remittanceCharge.getMaximumChargeAmount()) == -1) {
      chargeAmount = percentageChargeAmount;
    } else if (percentageChargeAmount.compareTo(remittanceCharge.getMinimumChargeAmount()) == -1) {
      chargeAmount = remittanceCharge.getMinimumChargeAmount();
    } else if (percentageChargeAmount.compareTo(remittanceCharge.getMaximumChargeAmount()) == 1) {
      chargeAmount = remittanceCharge.getMaximumChargeAmount();
    }
    return chargeAmount;
  }

  private BigDecimal slabBasedChargeCalculation(
      RemittanceChargeEntity remittanceCharge, BigDecimal amount) {
    BigDecimal chargeAmount = BigDecimal.ZERO;
    if (remittanceCharge.getRemittanceChargeSlabs().isEmpty()) return chargeAmount;

    for (RemittanceChargeSlabEntity chargeSlabEntity :
        remittanceCharge.getRemittanceChargeSlabs()) {
      if (amount.compareTo(chargeSlabEntity.getFromAmount()) > 0
          && amount.compareTo(chargeSlabEntity.getToAmount()) < 0) {

        if (chargeSlabEntity.isFixedCharge()) {
          chargeAmount = chargeSlabEntity.getChargeAmount();
        } else {
          BigDecimal percentageChargeAmount =
              BigDecimal.valueOf(0.01).multiply(chargeSlabEntity.getPercentage().multiply(amount));
          if (percentageChargeAmount.compareTo(chargeSlabEntity.getMinimumChargeAmount()) == 1
              && percentageChargeAmount.compareTo(chargeSlabEntity.getMaximumChargeAmount())
                  == -1) {
            chargeAmount = percentageChargeAmount;
          } else if (percentageChargeAmount.compareTo(chargeSlabEntity.getMinimumChargeAmount())
              == -1) {
            chargeAmount = chargeSlabEntity.getMinimumChargeAmount();
          } else if (percentageChargeAmount.compareTo(chargeSlabEntity.getMaximumChargeAmount())
              == 1) {
            chargeAmount = chargeSlabEntity.getMaximumChargeAmount();
          }
        }
        break;
      }
    }
    return chargeAmount;
  }

  private List<RemittanceChargeEntity> getChargeList(
      List<RemittanceChargeMappingEntity> chargeMappingList) {
    List<RemittanceChargeEntity> remittanceChargeEntityList =
        chargeMappingList
            .stream()
            .map(
                chargeMapping -> {
                  RemittanceChargeEntity remittanceCharge =
                      remittanceChargeRepository
                          .findById(chargeMapping.getRemittanceCharge().getId())
                          .map(
                              data -> {
                                RemittanceChargeEntity charge = data;
                                charge.setCanModifyCharge(chargeMapping.isChargeModifiable());
                                return charge;
                              })
                          .orElse(null);
                  return remittanceCharge;
                })
            .filter(data -> data.getId() != 0)
            .collect(Collectors.toList());
    return !remittanceChargeEntityList.isEmpty() ? remittanceChargeEntityList : new ArrayList<>();
  }

  private ResultMapper<RemittanceChargeEntity, RemittanceChargeInformation> entityToDomain() {
    return entity ->
        new RemittanceChargeInformation()
            .setChargeId(entity.getId())
            .setChargeName(entity.getChargeName())
            .setChargeAccountCode(entity.getChargeAccountCode())
            .setVatAccountCode(entity.getVatAccountCode())
            .setVatAccountType(entity.getVatAccountType())
            .setChargeAccountType(entity.getChargeAccountType());
  }
}
