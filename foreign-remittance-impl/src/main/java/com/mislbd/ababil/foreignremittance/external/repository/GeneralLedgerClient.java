package com.mislbd.ababil.foreignremittance.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Component
@FeignClient(name = "ababil-general-ledger", url = "${mislbd.api.gatewayUrl}/ababil-general-ledger")
public interface GeneralLedgerClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/general-ledger-accounts/account/{accountNo}")
    Optional<GLAccount> getGeneralLedgerAccount(@PathVariable("accountNo") String accountNo);

    @RequestMapping(method = RequestMethod.GET, value = "/subsidiary-ledgers/account/{accountNo}")
    Optional<SubGLAccount> getSubsidiaryLedgerAccount(@PathVariable("accountNo") String accountNo);
}
