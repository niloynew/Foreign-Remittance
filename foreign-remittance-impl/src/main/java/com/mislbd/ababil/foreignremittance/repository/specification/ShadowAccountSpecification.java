package com.mislbd.ababil.foreignremittance.repository.specification;

import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import org.springframework.data.jpa.domain.Specification;

public class ShadowAccountSpecification {

    public static Specification<ShadowAccountEntity> searchSpecification(){
        return (root, query, cb) -> {
            return null;
        };
    }
}
