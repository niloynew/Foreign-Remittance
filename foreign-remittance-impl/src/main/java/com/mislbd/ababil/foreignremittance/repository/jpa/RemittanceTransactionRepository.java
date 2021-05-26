package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface RemittanceTransactionRepository
    extends JpaRepository<RemittanceTransactionEntity, Long>, JpaSpecificationExecutor {
  Optional<RemittanceTransactionEntity> findByTransactionReferenceNumber(String referenceNumber);

  @Query(value = "SELECT S_ID_TXN_REFERENCE_NUMBER_TT.nextval FROM dual", nativeQuery = true)
  Long generateTransactionReferenceNumberSequenceForTT();

  @Query(value = "SELECT S_ID_TXN_REFERENCE_NUMBER_DD.nextval FROM dual", nativeQuery = true)
  Long generateTransactionReferenceNumberSequenceForDD();
}
