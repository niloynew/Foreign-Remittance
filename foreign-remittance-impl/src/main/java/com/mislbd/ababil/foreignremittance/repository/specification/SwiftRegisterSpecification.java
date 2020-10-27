package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.domain.AccountStatement;
import com.mislbd.ababil.foreignremittance.repository.schema.IDProductEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.SwiftRegisterEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.Date;

public class SwiftRegisterSpecification {
    public static Specification<SwiftRegisterEntity> searchSwiftRegisters(
             String referenceNo, String senderAddress, String receiverAddress, String status, Date messageRoutingDateTime) {
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

            if (messageRoutingDateTime != null) {
                predicate = cb.and(predicate, cb.equal(root.get("messageRoutingDateTime"), messageRoutingDateTime));
            }

            return predicate;
        };
    }
}
