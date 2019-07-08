package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.domain.AccountStatement;
import java.time.LocalDate;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class AccountStatementSpecification {
  public static Specification<AccountStatement> searchSpecification(
      Long accountId, LocalDate fromDate, LocalDate toDate) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (accountId != null) {
        predicate = cb.and(predicate, cb.equal(root.get("accountId"), accountId));
      }

      if (fromDate != null) {
        predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("txnDate"), fromDate));
      }

      if (toDate != null) {
        predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("txnDate"), toDate));
      }

      return predicate;
    };
  }
}
