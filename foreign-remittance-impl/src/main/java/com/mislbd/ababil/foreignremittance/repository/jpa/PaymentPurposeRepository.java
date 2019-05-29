package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.PaymentPurposeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentPurposeRepository
    extends JpaRepository<PaymentPurposeEntity, Long>, JpaSpecificationExecutor {}
