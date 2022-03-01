package com.mislbd.ababil.foreignremittance.external.repository;

import com.mislbd.ababil.foreignremittance.external.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ababil-customer", url = "${mislbd.api.gatewayUrl}/ababil-customer")
public interface CustomerClient {

  @RequestMapping(
      method = {RequestMethod.GET},
      value = {"/customers/{id}"})
  ResponseEntity<Customer> getCustomer(@PathVariable("id") Long var1);
}
