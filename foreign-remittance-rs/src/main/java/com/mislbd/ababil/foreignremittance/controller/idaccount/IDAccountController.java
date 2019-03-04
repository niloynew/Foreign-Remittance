package com.mislbd.ababil.foreignremittance.controller.idaccount;

import com.mislbd.ababil.foreignremittance.command.CreateIdAccountCommand;
import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.service.IdAccountService;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.commons.data.domain.PagedResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(path = "/id-accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class IDAccountController {

    private final IdAccountService idAccountService;
    private final CommandProcessor commandProcessor;


    public IDAccountController(IdAccountService idAccountService, CommandProcessor commandProcessor) {
        this.idAccountService = idAccountService;
        this.commandProcessor = commandProcessor;
    }

    @GetMapping
    public ResponseEntity<PagedResult<?>> findIdAccounts(
            Pageable pageable

            ) {

        PagedResult<Account> pagedAccounts = idAccountService.getAccounts(pageable);


        return ResponseEntity.ok(pagedAccounts);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandResponse<Long>> createIdAccount(
            @RequestBody Account account) {
        return status(CREATED)
                .body(commandProcessor.executeResult(new CreateIdAccountCommand(account)));
    }

}
