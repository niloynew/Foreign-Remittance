package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ShadowAccountSpecification {

  public static Specification<ShadowAccountEntity> searchSpecification(
      String number,
      String name,
      String nostroAccountNumber,
      String bank,
      String branch,
      String accountopenDate,
      String currency,
      String product) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (number != null) {
        predicate = cb.and(predicate, cb.equal(root.get("number"), number));
      }

      if (name != null) {
        predicate =
            cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
      }

      if (nostroAccountNumber != null) {
        predicate =
            cb.and(predicate, cb.equal(root.get("nostroAccountNumber"), nostroAccountNumber));
      }

      if (bank != null) {
        predicate = cb.and(predicate, cb.equal(root.get("bankId"), bank));
      }

      if (branch != null) {
        predicate = cb.and(predicate, cb.equal(root.get("branchId"), branch));
      }

      if (accountopenDate != null) {
        predicate = cb.and(predicate, cb.equal(root.get("accountOpenDate"), accountopenDate));
      }

      if (currency != null) {
        predicate = cb.and(predicate, cb.equal(root.get("currencyCode"), currency));
      }

      if (product != null) {
        predicate = cb.and(predicate, cb.equal(root.get("product"), product));
      }

      return cb.and(predicate, cb.equal(root.get("isActive"), true));
    };
  }
}
