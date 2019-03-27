package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemittanceChargeMappingRepository
    extends JpaRepository<RemittanceChargeMappingEntity, Long> {}
