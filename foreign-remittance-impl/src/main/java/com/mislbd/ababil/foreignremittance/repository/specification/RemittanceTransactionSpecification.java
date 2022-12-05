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
      String remittanceTypeName,
      String transactionReferenceNumber,
      LocalDate fromDate,
      LocalDate toDate,
      String salesContractNumber,
      Long applicantId,
      Long beneficiaryId) {
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

      if (remittanceTypeName != null) {
        predicate =
            cb.and(
                predicate, cb.equal(root.get("transactionType").get("name"), remittanceTypeName));
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

      if (salesContractNumber != null && !salesContractNumber.isEmpty()) {
        predicate =
            cb.and(
                predicate,
                cb.like(root.get("salesContractNumber"), "%" + salesContractNumber + "%"));
      }

      if (applicantId != null) {
        predicate = cb.and(predicate, cb.equal(root.get("applicantId"), applicantId));
      }

      if (beneficiaryId != null) {
        predicate = cb.and(predicate, cb.equal(root.get("beneficiaryId"), beneficiaryId));
      }

      predicate.in(query.orderBy(cb.desc(root.get("id"))));

      return predicate;
    };
  }
}
