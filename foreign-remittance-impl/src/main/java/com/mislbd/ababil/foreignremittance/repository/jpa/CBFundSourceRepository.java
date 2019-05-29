package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.CBFundSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CBFundSourceRepository
    extends JpaRepository<CBFundSourceEntity, Long>, JpaSpecificationExecutor {}
