package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.SwiftBankConfigurationEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.SwiftRegisterEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.TransactionTypeEntity;
import com.mislbd.swift.broker.model.raw.NostroTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TransactionBanksRepository extends JpaRepository<SwiftBankConfigurationEntity, Long>, JpaSpecificationExecutor {
    Optional<SwiftBankConfigurationEntity> findByNostroTransaction(NostroTransaction nostroTransaction);
}
