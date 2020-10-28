package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.SwiftRegisterEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SwiftRegisterRepository
    extends JpaRepository<SwiftRegisterEntity, Long>, JpaSpecificationExecutor {
  Optional<SwiftRegisterEntity> findByReferenceNo(String referenceNumber);
}
