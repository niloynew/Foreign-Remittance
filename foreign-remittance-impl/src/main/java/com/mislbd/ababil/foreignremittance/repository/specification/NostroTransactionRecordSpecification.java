package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.NostroTransactionRecordEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

import java.time.LocalDate;

import static org.springframework.data.jpa.domain.Specification.where;


public class NostroTransactionRecordSpecification {

    public static Specification<NostroTransactionRecordEntity> searchSpecification(String lcNo,
                                                                                   String accNo,
                                                                                   LocalDate valueDate,) {
        return (root, query, cb) -> {
            /*Predicate predicate = cb.conjunction();
            return cb.and(predicate, cb.equal(root.get("isActive"), true));*/
            return where(cb.equal(root.get("")))

        };
    }
}
