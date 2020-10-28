package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.asset.commons.repository.schema.BaseEntity;
import com.mislbd.swift.broker.model.RoutingStatus;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.SWIFT_REGISTER)
public class SwiftRegisterEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "SWIFT_REGISTER_ID_GEN")
  @SequenceGenerator(
      name = "SWIFT_REGISTER_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.SWIFT_REGISTER_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "REFERENCE_NUMBER")
  private String referenceNo;

  @Lob
  @Column(name = "MESSAGE")
  private String msg; // whole swift message

  @Column(name = "SENDERS_ADDRESS")
  private String senderAddress;

  @Column(name = "RECEIVER_ADDRESS")
  private String receiverAddress;

  @Column(name = "ROUTING_STATUS")
  private RoutingStatus status;

  @Column(name = "ROUTING_TIME")
  private Date messageRoutingDateTime;

  @Lob
  @Column(name = "TEXTBLOCK")
  private String textBlock;
}
