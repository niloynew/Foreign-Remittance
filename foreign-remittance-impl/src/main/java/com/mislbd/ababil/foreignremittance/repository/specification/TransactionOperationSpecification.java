package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.TransactionOperationEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.TransactionTypeEntity;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class TransactionOperationSpecification {

  public static Specification<TransactionOperationEntity> findOperations(long typeId) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();
      Path<TransactionTypeEntity> typeRoot = root.get("transactionTypeEntity");
      return cb.and(predicate, cb.equal(typeRoot.get("id"), typeId));
    };
  }
}
