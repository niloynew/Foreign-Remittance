package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.swift.broker.model.RoutingStatus;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
