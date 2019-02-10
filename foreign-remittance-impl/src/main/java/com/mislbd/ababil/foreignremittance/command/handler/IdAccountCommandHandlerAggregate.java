package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.CreateIdAccountCommand;
import com.mislbd.ababil.foreignremittance.mapper.IdAccountMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.IdAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroAccountRepository;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;

@Aggregate
public class IdAccountCommandHandlerAggregate {

    private final IdAccountRepository repository;
    private final IdAccountMapper mapper;
    private final NostroAccountRepository nostroAccountRepository;
    public IdAccountCommandHandlerAggregate(IdAccountRepository repository, IdAccountMapper mapper, NostroAccountRepository nostroAccountRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.nostroAccountRepository = nostroAccountRepository;
    }

    @CommandHandler
    public CommandResponse<String> createIdAccount(CreateIdAccountCommand command){

    return CommandResponse.of("");
    }

}
