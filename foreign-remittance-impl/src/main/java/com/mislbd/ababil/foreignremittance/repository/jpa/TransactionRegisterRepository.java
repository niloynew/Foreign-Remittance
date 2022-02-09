package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.TransactionRegisterEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRegisterRepository
    extends JpaRepository<TransactionRegisterEntity, Long> {

  List<TransactionRegisterEntity> findAllByVoucherNumber(Long voucherNumber);

  List<TransactionRegisterEntity> findAllByRemittanceTransactionId(Long remittanceTransactionId);
}
