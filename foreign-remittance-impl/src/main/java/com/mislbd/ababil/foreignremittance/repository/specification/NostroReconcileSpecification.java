package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.NostroReconcileEntity;
import java.time.LocalDate;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class NostroReconcileSpecification {

  public static Specification<NostroReconcileEntity> searchSpecification(
      Long id, String accNo, boolean selected, LocalDate valueDate) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (id != null) {
        predicate = cb.and(predicate, cb.equal(root.get("id"), id));
      }
      if (accNo != null) {
        predicate = cb.and(predicate, cb.equal(root.get("accountNo"), accNo));
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
