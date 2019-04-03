package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.ExportLC;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface ExportLCService {

  PagedResult<ExportLC> getLcs(
      Pageable pageable,
      String name,
      String ownerName,
      String address,
      String country,
      String cpName,
      String cpEmail);

  List<ExportLC> getLcList(
      String name, String ownerName, String address, String country, String cpName, String cpEmail);

  Optional<ExportLC> getLc(Long id);
}
