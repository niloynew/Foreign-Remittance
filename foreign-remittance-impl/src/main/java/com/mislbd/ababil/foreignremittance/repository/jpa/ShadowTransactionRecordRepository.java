package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.ShadowTransactionRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShadowTransactionRecordRepository
    extends JpaRepository<ShadowTransactionRecordEntity, Long>, JpaSpecificationExecutor {}
