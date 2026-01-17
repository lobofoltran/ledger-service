package com.company.ledgerservice.infrastructure.web;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.ledgerservice.application.LedgerService;

@RestController
public class BalanceController {

    private final LedgerService ledgerService;

    public BalanceController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam("account_id") String accountId) {
        BigDecimal balance = ledgerService.getBalance(accountId);

        return ResponseEntity.ok(balance);
    }

}
