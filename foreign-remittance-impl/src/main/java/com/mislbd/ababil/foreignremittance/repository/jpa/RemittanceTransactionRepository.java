package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.SwiftRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RemittanceTransactionRepository
    extends JpaRepository<RemittanceTransactionEntity, Long>, JpaSpecificationExecutor {
    Optional<RemittanceTransactionEntity> findByTransactionReferenceNumber(String referenceNumber);
}
