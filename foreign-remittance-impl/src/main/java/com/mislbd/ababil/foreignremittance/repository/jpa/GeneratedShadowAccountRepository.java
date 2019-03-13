package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.GeneratedShadowAccountEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface GeneratedShadowAccountRepository
    extends JpaRepository<GeneratedShadowAccountEntity, Long> {

  @Query("Select coalesce(max(ga.id),'0') From GeneratedShadowAccountEntity ga")
  long maxId();

  List<GeneratedShadowAccountEntity> findAllByReservationEndsOnLessThan(LocalDateTime now);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<GeneratedShadowAccountEntity> findFirstByReservedAndBranchIdAndProductId(
      boolean b, long userBranchId, long productId);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<GeneratedShadowAccountEntity> findOneByAccountNumber(String accountNumber);
}
