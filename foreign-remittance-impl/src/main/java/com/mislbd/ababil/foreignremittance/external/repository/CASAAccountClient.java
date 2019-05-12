package com.mislbd.ababil.foreignremittance.external.repository;

import com.mislbd.ababil.foreignremittance.external.domain.Account;
import com.mislbd.ababil.foreignremittance.external.domain.DepositAccount;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ababil-deposit", url = "${mislbd.api.gatewayUrl}/ababil-deposit")
public interface CASAAccountClient {

  @RequestMapping(method = RequestMethod.GET, value = "/accounts/account/{accountNumber}")
  Optional<Account> getAccount(@PathVariable("accountNumber") String accountNumber);

  @RequestMapping(method = RequestMethod.GET, value = "/demand-deposit-accounts")
  PagedResult<DepositAccount> getAccountBalance(
      @RequestParam(name = "accountNumber") String accountNumber);
}
