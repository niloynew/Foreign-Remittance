package com.mislbd.ababil.foreignremittance.domain;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Responsibility:
 *
 * @author Md Erfan Ullah Bhuiyan
 * @since 16-Aug-2020
 */
@Getter
@Setter
@Accessors(chain = true)
public class XmmRequest {

  @NotNull(message = "Message type can't be empty")
  private XmmMessageType messageType;

  @NotNull(message = "Reference number can't be empty")
  private String referenceNumber;

  private String domainGroup;

  private String user;

  private String userBranch;
}
