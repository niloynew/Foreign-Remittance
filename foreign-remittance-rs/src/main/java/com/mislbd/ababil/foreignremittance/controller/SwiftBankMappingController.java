package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.query.TransactionTypeQuery;
import com.mislbd.ababil.foreignremittance.query.TxnBanksQuery;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "txn-banks", produces = MediaType.APPLICATION_JSON_VALUE)
public class SwiftBankMappingController {
    private final QueryManager queryManager;

    public SwiftBankMappingController(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @GetMapping
    public ResponseEntity<?> getTransactionBanks(
            Pageable pageable,
            @RequestParam(required = true, name = "txnId") Long transactionId,
            @RequestParam(name = "asPage") final boolean asPage
            ) {
        QueryResult<?> queryResult =
                queryManager.executeQuery(new TxnBanksQuery(pageable, transactionId, asPage));
        return ResponseEntity.ok(queryResult.getResult());
    }

}
