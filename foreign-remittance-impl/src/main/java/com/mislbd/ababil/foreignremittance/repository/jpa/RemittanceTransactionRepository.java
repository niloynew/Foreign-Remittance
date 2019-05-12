package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RemittanceTransactionRepository
    extends JpaRepository<RemittanceTransactionEntity, Long>, JpaSpecificationExecutor {}
