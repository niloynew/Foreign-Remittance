package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import java.time.LocalDate;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class RemittanceTransactionSpecification {
  public static Specification<RemittanceTransaction> searchSpecification(
      String voucherNumber,
      RemittanceType remittanceType,
      String transactionReferenceNumber,
      String applicantName,
      String beneficiaryName,
      LocalDate fromDate,
      LocalDate toDate) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (voucherNumber != null) {
        predicate = cb.and(predicate, cb.equal(root.get("voucherNumber"), voucherNumber));
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
        predicate = cb.and(predicate, cb.equal(root.get("fromDate"), fromDate));
      }

      if (toDate != null) {
        predicate = cb.and(predicate, cb.equal(root.get("toDate"), toDate));
      }

      return predicate;
    };
  }
}
