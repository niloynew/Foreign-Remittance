package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.IDProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDProductRepository extends JpaRepository <IDProductEntity, Long> {
}
