package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.domain.SwiftRegister;
import com.mislbd.ababil.foreignremittance.query.AccountQuery;
import com.mislbd.ababil.foreignremittance.query.SwiftRegisterQuery;
import com.mislbd.ababil.foreignremittance.service.SwiftRegisterService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;

import java.util.List;

public class SwiftRegisterQueryHandlerAggregate {
    private SwiftRegisterService swiftRegisterService;


    public SwiftRegisterQueryHandlerAggregate(SwiftRegisterService swiftRegisterService) {
        this.swiftRegisterService = swiftRegisterService;
    }

    @QueryHandler
    public QueryResult<?> registerSearch(SwiftRegisterQuery registerQuery) {
        if (registerQuery.isAsPage()) {
            PagedResult<SwiftRegister> registerPage =
                    swiftRegisterService.getSwiftRegisters(
                            registerQuery.getPageable(),
                            registerQuery.getReferenceNo(),
                            registerQuery.getSenderAddress(),
                            registerQuery.getReceiverAddress(),
                            registerQuery.getStatus(),
                            registerQuery.getMessageRoutingDateTime()
                    );

            return QueryResult.of(registerPage);

        } else {
            List<SwiftRegister> registers =
                    swiftRegisterService.getSwiftRegisters(
                            registerQuery.getReferenceNo(),
                            registerQuery.getSenderAddress(),
                            registerQuery.getReceiverAddress(),
                            registerQuery.getStatus(),
                            registerQuery.getMessageRoutingDateTime());
            return QueryResult.of(registers);
        }
    }


}
