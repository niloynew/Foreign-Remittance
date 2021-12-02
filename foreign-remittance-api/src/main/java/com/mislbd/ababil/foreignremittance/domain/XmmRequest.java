package com.mislbd.ababil.foreignremittance.domain;

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

  private XmmMessageType messageType;

  private String referenceNumber;

  private String domainGroup;

  private String user;

  private String userBranch;
}
