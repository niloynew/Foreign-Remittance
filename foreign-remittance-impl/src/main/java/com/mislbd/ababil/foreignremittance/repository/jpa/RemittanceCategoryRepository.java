package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.RemittanceCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemittanceCategoryRepository
    extends JpaRepository<RemittanceCategoryEntity, Long> {}
