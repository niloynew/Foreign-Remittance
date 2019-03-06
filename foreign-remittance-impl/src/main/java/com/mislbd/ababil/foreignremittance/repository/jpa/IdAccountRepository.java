package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.IdAccountEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdAccountRepository extends JpaRepository<IdAccountEntity, Long> {
  IdAccountEntity findByNumber(String idAccNumber);

  List<IdAccountEntity> findByBrId(long brId);
}
