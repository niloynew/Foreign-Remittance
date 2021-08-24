package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.NostroTransactionRecordEntity;
import java.time.LocalDate;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class NostroReconcileSpecification {

  public static Specification<NostroTransactionRecordEntity> searchSpecification(
      Long id, String accNo, String advBranch, boolean selected, LocalDate valueDate) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (id != null) {
        predicate = cb.and(predicate, cb.equal(root.get("id"), id));
      }
      if (accNo != null) {
        predicate = cb.and(predicate, cb.equal(root.get("accountNo"), accNo));
      }
      if (advBranch != null) {
        predicate = cb.and(predicate, cb.equal(root.get("advBranch"), advBranch));
      }
      predicate = cb.and(predicate, cb.equal(root.get("selected"), selected));
      if (valueDate != null) {
        predicate = cb.and(predicate, cb.equal(root.get("valueDate"), valueDate));
      }

      return predicate;
    };
  }
}
