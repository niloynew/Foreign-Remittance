package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountNumberSequenceEntity;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

/** Created by Mahbub islam on 11/17/17. */
public interface ShadowAccountNumberSequenceRepository
    extends JpaRepository<ShadowAccountNumberSequenceEntity, Long> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<ShadowAccountNumberSequenceEntity> findOneByBranchIdAndProductId(
      long branchId, long productId);

  @Query("Select coalesce(max(ans.id),'0') From ShadowAccountNumberSequenceEntity ans")
  long maxId();
}
