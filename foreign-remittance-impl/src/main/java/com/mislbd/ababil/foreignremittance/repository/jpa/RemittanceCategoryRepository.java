package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceCategoryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RemittanceCategoryRepository
    extends JpaRepository<RemittanceCategoryEntity, Long>, JpaSpecificationExecutor {

  List<RemittanceCategoryEntity> findAllByRemittanceType(RemittanceType remittanceType);
}
