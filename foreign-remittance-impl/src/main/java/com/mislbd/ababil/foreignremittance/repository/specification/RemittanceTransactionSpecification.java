package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransactionStatus;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import java.time.LocalDate;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class RemittanceTransactionSpecification {
  public static Specification<RemittanceTransactionEntity> searchSpecification(
      RemittanceTransactionStatus status,
      RemittanceType remittanceType,
      String transactionReferenceNumber,
      LocalDate fromDate,
      LocalDate toDate) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (status != null) {
        predicate = cb.and(predicate, cb.equal(root.get("transactionStatus"), status));
      } else {
        predicate =
            cb.and(
                predicate,
                cb.equal(root.get("transactionStatus"), RemittanceTransactionStatus.Succeed));
      }

      if (remittanceType != null) {
        predicate = cb.and(predicate, cb.equal(root.get("remittanceType"), remittanceType));
      }

      if (transactionReferenceNumber != null) {
        predicate =
            cb.and(
                predicate,
                cb.equal(root.get("transactionReferenceNumber"), transactionReferenceNumber));
      }

      if (fromDate != null) {
        predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("valueDate"), fromDate));
      }

      if (toDate != null) {
        predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("valueDate"), toDate));
      }

      predicate = cb.and(predicate, cb.equal(root.get("valid"), true));

      return predicate;
    };
  }
}
