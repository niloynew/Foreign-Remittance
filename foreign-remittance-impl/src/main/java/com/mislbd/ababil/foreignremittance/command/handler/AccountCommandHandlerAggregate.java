package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.SaveShadowAccountCommand;
import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.mapper.ShadowAccountMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class AccountCommandHandlerAggregate {

  private final ShadowAccountMapper shadowAccountMapper;
  private final ShadowAccountRepository shadowAccountRepository;

  public AccountCommandHandlerAggregate(
      ShadowAccountMapper shadowAccountMapper, ShadowAccountRepository shadowAccountRepository) {
    this.shadowAccountMapper = shadowAccountMapper;
    this.shadowAccountRepository = shadowAccountRepository;
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> saveShadowAccount(SaveShadowAccountCommand command) {
    saveShadowAccount(command.getPayload());
    return CommandResponse.asVoid();
  }

  private void saveShadowAccount(Account account) {
    ShadowAccountEntity shadowAccountEntity = shadowAccountMapper.domainToEntity().map(account);
    //    if (shadowAccountEntity != null) System.out.print("Almost done");
    shadowAccountRepository.save(shadowAccountEntity);
  }
}
