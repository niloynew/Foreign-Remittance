package com.mislbd.ababil.foreignremittance.external.repository;

import com.mislbd.ababil.foreignremittance.external.domain.TransactionQueryResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "ababil-tf-admin", url = "${mislbd.api.gatewayUrl}/ababil-tf-admin")
public interface TransactionQueryClient {

  @RequestMapping(method = RequestMethod.GET, value = "/otherApi")
  List<TransactionQueryResponse> getQueryResponse(
      @RequestParam("voucherNo") List<String> voucherNumbers);
}
