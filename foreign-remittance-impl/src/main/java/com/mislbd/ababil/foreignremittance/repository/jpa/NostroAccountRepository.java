package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.NostroAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NostroAccountRepository extends JpaRepository<NostroAccountEntity, Long> {

    Optional<NostroAccountEntity> findByNumber(String accountNumber);

}
