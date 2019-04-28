package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.repository.schema.TransactionTypeEntity;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class TransactionTypeSpecification {

  public static Specification<TransactionTypeEntity> findTransactionTypes(
      RemittanceType remittanceType) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();
      return cb.and(predicate, cb.equal(root.get("remittanceType"), remittanceType));
    };
  }
}
