package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ShadowAccountSpecification {

  public static Specification<ShadowAccountEntity> searchSpecification() {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();
      return cb.and(predicate, cb.equal(root.get("isActive"), true));
    };
  }
}
