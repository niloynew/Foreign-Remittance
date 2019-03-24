package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.TransactionOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionOperationRepository
    extends JpaRepository<TransactionOperationEntity, Long> {}
