package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.service.AccountService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/id-accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping
  public ResponseEntity<?> getIDAccounts(
      Pageable pageable, @RequestParam(value = "asPage", required = false) final boolean asPage) {
    if (asPage) {
      PagedResult<Account> pagedResults = accountService.getAccounts(pageable);
      return ResponseEntity.ok(pagedResults);
    } else {
      List<Account> accounts = accountService.getAccounts();
      return ResponseEntity.ok(accounts);
    }
  }
}
