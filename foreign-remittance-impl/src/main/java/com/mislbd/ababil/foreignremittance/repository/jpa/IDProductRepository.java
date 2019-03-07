package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.IDProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDProductRepository extends JpaRepository<IDProductEntity, Long> {
  Page<IDProductEntity> findAll(Specification<IDProductEntity> idProduct, Pageable pageable);
}
