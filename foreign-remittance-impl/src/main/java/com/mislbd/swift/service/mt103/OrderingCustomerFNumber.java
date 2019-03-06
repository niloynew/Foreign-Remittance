package com.mislbd.swift.service.mt103;

public enum OrderingCustomerFNumber {
  Name_of_the_Ordering_Customer(1),
  Address_Line(2),
  Country_and_Town(3),
  Date_of_Birth(4),
  Place_of_Birth(5),
  Customer_Identification_Number(6),
  National_Identity_Number(7),
  Additional_Information(8);

  private int numVal;

  OrderingCustomerFNumber(int numVal) {
    this.numVal = numVal;
  }

  public int getNumVal() {
    return numVal;
  }
}
