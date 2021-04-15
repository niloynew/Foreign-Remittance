package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.domain.OtherCbsSystemSettlementStatus;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowTransactionRecordEntity;
import java.time.LocalDate;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class NostroTransactionRecordSpecification {
  public static Specification<ShadowTransactionRecordEntity> searchSpecification(
      Long accountId,
      LocalDate fromDate,
      LocalDate toDate,
      OtherCbsSystemSettlementStatus reconcileStatus) {
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

      if (reconcileStatus != null) {
        predicate = cb.and(predicate, cb.equal(root.get("reconcileStatus"), reconcileStatus));
      } else {
        predicate =
            cb.and(
                predicate,
                cb.equal(root.get("reconcileStatus"), OtherCbsSystemSettlementStatus.Pending));
      }

      predicate.in(query.orderBy(cb.desc(root.get("id"))));

      return predicate;
    };
  }
}
