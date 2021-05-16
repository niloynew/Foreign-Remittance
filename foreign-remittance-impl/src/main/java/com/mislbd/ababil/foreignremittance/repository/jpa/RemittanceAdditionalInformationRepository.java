package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceAdditionalInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemittanceAdditionalInformationRepository
    extends JpaRepository<RemittanceAdditionalInformationEntity, Long> {}
