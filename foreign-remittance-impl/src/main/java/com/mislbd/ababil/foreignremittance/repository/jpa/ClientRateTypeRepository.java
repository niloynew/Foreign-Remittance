package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.RateTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRateTypeRepository extends JpaRepository<RateTypeEntity, Long> {}
