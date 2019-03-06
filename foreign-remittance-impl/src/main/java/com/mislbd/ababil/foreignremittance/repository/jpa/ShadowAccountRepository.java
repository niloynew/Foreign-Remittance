package com.mislbd.ababil.foreignremittance.repository.jpa;

import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShadowAccountRepository extends JpaRepository<ShadowAccountEntity, Long> {

}
