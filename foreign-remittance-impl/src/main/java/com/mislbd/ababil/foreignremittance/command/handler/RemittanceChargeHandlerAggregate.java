package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.CreateRemittanceChargeCommand;
import com.mislbd.ababil.foreignremittance.command.CreateRemittanceChargeMappingCommand;
import com.mislbd.ababil.foreignremittance.command.UpdateRemittanceChargeCommand;
import com.mislbd.ababil.foreignremittance.domain.RemittanceCharge;
import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeSlab;
import com.mislbd.ababil.foreignremittance.exception.*;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceChargeMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceChargeMappingMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceChargeSlabMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeMappingRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeSlabRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeMappingEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeSlabEntity;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import java.math.BigDecimal;
import javax.transaction.Transactional;

@Aggregate
public class RemittanceChargeHandlerAggregate {

  private final RemittanceChargeMapper remittanceChargeMapper;
  private final RemittanceChargeRepository remittanceChargeRepository;
  private final RemittanceChargeSlabRepository remittanceChargeSlabRepository;
  private final RemittanceChargeSlabMapper remittanceChargeSlabMapper;
  private final RemittanceChargeMappingMapper remittanceChargeMappingMapper;
  private final RemittanceChargeMappingRepository remittanceChargeMappingRepository;

  public RemittanceChargeHandlerAggregate(
      RemittanceChargeMapper remittanceChargeMapper,
      RemittanceChargeRepository remittanceChargeRepository,
      RemittanceChargeSlabRepository remittanceChargeSlabRepository,
      RemittanceChargeSlabMapper remittanceChargeSlabMapper,
      RemittanceChargeMappingMapper remittanceChargeMappingMapper,
      RemittanceChargeMappingRepository remittanceChargeMappingRepository) {
    this.remittanceChargeMapper = remittanceChargeMapper;
    this.remittanceChargeRepository = remittanceChargeRepository;
    this.remittanceChargeSlabRepository = remittanceChargeSlabRepository;
    this.remittanceChargeSlabMapper = remittanceChargeSlabMapper;
    this.remittanceChargeMappingMapper = remittanceChargeMappingMapper;
    this.remittanceChargeMappingRepository = remittanceChargeMappingRepository;
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> createRemittanceCharge(CreateRemittanceChargeCommand command) {
    RemittanceChargeEntity remittanceCharge =
        remittanceChargeMapper.domainToEntity().map(command.getPayload());
    chargeValidations(remittanceCharge);
    minMaxAmountValidationForCharge(command.getPayload());
    minMaxAmountValidationForSlabs(command.getPayload());
    saveSlabs(remittanceCharge, command.getPayload());
    remittanceChargeRepository.save(remittanceCharge);
    return CommandResponse.of(remittanceCharge.getId());
  }

  private RemittanceChargeEntity chargeValidations(RemittanceChargeEntity remittanceCharge) {

    if (remittanceCharge.isFixedCharge() && remittanceCharge.isSlabBased()) {
      throw new RemittanceChargeConfigurationConflictException();
    }
    if (remittanceCharge.isSlabBased()) {
      if (remittanceCharge.getRemittanceChargeSlabs().size() < 1
          || remittanceCharge.getRemittanceChargeSlabs() == null) {
        throw new RemittanceChargeSlabNotFoundException();
      }
    } else if (remittanceCharge.isFixedCharge()) {
      if (remittanceCharge.getChargeAmount() == null) {
        throw new RemittanceChargeAmountNotFoundException();
      }
    } else if (!remittanceCharge.isFixedCharge() && !remittanceCharge.isSlabBased()) {
      if (remittanceCharge.getChargePercentage() == null) {
        throw new RemittanceChargePercentageNotFoundException();
      }
      if (remittanceCharge.getChargePercentage().compareTo(BigDecimal.valueOf(100)) == 1) {
        throw new RemittanceChargePercentageNotFoundException(
            "Charge percentage cannot be more than 100%.");
      }
    }

    if (remittanceCharge.isFixedVat()) {
      if (remittanceCharge.getVatAmount() == null) {
        throw new RemittanceVatAmountNotFoundException();
      }
    }

    if (!remittanceCharge.isFixedVat()) {
      if (remittanceCharge.getVatPercentage() == null) {
        throw new RemittanceVatPercentageNotFoundException();
      }
      if (remittanceCharge.getVatPercentage().compareTo(BigDecimal.valueOf(100)) == 1) {
        throw new RemittanceVatPercentageNotFoundException(
            "VAT percentage cannot be more than 100%.");
      }
    }

    return remittanceCharge;
  }

  private void minMaxAmountValidationForCharge(RemittanceCharge charge) {
    if (charge.isSlabBased()
        || (charge.getMaximumChargeAmount() == null || charge.getMinimumChargeAmount() == null))
      return;

    if (charge.getMaximumChargeAmount().compareTo(charge.getMinimumChargeAmount()) == -1) {
      throw new RemittanceChargeMinMaxAmountException(
          "Minimum charge amount cannot be greater than maximum charge amount");
    }
  }

  private void minMaxAmountValidationForSlabs(RemittanceCharge charge) {
    if (charge.getRemittanceChargeSlabs() != null && !charge.getRemittanceChargeSlabs().isEmpty()) {
      for (RemittanceChargeSlab financingChargeSlab : charge.getRemittanceChargeSlabs()) {
        if (financingChargeSlab.isFixedCharge()
            || (financingChargeSlab.getMaximumChargeAmount() == null
                || financingChargeSlab.getMinimumChargeAmount() == null)) return;

        if (financingChargeSlab
                .getMaximumChargeAmount()
                .compareTo(financingChargeSlab.getMinimumChargeAmount())
            == -1) {
          throw new RemittanceChargeMinMaxAmountException(
              "Minimum charge amount cannot be greater than maximum charge amount");
        }
      }
    }
  }

  private void slabAmountValidationForSlabs(RemittanceCharge depositCharge) {

    for (RemittanceChargeSlab chargeSlab : depositCharge.getRemittanceChargeSlabs()) {
      if (chargeSlab.getFromAmount() == null) {
        throw new RemittanceChargeSlabNotFoundException("From amount cannot be null");
      }

      if (chargeSlab.getFromAmount().compareTo(chargeSlab.getToAmount()) == 0) {
        throw new RemittanceChargeSlabNotFoundException("From amount and To amount cannot be same");
      }

      if (chargeSlab.getToAmount().compareTo(chargeSlab.getFromAmount()) == -1) {
        throw new RemittanceChargeSlabNotFoundException(
            "From amount should not be greater than To amount");
      }
    }
  }

  private void saveSlabs(RemittanceChargeEntity chargeEntity, RemittanceCharge command) {
    remittanceChargeSlabRepository.deleteAllByRemittanceChargeId(chargeEntity.getId());

    if (command.isSlabBased()
        && (command.getRemittanceChargeSlabs() != null
            || command.getRemittanceChargeSlabs().size() > 0)) {

      minMaxAmountValidationForSlabs(command);
      slabAmountValidationForSlabs(command);
      for (RemittanceChargeSlab slab : command.getRemittanceChargeSlabs()) {
        RemittanceChargeSlabEntity slabEntity =
            remittanceChargeSlabMapper.domainToEntity().map(slab);
        slabEntity.setRemittanceCharge(chargeEntity);
        remittanceChargeSlabRepository.save(slabEntity);
      }
    }
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateRemittanceCharge(UpdateRemittanceChargeCommand command) {
    RemittanceChargeEntity chargeEntity =
        remittanceChargeMapper.domainToEntity().map(command.getPayload());
    chargeValidations(chargeEntity);
    minMaxAmountValidationForCharge(command.getPayload());
    saveSlabs(chargeEntity, command.getPayload());
    remittanceChargeRepository.save(chargeEntity);
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> saveRemittanceChargeMapping(
      CreateRemittanceChargeMappingCommand command) {
    RemittanceChargeMappingEntity chargeMappingEntity =
        remittanceChargeMappingMapper.domainToEntity().map(command.getPayload());
    remittanceChargeMappingRepository.save(chargeMappingEntity);
    return CommandResponse.of(chargeMappingEntity.getId());
  }
}
