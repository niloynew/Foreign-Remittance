package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.NostroAccountEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NostroAccountRepository extends JpaRepository<NostroAccountEntity, Long> {

  Optional<NostroAccountEntity> findByNumber(String accountNumber);
}
