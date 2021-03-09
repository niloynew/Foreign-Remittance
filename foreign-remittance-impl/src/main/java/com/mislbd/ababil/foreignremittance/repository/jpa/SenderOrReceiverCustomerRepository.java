package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.SenderOrReceiverCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SenderOrReceiverCustomerRepository
    extends JpaRepository<SenderOrReceiverCustomerEntity, Long>, JpaSpecificationExecutor {}
