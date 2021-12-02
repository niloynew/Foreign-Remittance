package com.mislbd.ababil.foreignremittance.domain;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * Responsibility:
 *
 * @author Zahid Hassan
 * @since 04-Nov-2021
 */
public class CustomerMerge {

  @NotNull private Long originalCustomerId;

  @NotNull private List<Long> duplicateCustomerIds;

  public Long getOriginalCustomerId() {
    return originalCustomerId;
  }

  public CustomerMerge setOriginalCustomerId(Long originalCustomerId) {
    this.originalCustomerId = originalCustomerId;
    return this;
  }

  public List<Long> getDuplicateCustomerIds() {
    return duplicateCustomerIds;
  }

  public CustomerMerge setDuplicateCustomerIds(List<Long> duplicateCustomerIds) {
    this.duplicateCustomerIds = duplicateCustomerIds;
    return this;
  }
}
