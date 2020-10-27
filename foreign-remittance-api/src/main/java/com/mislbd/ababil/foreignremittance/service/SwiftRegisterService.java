package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.domain.SwiftRegister;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.swift.broker.model.RoutingStatus;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SwiftRegisterService {
    PagedResult<SwiftRegister> getSwiftRegisters(
            Pageable pageable,
            String referenceNo,
            String senderAddress,
            String receiverAddress,
            String status,
            Date messageRoutingDateTime
            );

    List<SwiftRegister> getSwiftRegisters(
            String referenceNo,
            String senderAddress,
            String receiverAddress,
            String status,
            Date messageRoutingDateTime);

    SwiftRegister findByReferenceNumber(String referenceNumber);
}
