package com.mislbd.ababil.foreignremittance.domain;

public enum BankType {

  SendingInstitution("Sending Institution"),
  OrderingInstitution("Ordering Institution"),
  SenderCorrespondent("Sender's Correspondent"),
  ReceiverCorrespondent("Receiver's Correspondent"),
  ThirdReimbursementInstitution("Third Reimbursement Institution"),
  IntermediaryInstitution("Intermediary Institution"),
  AccountWithInstitution("Account With Institution");

  public final String name;

  BankType(String name){
    this.name = name;
  }

}
