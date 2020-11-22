package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.SwiftRegister;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.swift.broker.model.RoutingStatus;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

public interface SwiftRegisterService {
  PagedResult<SwiftRegister> getSwiftRegisters(
      Pageable pageable,
      String referenceNo,
      String senderAddress,
      String receiverAddress,
      RoutingStatus status,
      Date messageRoutingDateTimeFrom,
      Date messageRoutingDateTimeTo);

  List<SwiftRegister> getSwiftRegisters(
      String referenceNo,
      String senderAddress,
      String receiverAddress,
      RoutingStatus status,
      Date messageRoutingDateTimeFrom,
      Date messageRoutingDateTimeTo);

  SwiftRegister findByReferenceNumber(String referenceNumber);

  void saveRegister(SwiftRegister swiftRegister);

  void updateRegister(SwiftRegister swiftRegister);

  void registerMessage(
      String sendersReference, String senderAddress, String receiverAddress, String message);

  Optional<SwiftRegister> findRegisterById(Long id);
}
