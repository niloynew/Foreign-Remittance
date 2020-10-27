package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.domain.SwiftRegister;
import com.mislbd.ababil.foreignremittance.repository.jpa.SwiftRegisterRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.SwiftRegisterEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class SwiftRegisterMapper {
    private final SwiftRegisterRepository swiftRegisterRepository;

    public SwiftRegisterMapper(SwiftRegisterRepository swiftRegisterRepository) {
        this.swiftRegisterRepository = swiftRegisterRepository;
    }

    public ResultMapper<SwiftRegisterEntity, SwiftRegister> entityToDomain() {
        return entity ->
                new SwiftRegister()
                        .setId(entity.getId())
                        .setReferenceNo(entity.getReferenceNo())
                        .setMsg(entity.getMsg())
                        .setSenderAddress(entity.getReceiverAddress())
                        .setReceiverAddress(entity.getReceiverAddress())
                        .setStatus(entity.getStatus())
                        .setMessageRoutingDateTime(entity.getMessageRoutingDateTime())
                        .setTextBlock(entity.getTextBlock());
    }

    public ResultMapper<SwiftRegister, SwiftRegisterEntity> domainToEntity() {
        return domain ->
                swiftRegisterRepository
                        .findByReferenceNo(domain.getReferenceNo())
                        .orElseGet(SwiftRegisterEntity::new)
                        .setId(domain.getId())
                        .setReferenceNo(domain.getReferenceNo())
                        .setMsg(domain.getMsg())
                        .setSenderAddress(domain.getSenderAddress())
                        .setReceiverAddress(domain.getReceiverAddress())
                        .setMessageRoutingDateTime(domain.getMessageRoutingDateTime())
                        .setStatus(domain.getStatus())
                        .setTextBlock(domain.getTextBlock());
    }


}
