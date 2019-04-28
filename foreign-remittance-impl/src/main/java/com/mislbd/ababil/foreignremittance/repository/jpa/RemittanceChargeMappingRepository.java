package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RemittanceChargeMappingRepository
    extends JpaRepository<RemittanceChargeMappingEntity, Long>, JpaSpecificationExecutor {}
