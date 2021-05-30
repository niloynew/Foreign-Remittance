package com.mislbd.ababil.foreignremittance.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.foreignremittance.command.*;
import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.query.AccountQuery;
import com.mislbd.ababil.foreignremittance.service.AccountService;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import java.time.LocalDate;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/id-accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

  private final CommandProcessor commandProcessor;
  private final QueryManager queryManager;
  private final AccountService accountService;

  public AccountController(
      CommandProcessor commandProcessor, QueryManager queryManager, AccountService accountService) {
    this.commandProcessor = commandProcessor;
    this.queryManager = queryManager;
    this.accountService = accountService;
  }

  @GetMapping
  public ResponseEntity<?> getIDAccounts(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "shadowAccountNumber", required = false) final String number,
      @RequestParam(value = "accountTitle", required = false) final String name,
      @RequestParam(value = "nostroAccountNumber", required = false)
          final String nostroAccountNumber,
      @RequestParam(value = "bankId", required = false) final String bank,
      @RequestParam(value = "branchId", required = false) final String branch,
      @RequestParam(value = "accountOpenDate", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          final LocalDate accountOpenDate,
      @RequestParam(value = "currencyCode", required = false) final String currency,
      @RequestParam(value = "productId", required = false) final String product,
      @RequestParam(value = "isActive", required = false) final Boolean isActive) {

    QueryResult<?> queryResult =
        queryManager.executeQuery(
            new AccountQuery(
                pageable,
                asPage,
                number,
                name,
                nostroAccountNumber,
                bank,
                branch,
                accountOpenDate,
                currency,
                product,
                isActive));
    if (queryResult.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(queryResult.getResult());
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(ACCEPTED)
  public ResponseEntity<?> saveAccount(@RequestBody Account account) {
    commandProcessor.executeResult(new SaveShadowAccountCommand(account));
    //    commandProcessor.executeResult(new SaveNostroAccountCommand(account));
    return ResponseEntity.accepted().build();
  }

  @PutMapping(
      path = "/{accountId}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(ACCEPTED)
  public ResponseEntity<Void> updateAccount(
      @PathVariable("accountId") final long accountId, @RequestBody final Account account) {
    commandProcessor.executeUpdate(new UpdateShadowAccountCommand(accountId, account));
    //    commandProcessor.executeUpdate(new UpdateNostroAccountCommand(accountId, account));
    return status(ACCEPTED).build();
  }

  @PutMapping(
      path = "/{accountId}/inactive",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(ACCEPTED)
  public ResponseEntity<Void> inactiveShadowAccount(
      @PathVariable("accountId") final long accountId, @RequestBody final Account account) {
    commandProcessor.executeUpdate(new InactiveShadowAccountCommand(accountId, account));
    //    commandProcessor.executeUpdate(new InactiveNostroAccountCommand(accountId, account));
    return status(ACCEPTED).build();
  }

  @GetMapping(path = "/bic/{BIC}")
  public ResponseEntity<?> getIDAccountsByBIC(@PathVariable("BIC") final String bicCode) {
    return ResponseEntity.ok(accountService.getAccountsByBICCode(bicCode));
  }
}
