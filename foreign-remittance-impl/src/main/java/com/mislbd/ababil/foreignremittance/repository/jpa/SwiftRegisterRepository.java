package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.NostroAccountEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.SwiftRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SwiftRegisterRepository extends JpaRepository<SwiftRegisterEntity, Long> , JpaSpecificationExecutor {
    Optional<SwiftRegisterEntity> findByReferenceNo(String referenceNumber);


}

