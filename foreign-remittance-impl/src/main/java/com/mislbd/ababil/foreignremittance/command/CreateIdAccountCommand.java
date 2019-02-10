package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.IdAccount;
import com.mislbd.ababil.foreignremittance.repository.schema.IdAccountEntity;
import com.mislbd.asset.command.api.Command;

public class CreateIdAccountCommand extends Command<IdAccount> {
    public CreateIdAccountCommand(IdAccount payload){
        super(payload);
    }
}
