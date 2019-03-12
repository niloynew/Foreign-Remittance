package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.AccountNumberSequenceEntity;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

/** Created by Mahbub islam on 11/17/17. */
public interface AccountNumberSequenceRepository
    extends JpaRepository<AccountNumberSequenceEntity, Long> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<AccountNumberSequenceEntity> findOneByBranchIdAndProductId(
      long branchId, long productId);

  @Query("Select coalesce(max(ans.id),'0') From AccountNumberSequenceEntity ans")
  long maxId();
}
