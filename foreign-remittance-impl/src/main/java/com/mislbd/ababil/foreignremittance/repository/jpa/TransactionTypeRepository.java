package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.repository.schema.TransactionTypeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionTypeRepository
    extends JpaRepository<TransactionTypeEntity, Long>, JpaSpecificationExecutor {
  Optional<TransactionTypeEntity> findByNameIsLike(String name);

  Optional<TransactionTypeEntity> findByRemittanceTypeAndNameIsLike(
      RemittanceType type, String name);
}
