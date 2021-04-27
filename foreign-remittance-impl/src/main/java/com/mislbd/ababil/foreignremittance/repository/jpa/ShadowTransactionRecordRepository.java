package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowTransactionRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ShadowTransactionRecordRepository
    extends JpaRepository<ShadowTransactionRecordEntity, Long>, JpaSpecificationExecutor {

    Optional<ShadowTransactionRecordEntity> findByGlobalTxnNo(Long globalTransactionNo);
}
