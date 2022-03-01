package com.mislbd.ababil.foreignremittance.external.service;

import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
import com.mislbd.ababil.foreignremittance.external.domain.Customer;
import com.mislbd.ababil.foreignremittance.external.repository.CustomerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerClient customerClient;

  public CustomerService(CustomerClient customerClient) {
    this.customerClient = customerClient;
  }

  public Customer findCustomerDetails(Long customerId) {
    ResponseEntity<Customer> customerResponse = customerClient.getCustomer(customerId);
    if (customerResponse.getStatusCode() == HttpStatus.OK) {
      return customerResponse.getBody();
    } else {
      new ForeignRemittanceBaseException("Exception in customer fetching, Contact administrator");
    }
    return null;
  }
}
