package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.SwiftRegister;
import com.mislbd.asset.command.api.Command;

public class UpdateSwiftRegisterCommand extends Command<SwiftRegister> {
    public UpdateSwiftRegisterCommand(SwiftRegister payload) {
        super(payload);
    }
}
