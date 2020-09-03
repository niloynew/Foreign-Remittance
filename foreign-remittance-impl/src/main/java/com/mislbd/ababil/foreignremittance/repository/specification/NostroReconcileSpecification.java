package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.NostroTransactionEntity;
import java.time.LocalDate;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class NostroReconcileSpecification {

  public static Specification<NostroTransactionEntity> searchSpecification(
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
      if (selected == true || selected == false) {
        predicate = cb.and(predicate, cb.equal(root.get("selected"), selected));
      }
      if (valueDate != null) {
        predicate = cb.and(predicate, cb.equal(root.get("valueDate"), valueDate));
      }

      return predicate;
    };
  }
}
