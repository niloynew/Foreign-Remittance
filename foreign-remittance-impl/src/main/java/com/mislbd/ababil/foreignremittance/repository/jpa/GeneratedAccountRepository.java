package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.GeneratedAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GeneratedAccountRepository extends JpaRepository<GeneratedAccountEntity, Long> {

    @Query("Select coalesce(max(ga.id),'0') From GeneratedAccountEntity ga")
    long maxId();

    List<GeneratedAccountEntity> findAllByReservationEndsOnLessThan(LocalDateTime now);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<GeneratedAccountEntity> findFirstByReservedAndBranchIdAndProductId(
            boolean b, long userBranchId, long productId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<GeneratedAccountEntity> findOneByAccountNumber(String accountNumber);
}