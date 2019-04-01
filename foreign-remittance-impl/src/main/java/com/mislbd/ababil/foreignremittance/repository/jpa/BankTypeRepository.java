package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.BankTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTypeRepository extends JpaRepository<BankTypeEntity, Long> {
}
