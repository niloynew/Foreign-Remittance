package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.SwiftRegister;
import com.mislbd.ababil.foreignremittance.exception.AccountNotFoundException;
import com.mislbd.ababil.foreignremittance.exception.SwiftRegisterNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.CBFundSourceMapper;
import com.mislbd.ababil.foreignremittance.mapper.SwiftRegisterMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.CBFundSourceRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.SwiftRegisterRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.CBFundSourceSpecification;
import com.mislbd.ababil.foreignremittance.repository.specification.SwiftRegisterSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SwiftRegisterServiceImpl implements SwiftRegisterService {

    private final SwiftRegisterRepository swiftRegisterRepository;
    private final SwiftRegisterMapper swiftRegisterMapper;

    public SwiftRegisterServiceImpl(
            SwiftRegisterRepository swiftRegisterRepository, SwiftRegisterMapper swiftRegisterMapper) {
        this.swiftRegisterRepository = swiftRegisterRepository;
        this.swiftRegisterMapper = swiftRegisterMapper;
    }

    @Override
    public PagedResult<SwiftRegister> getSwiftRegisters(Pageable pageable, String referenceNo, String senderAddress, String receiverAddress, String status, Date messageRoutingDateTime) {
        return PagedResultBuilder.build(
                swiftRegisterRepository.findAll(SwiftRegisterSpecification.searchSwiftRegisters(referenceNo, senderAddress, receiverAddress, status, messageRoutingDateTime), pageable),
                swiftRegisterMapper.entityToDomain());
    }

    @Override
    public List<SwiftRegister> getSwiftRegisters(String referenceNo, String senderAddress, String receiverAddress, String status, Date messageRoutingDateTime) {
        return ListResultBuilder.build(
                swiftRegisterRepository.findAll(SwiftRegisterSpecification.searchSwiftRegisters(referenceNo, senderAddress, receiverAddress, status, messageRoutingDateTime)),
                swiftRegisterMapper.entityToDomain());
    }

    @Override
    public SwiftRegister findByReferenceNumber(String referenceNumber) {
        return swiftRegisterMapper
                .entityToDomain()
                .map(swiftRegisterRepository.findByReferenceNo(referenceNumber).orElseThrow(SwiftRegisterNotFoundException::new));

    }
}
