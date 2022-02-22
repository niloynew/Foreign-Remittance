package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.AdditionalInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalInformationRepository
    extends JpaRepository<AdditionalInformationEntity, Long> {}
