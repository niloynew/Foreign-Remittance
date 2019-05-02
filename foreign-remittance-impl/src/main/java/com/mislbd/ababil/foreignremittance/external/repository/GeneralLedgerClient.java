package com.mislbd.ababil.foreignremittance.external.repository;

import com.mislbd.ababil.foreignremittance.external.domain.GLAccount;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "ababil-general-ledger", url = "${mislbd.api.gatewayUrl}/ababil-general-ledger")
public interface GeneralLedgerClient {

  @RequestMapping(
      method = RequestMethod.GET,
      value = "/general-ledger-accounts/account/{accountNo}")
  Optional<GLAccount> getGeneralLedgerAccount(@PathVariable("accountNo") String accountNo);
}
