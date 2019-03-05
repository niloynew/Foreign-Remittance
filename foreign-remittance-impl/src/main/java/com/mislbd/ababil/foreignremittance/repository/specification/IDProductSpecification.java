package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.IDProductEntity;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class IDProductSpecification {

  public static Specification<IDProductEntity> findIDProduct(
      String name, String code, String currency) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (name != null) {
        predicate =
            cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
      }

      if (code != null) {
        predicate = cb.and(predicate, cb.equal(root.get("code"), code));
      }

      if (currency != null) {
        predicate = cb.and(predicate, cb.equal(root.get("currency"), currency));
      }

      return predicate;
    };
  }
}
