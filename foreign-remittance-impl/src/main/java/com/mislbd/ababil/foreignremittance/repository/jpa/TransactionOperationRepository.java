package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.TransactionOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionOperationRepository
    extends JpaRepository<TransactionOperationEntity, Long>, JpaSpecificationExecutor {}
