package com.mislbd.ababil.foreignremittance.service;

public interface ShadowAccountNumberProviderService {

  void makeAccountUsed(String accountNumber);

  int calculateCheckDigit(String accountNumber);

  String getAccountNumber(long productId, long branchId, String username);
}
