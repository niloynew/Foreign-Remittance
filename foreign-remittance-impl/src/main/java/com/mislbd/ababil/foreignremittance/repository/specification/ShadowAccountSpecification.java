package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.IDProductEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import java.time.LocalDate;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ShadowAccountSpecification {

  public static Specification<ShadowAccountEntity> searchSpecification(
      String number,
      String name,
      String nostroAccountNumber,
      String bank,
      String branch,
      LocalDate accountOpenDate,
      String currency,
      String product,
      Boolean isActive) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();
      Path<IDProductEntity> productRoot = root.get("product");

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

      if (accountOpenDate != null) {
        predicate = cb.and(predicate, cb.equal(root.get("accountOpenDate"), accountOpenDate));
      }

      if (currency != null) {
        predicate = cb.and(predicate, cb.equal(root.get("currencyCode"), currency));
      }

      if (product != null) {
        predicate = cb.and(predicate, cb.equal(productRoot.get("id"), product));
      }

      return cb.and(predicate, cb.equal(root.get("isActive"), isActive));
    };
  }
}
