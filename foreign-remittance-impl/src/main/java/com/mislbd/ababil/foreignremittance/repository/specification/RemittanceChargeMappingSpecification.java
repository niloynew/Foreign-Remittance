package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeMappingEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.TransactionTypeEntity;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class RemittanceChargeMappingSpecification {

  public static Specification<RemittanceChargeMappingEntity> findSpecificChargeMappings(
      Long transactionType, Long chargeId, Boolean chargeModifiable) {

    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();
      Path<TransactionTypeEntity> transactionTypeRoot = root.get("transactionType");
      Path<RemittanceChargeEntity> remittanceChargeRoot = root.get("remittanceCharge");

      if (transactionType != null) {
        predicate = cb.and(predicate, cb.equal(transactionTypeRoot.get("id"), transactionType));
      }

      if (chargeId != null) {
        predicate = cb.and(predicate, cb.equal(remittanceChargeRoot.get("id"), transactionType));
      }

      if (chargeModifiable != null) {
        predicate = cb.and(predicate, cb.equal(root.get("chargeModifiable"), chargeModifiable));
      }

      return predicate;
    };
  }
}
