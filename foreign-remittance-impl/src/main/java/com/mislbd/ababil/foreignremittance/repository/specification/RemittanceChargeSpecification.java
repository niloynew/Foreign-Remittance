package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.domain.ChargeAccountType;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeEntity;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class RemittanceChargeSpecification {

  public static Specification<RemittanceChargeEntity> findAllCharges(
      String chargeName,
      ChargeAccountType chargeAccountType,
      ChargeAccountType vatAccountType,
      Boolean status) {

    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();
      if (chargeName != null) {
        predicate =
            cb.and(
                predicate,
                cb.like(cb.lower(root.get("chargeName")), "%" + chargeName.toLowerCase() + "%"));
      }
      if (chargeAccountType != null) {
        predicate = cb.and(predicate, cb.equal(root.get("chargeAccountType"), chargeAccountType));
      }
      if (vatAccountType != null) {
        predicate = cb.and(predicate, cb.equal(root.get("vatAccountType"), vatAccountType));
      }
      if (status != null) {
        predicate = cb.and(predicate, cb.equal(root.get("active"), status));
      }

      return predicate;
    };
  }
}
