package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.SenderOrReceiverCustomerEntity;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class SenderOrReceiverCustomerSpecification {

  public static Specification<SenderOrReceiverCustomerEntity> findSpecificLcs(
      String name,
      String ownerName,
      String address,
      String country,
      String cpName,
      String cpEmail) {

    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();
      if (name != null) {
        predicate =
            cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
      }

      if (ownerName != null) {
        predicate =
            cb.and(
                predicate,
                cb.like(cb.lower(root.get("ownerName")), "%" + ownerName.toLowerCase() + "%"));
      }

      if (address != null) {
        predicate =
            cb.and(
                predicate,
                cb.like(cb.lower(root.get("address")), "%" + address.toLowerCase() + "%"));
      }

      if (country != null) {
        predicate =
            cb.and(
                predicate,
                cb.like(cb.lower(root.get("country")), "%" + country.toLowerCase() + "%"));
      }

      if (cpName != null) {
        predicate =
            cb.and(
                predicate, cb.like(cb.lower(root.get("cpName")), "%" + cpName.toLowerCase() + "%"));
      }

      if (cpEmail != null) {
        predicate =
            cb.and(
                predicate,
                cb.like(cb.lower(root.get("cpEmail")), "%" + cpEmail.toLowerCase() + "%"));
      }
      return predicate;
    };
  }
}
