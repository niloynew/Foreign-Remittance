package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.SwiftRegisterEntity;
import com.mislbd.swift.broker.model.RoutingStatus;
import java.util.Date;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class SwiftRegisterSpecification {
  public static Specification<SwiftRegisterEntity> searchSwiftRegisters(
      String referenceNo,
      String senderAddress,
      String receiverAddress,
      RoutingStatus status,
      Date messageRoutingDateTimeFrom,
      Date messageRoutingDateTimeTo) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (referenceNo != null) {
        predicate = cb.and(predicate, cb.equal(root.get("referenceNo"), referenceNo));
      }

      if (senderAddress != null) {
        predicate = cb.and(predicate, cb.equal(root.get("senderAddress"), senderAddress));
      }

      if (receiverAddress != null) {
        predicate = cb.and(predicate, cb.equal(root.get("receiverAddress"), receiverAddress));
      }

      if (status != null) {
        predicate = cb.and(predicate, cb.equal(root.get("status"), status));
      }

      if (messageRoutingDateTimeFrom != null && messageRoutingDateTimeTo != null) {
        predicate =
            cb.and(
                predicate,
                cb.between(
                    root.get("messageRoutingDateTime"),
                    messageRoutingDateTimeFrom,
                    messageRoutingDateTimeTo));
      }

      return predicate;
    };
  }
}
