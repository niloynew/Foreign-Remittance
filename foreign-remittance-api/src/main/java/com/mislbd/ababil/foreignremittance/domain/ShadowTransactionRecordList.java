package com.mislbd.ababil.foreignremittance.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShadowTransactionRecordList {
  private List<ShadowTransactionRecord> shadowTransactionRecords;
}
