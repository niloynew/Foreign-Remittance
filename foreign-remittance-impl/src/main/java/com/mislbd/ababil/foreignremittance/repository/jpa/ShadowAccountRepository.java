package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShadowAccountRepository
    extends JpaRepository<ShadowAccountEntity, Long>, JpaSpecificationExecutor {}
