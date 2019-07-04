package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.AccountStatement;
import com.mislbd.ababil.foreignremittance.service.AccountStatementService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountStatementController {

  private final AccountStatementService accountStatementService;

  public AccountStatementController(AccountStatementService accountStatementService) {
    this.accountStatementService = accountStatementService;
  }

  @GetMapping(path = "/account-statement")
  public ResponseEntity<?> getAccountStatements(
      @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "accountId", required = false) final Long accountId,
      @RequestParam(value = "fromDate", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          final LocalDate fromDate,
      @RequestParam(value = "toDate", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          final LocalDate toDate) {
    if (asPage) {
      pageNumber = pageNumber != null ? pageNumber : 0;
      Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by("txnDate").ascending());
      PagedResult<AccountStatement> accountStatementPaged =
          accountStatementService.getAccountStatements(pageable, accountId, fromDate, toDate);
      return ResponseEntity.ok(accountStatementPaged);
    } else {
      List<AccountStatement> accountStatementList =
          accountStatementService.getAccountStatements(accountId, fromDate, toDate);
      return ResponseEntity.ok(accountStatementList);
    }
  }
}
