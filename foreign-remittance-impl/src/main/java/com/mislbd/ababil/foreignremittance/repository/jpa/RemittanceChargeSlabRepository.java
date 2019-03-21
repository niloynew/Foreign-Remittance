package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeSlabEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemittanceChargeSlabRepository
    extends JpaRepository<RemittanceChargeSlabEntity, Long> {}
