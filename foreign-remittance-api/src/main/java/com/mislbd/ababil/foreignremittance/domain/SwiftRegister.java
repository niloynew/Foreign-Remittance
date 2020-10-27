package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.swift.broker.model.RoutingStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;

@Setter
@Getter
@Accessors(chain = true)
public class SwiftRegister {

    private long id;

    private String referenceNo;

    private String msg; // whole swift message

    private String senderAddress;

    private String receiverAddress;

    private RoutingStatus status;

    private Date messageRoutingDateTime;

    private String textBlock;

}
