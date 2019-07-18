package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.query.AccountStatementQuery;
import com.mislbd.ababil.foreignremittance.service.AccountStatementService;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import java.time.LocalDate;
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
  private final QueryManager queryManager;

  public AccountStatementController(
      AccountStatementService accountStatementService, QueryManager queryManager) {
    this.accountStatementService = accountStatementService;
    this.queryManager = queryManager;
  }

  @GetMapping(path = "/account-statements")
  public ResponseEntity<?> getAccountStatement(
      @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
      @RequestParam(value = "accountNumber") final String accountNumber,
      @RequestParam(value = "fromDate", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          final LocalDate fromDate,
      @RequestParam(value = "toDate", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          final LocalDate toDate) {
    pageNumber = pageNumber != null ? pageNumber : 0;
    Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by("id").ascending());
    QueryResult<?> queryResult =
        queryManager.executeQuery(
            new AccountStatementQuery(pageable, accountNumber, fromDate, toDate));
    if (queryResult.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(queryResult.getResult());
  }
}
