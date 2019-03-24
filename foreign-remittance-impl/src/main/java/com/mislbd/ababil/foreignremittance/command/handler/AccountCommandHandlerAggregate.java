package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.*;
import com.mislbd.ababil.foreignremittance.mapper.NostroAccountMapper;
import com.mislbd.ababil.foreignremittance.mapper.ShadowAccountMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroAccountEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class AccountCommandHandlerAggregate {

  private final ShadowAccountMapper shadowAccountMapper;
  private final NostroAccountMapper nostroAccountMapper;
  private final ShadowAccountRepository shadowAccountRepository;
  private final NostroAccountRepository nostroAccountRepository;

  public AccountCommandHandlerAggregate(
      ShadowAccountMapper shadowAccountMapper,
      NostroAccountMapper nostroAccountMapper,
      ShadowAccountRepository shadowAccountRepository,
      NostroAccountRepository nostroAccountRepository) {
    this.shadowAccountMapper = shadowAccountMapper;
    this.nostroAccountMapper = nostroAccountMapper;
    this.shadowAccountRepository = shadowAccountRepository;
    this.nostroAccountRepository = nostroAccountRepository;
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> saveShadowAccount(SaveShadowAccountCommand command) {
    shadowAccountRepository.save(shadowAccountMapper.domainToEntity().map(command.getPayload()));
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> saveNostroAccount(SaveNostroAccountCommand command) {
    nostroAccountRepository.save(nostroAccountMapper.domainToEntity().map(command.getPayload()));
    return CommandResponse.asVoid();
  }

  public CommandResponse<Void> updateShardowAccount(UpdateShadowAccountCommand command) {
    shadowAccountRepository.save(shadowAccountMapper.domainToEntity().map(command.getPayload()));
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateNostroAccount(UpdateNostroAccountCommand command) {
    nostroAccountRepository.save(nostroAccountMapper.domainToEntity().map(command.getPayload()));
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> inactiveShadowAccount(InactiveShadowAccountCommand command) {
    ShadowAccountEntity shadowAccountEntity =
        shadowAccountRepository.findByNumber(command.getShadowAccountNumber()).get();
    shadowAccountEntity.setActive(false);
    shadowAccountRepository.save(shadowAccountEntity);
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> inactiveNostroAccount(InactiveNostroAccountCommand command) {
    NostroAccountEntity nostroAccountEntity =
        nostroAccountRepository.findByNostroAccountNumber(command.getNostroAccountNumber()).get();
    nostroAccountEntity.setActive(false);
    nostroAccountRepository.save(nostroAccountEntity);
    return CommandResponse.asVoid();
  }
}
