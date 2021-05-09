package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import java.time.LocalDate;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class RemittanceTransactionSpecification {
  public static Specification<RemittanceTransactionEntity> searchSpecification(
      String globalTransactionNo,
      RemittanceType remittanceType,
      String transactionReferenceNumber,
      String applicantName,
      String beneficiaryName,
      LocalDate fromDate,
      LocalDate toDate) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (globalTransactionNo != null) {
        predicate =
            cb.and(predicate, cb.equal(root.get("globalTransactionNo"), globalTransactionNo));
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

      if (applicantName != null) {
        predicate =
            cb.and(
                predicate,
                cb.like(
                    cb.lower(root.get("applicantName")), "%" + applicantName.toLowerCase() + "%"));
      }

      if (beneficiaryName != null) {
        predicate =
            cb.and(
                predicate,
                cb.like(
                    cb.lower(root.get("beneficiaryName")),
                    "%" + beneficiaryName.toLowerCase() + "%"));
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
