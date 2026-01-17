package com.company.ledgerservice.infrastructure.web;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.ledgerservice.application.LedgerService;
import com.company.ledgerservice.application.TransferResult;
import com.company.ledgerservice.domain.model.Account;

@RestController
public class EventController {

    private final LedgerService ledgerService;

    public EventController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @PostMapping("/event")
    public Object handleEvent(@RequestBody Map<String, Object> event) {
        String type = (String) event.get("type");

        switch (type) {
            case "deposit": {
                String destination = (String) event.get("destination");
                BigDecimal amount = new BigDecimal(event.get("amount").toString());

                Account account = ledgerService.deposit(destination, amount);

                return Map.of(
                        "destination", Map.of(
                                "id", account.getId(),
                                "balance", account.getBalance()));
            }
            
            case "withdraw": {
                String origin = (String) event.get("origin");
                BigDecimal amount = new BigDecimal(event.get("amount").toString());

                Account account = ledgerService.withdraw(origin, amount);

                return Map.of(
                        "origin", Map.of(
                                "id", account.getId(),
                                "balance", account.getBalance()));
            }

            case "transfer": {
                String origin = (String) event.get("origin");
                String destination = (String) event.get("destination");
                BigDecimal amount = new BigDecimal(event.get("amount").toString());

                TransferResult result = ledgerService.transfer(origin, destination, amount);

                return Map.of(
                        "origin", Map.of(
                                "id", result.getOrigin().getId(),
                                "balance", result.getOrigin().getBalance()),
                        "destination", Map.of(
                                "id", result.getDestination().getId(),
                                "balance", result.getDestination().getBalance()));
            }

            default:
                throw new IllegalArgumentException("Invalid event type");
        }
    }
}
