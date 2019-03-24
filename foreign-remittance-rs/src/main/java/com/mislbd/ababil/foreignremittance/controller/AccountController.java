package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.command.SaveNostroAccountCommand;
import com.mislbd.ababil.foreignremittance.command.SaveShadowAccountCommand;
import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.service.AccountService;
import com.mislbd.asset.command.api.CommandProcessor;
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
  private final CommandProcessor commandProcessor;

  public AccountController(AccountService accountService, CommandProcessor commandProcessor) {
    this.accountService = accountService;
    this.commandProcessor = commandProcessor;
  }

  @GetMapping
  public ResponseEntity<?> getIDAccounts(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "number", required = false) final String number,
      @RequestParam(value = "name", required = false) final String name,
      @RequestParam(value = "nostroAccountNumber", required = false)
          final String nostroAccountNumber,
      @RequestParam(value = "bankId", required = false) final String bank,
      @RequestParam(value = "branchId", required = false) final String branch,
      @RequestParam(value = "accountopenDate", required = false) final String accountopenDate,
      @RequestParam(value = "currencyCode", required = false) final String currency,
      @RequestParam(value = "product", required = false) final String product) {
    if (asPage) {
      PagedResult<Account> pagedResults =
          accountService.getAccounts(
              pageable,
              number,
              name,
              nostroAccountNumber,
              bank,
              branch,
              accountopenDate,
              currency,
              product);
      return ResponseEntity.ok(pagedResults);
    } else {
      List<Account> accounts =
          accountService.getAccounts(
              number, name, nostroAccountNumber, bank, branch, accountopenDate, currency, product);
      return ResponseEntity.ok(accounts);
    }
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> saveShadowAccount(@RequestBody Account account) {
    commandProcessor.executeResult(new SaveShadowAccountCommand(account));
    commandProcessor.executeResult(new SaveNostroAccountCommand(account));
    return ResponseEntity.accepted().build();
  }
}
