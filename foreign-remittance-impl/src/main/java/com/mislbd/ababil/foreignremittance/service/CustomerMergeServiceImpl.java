package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.CustomerMerge;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Responsibility:
 *
 * @author Zahid Hassan
 * @since 04-Nov-2021
 */
@Service
public class CustomerMergeServiceImpl implements CustomerMergeService {

  public CustomerMergeServiceImpl() {}

  @Override
  @Transactional
  public void customerMerge(CustomerMerge customerMerge) {
    Long originalCustomerId = customerMerge.getOriginalCustomerId();
    List<Long> duplicateCustomerIds = customerMerge.getDuplicateCustomerIds();
  }
}
