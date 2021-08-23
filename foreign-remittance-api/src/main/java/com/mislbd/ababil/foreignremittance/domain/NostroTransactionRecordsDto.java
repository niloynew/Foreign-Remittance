package com.mislbd.ababil.foreignremittance.domain;

import java.util.List;

public class NostroTransactionRecordsDto {

  private List<NostroTransactionRecord> nostroAccountTransactionRecordsList;

  public NostroTransactionRecordsDto() {}

  public List<NostroTransactionRecord> getNostroAccountTransactionRecordsList() {
    return nostroAccountTransactionRecordsList;
  }

  public void setNostroAccountTransactionRecordsList(
      List<NostroTransactionRecord> nostroAccountTransactionRecordsList) {
    this.nostroAccountTransactionRecordsList = nostroAccountTransactionRecordsList;
  }
}
