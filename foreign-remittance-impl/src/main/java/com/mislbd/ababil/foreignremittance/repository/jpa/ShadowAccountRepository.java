package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShadowAccountRepository
    extends JpaRepository<ShadowAccountEntity, Long>, JpaSpecificationExecutor {

  Optional<ShadowAccountEntity> findByNumber(String accountNumber);

  Optional<ShadowAccountEntity> findByNostroAccountNumber(String accountNumber);

  List<ShadowAccountEntity> findAllByBranchId(Long branchId);
}
