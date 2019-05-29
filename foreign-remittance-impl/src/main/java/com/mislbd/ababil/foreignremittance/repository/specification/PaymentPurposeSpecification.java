package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.PaymentPurposeEntity;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class PaymentPurposeSpecification {
  public static Specification<PaymentPurposeEntity> findPaymentPurpose(Long id) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();
      if (id != null) {
        predicate = cb.and(predicate, cb.equal(root.get("id"), id));
      }
      return predicate;
    };
  }
}
