package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.IdAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdAccountRepository extends JpaRepository<IdAccountEntity, Long> {
    IdAccountEntity findByNumber(String idAccNumber);
    List<IdAccountEntity> findByBrId(long brId);
}
