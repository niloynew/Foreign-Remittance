package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.ReconcileTxnLogEntity;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReconcileTxnLogRepository
    extends JpaRepository<ReconcileTxnLogEntity, Long>, JpaSpecificationExecutor {
  Optional<ReconcileTxnLogEntity> findByGlobalTxnNo(BigDecimal globalTxnNo);
}
