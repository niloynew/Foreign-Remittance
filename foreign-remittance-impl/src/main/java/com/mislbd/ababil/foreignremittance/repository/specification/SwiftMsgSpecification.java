package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceMsgEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class SwiftMsgSpecification {
  public static Specification<RemittanceMsgEntity> searchSpecification(
      String msgType, String lcNo, BigDecimal amount, LocalDate valueDate) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      predicate = cb.and(predicate, cb.equal(root.get("processed"), false));
      if (msgType != null) {
        predicate = cb.and(predicate, cb.equal(root.get("messageType"), msgType));
      }
      if (lcNo != null) {
        predicate = cb.and(predicate, cb.equal(root.get("lcNumber"), lcNo));
      }
      if (amount != null) {
        predicate = cb.and(predicate, cb.equal(root.get("amount"), amount));
      }
      if (valueDate != null) {
        predicate = cb.and(predicate, cb.equal(root.get("valueDate"), valueDate));
        ;
      }
      return predicate;
    };
  }
}
