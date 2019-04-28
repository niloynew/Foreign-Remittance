package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.NostroTransactionRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NostroTransactionRecordRepository
    extends JpaRepository<NostroTransactionRecordEntity, Long>, JpaSpecificationExecutor {}
