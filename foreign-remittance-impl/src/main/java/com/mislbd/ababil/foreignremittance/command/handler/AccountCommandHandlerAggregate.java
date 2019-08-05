package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.*;
import com.mislbd.ababil.foreignremittance.mapper.NostroAccountMapper;
import com.mislbd.ababil.foreignremittance.mapper.ShadowAccountMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroAccountEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.ababil.foreignremittance.service.NostroAccountService;
import com.mislbd.ababil.foreignremittance.service.ShadowAccountNumberProviderService;
import com.mislbd.ababil.foreignremittance.service.ShadowAccountService;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class AccountCommandHandlerAggregate {

  private final ShadowAccountMapper shadowAccountMapper;
  private final NostroAccountMapper nostroAccountMapper;
  private final ShadowAccountRepository shadowAccountRepository;
  private final NostroAccountRepository nostroAccountRepository;
  private final ShadowAccountNumberProviderService shadowAccountNumberProviderService;

  private final NostroAccountService nostroAccountService;
  private final ShadowAccountService shadowAccountService;
  private Auditor auditor;

  public AccountCommandHandlerAggregate(
      Auditor auditor,
      ShadowAccountMapper shadowAccountMapper,
      NostroAccountMapper nostroAccountMapper,
      ShadowAccountRepository shadowAccountRepository,
      NostroAccountRepository nostroAccountRepository,
      ShadowAccountNumberProviderService shadowAccountNumberProviderService,
      NostroAccountService nostroAccountService,
      ShadowAccountService shadowAccountService) {
    this.auditor = auditor;
    this.shadowAccountMapper = shadowAccountMapper;
    this.nostroAccountMapper = nostroAccountMapper;
    this.shadowAccountRepository = shadowAccountRepository;
    this.nostroAccountRepository = nostroAccountRepository;
    this.shadowAccountNumberProviderService = shadowAccountNumberProviderService;
    this.nostroAccountService = nostroAccountService;

    this.shadowAccountService = shadowAccountService;
  }

  @CommandListener(
      commandClasses = {
        SaveShadowAccountCommand.class,
        SaveNostroAccountCommand.class,
        UpdateShadowAccountCommand.class,
        UpdateNostroAccountCommand.class
      })
  public void auditAccountCreateAndUpdate(CommandEvent e) {

    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @CommandListener(commandClasses = {InactiveShadowAccountCommand.class})
  public void auditInactiveShadowAccountCommand(CommandEvent e) {

    auditor.audit(
        shadowAccountService.findById((Long) e.getCommand().getPayload()), e.getCommand());
  }

  @CommandListener(commandClasses = {InactiveNostroAccountCommand.class})
  public void auditInactiveNostroAccountCommand(CommandEvent e) {

    auditor.audit(
        nostroAccountService.findById((Long) e.getCommand().getPayload()), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> saveShadowAccount(SaveShadowAccountCommand command) {

    ShadowAccountEntity shadowAccountEntity =
        shadowAccountMapper.domainToEntity().map(command.getPayload());
    shadowAccountNumberProviderService.makeAccountUsed(shadowAccountEntity.getNumber());
    shadowAccountRepository.save(shadowAccountEntity);
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> saveNostroAccount(SaveNostroAccountCommand command) {
    nostroAccountRepository.save(nostroAccountMapper.domainToEntity().map(command.getPayload()));
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
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
        shadowAccountRepository.findByNumber(command.getPayload().getShadowAccountNumber()).get();
    shadowAccountEntity.setActive(false);
    shadowAccountRepository.save(shadowAccountEntity);
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> inactiveNostroAccount(InactiveNostroAccountCommand command) {
    NostroAccountEntity nostroAccountEntity =
        nostroAccountRepository
            .findByNostroAccountNumber(command.getPayload().getNostroAccountNumber())
            .get();
    nostroAccountEntity.setActive(false);
    nostroAccountRepository.save(nostroAccountEntity);
    return CommandResponse.asVoid();
  }
}
