package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemittanceTransactionRepository
    extends JpaRepository<RemittanceTransactionEntity, Long> {}
