package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceTransactionEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RemittanceTransactionRepository
    extends JpaRepository<RemittanceTransactionEntity, Long>, JpaSpecificationExecutor {
  Optional<RemittanceTransactionEntity> findByTransactionReferenceNumber(String referenceNumber);

  Boolean existsByTransactionReferenceNumber(String referenceNumber);

  @Query(value = "SELECT S_ID_TXN_REFERENCE_NUMBER.nextval FROM dual", nativeQuery = true)
  Long generateTransactionReferenceNumberSequence();

  @Query(value = "SELECT S_ID_INWARD_TXN_REF_NUMBER.nextval FROM dual", nativeQuery = true)
  Long generateInwardTransactionReferenceNumberSequence();

  @Modifying(clearAutomatically = true)
  @Query(value = "UPDATE RemittanceTransactionEntity SET customerId=?1 WHERE customerId IN ?2")
  void updateCustomerIds(Long originalCustomerId, List<Long> duplicateCustomerId);

  @Query(value = "SELECT S_ID_REQUEST_REFERENCE_NUMBER.nextval FROM dual", nativeQuery = true)
  Long generateRequestIdSequence();

  List<RemittanceTransactionEntity>
      findAllByBeneficiaryIdAndShadowAccountCurrencyAndTransactionType_Id(
          Long customerId, String currency, Long transactionTypeId);

  List<RemittanceTransactionEntity>
      findAllByBeneficiaryIdAndShadowAccountCurrencyAndTransactionType_IdAndSalesContractNumber(
          Long customerId, String currency, Long transactionTypeId, String salesContractNumber);
}
