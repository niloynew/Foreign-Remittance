package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;
import com.mislbd.swift.broker.model.raw.mt1xx.MT103MessageRequest;

@CommandAttribute(
            name = "Generate SingleCustomerCreditTransfer Message",
            group = "FOREIGN REMITTANCE")
public class CreateGenerateSingleCustomerCreditTransferMessageCommand
            extends Command<MT103MessageRequest> {
    public CreateGenerateSingleCustomerCreditTransferMessageCommand() {}

        public CreateGenerateSingleCustomerCreditTransferMessageCommand(MT103MessageRequest payload) {
            super(payload);
        }
}
