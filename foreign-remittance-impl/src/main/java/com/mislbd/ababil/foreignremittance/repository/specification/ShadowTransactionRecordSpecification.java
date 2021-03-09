package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.ShadowTransactionRecordEntity;
import java.time.LocalDate;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ShadowTransactionRecordSpecification {

  public static Specification<ShadowTransactionRecordEntity> searchSpecification(
      String number,
      String name,
      String shadowAccountNumber,
      String bank,
      String branch,
      LocalDate accountOpenDate,
      String currency,
      String product,
      Boolean isActive) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();
      return predicate;
    };
  }
}
