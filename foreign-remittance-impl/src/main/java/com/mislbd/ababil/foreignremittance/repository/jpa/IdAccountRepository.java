package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.IdAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdAccountRepository extends JpaRepository<IdAccountEntity, Long> {
}
