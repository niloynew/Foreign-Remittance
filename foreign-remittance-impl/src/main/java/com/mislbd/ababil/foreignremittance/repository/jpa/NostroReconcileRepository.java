package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.NostroReconcileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NostroReconcileRepository
    extends JpaRepository<NostroReconcileEntity, Long>, JpaSpecificationExecutor {}
