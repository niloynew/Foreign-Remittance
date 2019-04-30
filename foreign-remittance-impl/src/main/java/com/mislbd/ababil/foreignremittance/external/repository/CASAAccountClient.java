package com.mislbd.ababil.foreignremittance.external.repository;


import com.mislbd.ababil.foreignremittance.external.domain.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "ababil-deposit", url = "${mislbd.api.gatewayUrl}/ababil-deposit")
public interface AccountClient {

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/account/{accountNumber}")
    Optional<Account> getAccount(@PathVariable("accountNumber") String accountNumber);

}
