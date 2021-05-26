package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;
import com.mislbd.swift.broker.model.raw.mt1xx.MT103MessageRequest;

@CommandAttribute(
        name = "Authorize SingleCustomerCreditTransfer Message",
        group = "FOREIGN REMITTANCE")
public class AuthorizeSingleCustomerCreditTransferMessageCommand extends Command<String> {

    public AuthorizeSingleCustomerCreditTransferMessageCommand(){}
    public AuthorizeSingleCustomerCreditTransferMessageCommand(String payload) {
        super(payload);
    }


}
