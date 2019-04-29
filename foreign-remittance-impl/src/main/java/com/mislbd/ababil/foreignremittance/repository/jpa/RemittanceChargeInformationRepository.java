package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceChargeInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemittanceChargeInformationRepository extends JpaRepository<RemittanceChargeInformationEntity, Long> {}
