package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.TransactionRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRegisterRepository extends JpaRepository<TransactionRegisterEntity, Long> {

    List<TransactionRegisterEntity> findAllByVoucherNumber(Long voucherNumber);
    List<TransactionRegisterEntity> findAllByRemittanceTransactionId(Long remittanceTransactionId);
}
