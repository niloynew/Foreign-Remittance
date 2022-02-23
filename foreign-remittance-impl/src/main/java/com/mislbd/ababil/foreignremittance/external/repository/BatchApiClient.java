package com.mislbd.ababil.foreignremittance.external.repository;

import com.mislbd.ababil.foreignremittance.external.domain.ApiTransactionRequest;
import com.mislbd.ababil.foreignremittance.external.domain.CorrectionApiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
    name = "ababil-batch-transaction",
    url = "${mislbd.api.gatewayUrl}/ababil-batch-transaction")
public interface BatchApiClient {

  @RequestMapping(
      method = {RequestMethod.POST},
      value = {"/api-transaction"})
  ResponseEntity<?> doBatchApiTransaction(ApiTransactionRequest var1);

  @RequestMapping(
      method = {RequestMethod.POST},
      value = {"/api-transaction/correction"})
  ResponseEntity<?> doApiTxnCorrection(CorrectionApiRequest var1);
}
