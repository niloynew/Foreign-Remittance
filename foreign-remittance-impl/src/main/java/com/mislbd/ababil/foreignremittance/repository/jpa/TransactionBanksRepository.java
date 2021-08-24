package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.SwiftBankConfigurationEntity;
import com.mislbd.swift.broker.model.raw.NostroTransaction;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionBanksRepository
    extends JpaRepository<SwiftBankConfigurationEntity, Long>, JpaSpecificationExecutor {
  Optional<SwiftBankConfigurationEntity> findByNostroTransaction(
      NostroTransaction nostroTransaction);
}
