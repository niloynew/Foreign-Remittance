package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.organization.repository.projection.AllBankBranchesProjection;
import com.mislbd.ababil.organization.service.AllBankBranchesService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final ShadowAccountService shadowAccountService;
  private final AllBankBranchesService branchService;

  public AccountServiceImpl(
      ShadowAccountService shadowAccountService, AllBankBranchesService branchService) {
    this.shadowAccountService = shadowAccountService;
    this.branchService = branchService;
  }

  /*
   * Fetch all the active accounts from NOSTRO_ACCOUNT table
   * */
  @Override
  public PagedResult<Account> getAccounts(
      Pageable pageable,
      String number,
      String name,
      String nostroAccountNumber,
      String bank,
      String branch,
      LocalDate accountOpenDate,
      String currency,
      String product,
      Boolean isActive) {
    return shadowAccountService.findActiveAccounts(
        pageable,
        number,
        name,
        nostroAccountNumber,
        bank,
        branch,
        accountOpenDate,
        currency,
        product,
        isActive);
  }

  @Override
  public List<Account> getAccounts(
      String number,
      String name,
      String nostroAccountNumber,
      String bank,
      String branch,
      LocalDate accountOpenDate,
      String currency,
      String product,
      Boolean isActive) {
    return shadowAccountService.findActiveAccounts(
        number,
        name,
        nostroAccountNumber,
        bank,
        branch,
        accountOpenDate,
        currency,
        product,
        isActive);
  }

  @Override
  public Account findById(Long id) {
    return shadowAccountService.findById(id);
  }

  @Override
  public List<Account> getAccountsByBICCode(String bicCode) {
    Optional<AllBankBranchesProjection> branchesProjection =
        branchService.getBranchInformations(bicCode);
    return branchesProjection
        .map(
            allBankBranchesProjection ->
                shadowAccountService.getAccountsByBICCode(allBankBranchesProjection.getBranchId()))
        .orElse(Collections.emptyList());
  }
}
