package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.CreateIdAccountCommand;
import com.mislbd.ababil.foreignremittance.mapper.ShadowAccountMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroAccountRepository;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;

@Aggregate
public class IdAccountCommandHandlerAggregate {

    private final ShadowAccountRepository repository;
    private final ShadowAccountMapper mapper;
    private final NostroAccountRepository nostroAccountRepository;
    public IdAccountCommandHandlerAggregate(ShadowAccountRepository repository, ShadowAccountMapper mapper, NostroAccountRepository nostroAccountRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.nostroAccountRepository = nostroAccountRepository;
    }

    @CommandHandler
    public CommandResponse<String> createIdAccount(CreateIdAccountCommand command){

    return CommandResponse.of("");
    }

}
