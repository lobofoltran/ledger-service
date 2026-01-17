package com.company.ledgerservice.infrastructure.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.ledgerservice.application.LedgerService;
import com.company.ledgerservice.application.TransferResult;
import com.company.ledgerservice.domain.model.Account;
import com.company.ledgerservice.infrastructure.web.dto.AccountResponse;
import com.company.ledgerservice.infrastructure.web.dto.EventRequest;
import com.company.ledgerservice.infrastructure.web.dto.EventResponse;

@RestController
public class EventController {

    private final LedgerService ledgerService;

    public EventController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @PostMapping("/event")
    public Object handleEvent(@RequestBody EventRequest event) {
        
        switch (event.type()) {
            case "deposit": {
                Account deposited = ledgerService.deposit(event.destination(), event.amount());

                return new EventResponse(
                        null,
                        toAccountResponse(deposited));
            }

            case "withdraw": {
                Account withdrawn = ledgerService.withdraw(event.origin(), event.amount());

                return new EventResponse(
                        toAccountResponse(withdrawn),
                        null);
            }

            case "transfer": {
                TransferResult result = ledgerService.transfer(event.origin(), event.destination(), event.amount());

                return new EventResponse(
                        toAccountResponse(result.getOrigin()),
                        toAccountResponse(result.getDestination()));
            }

            default:
                throw new IllegalArgumentException("Invalid event type");
        }
    }

    private AccountResponse toAccountResponse(Account deposited) {
        return new AccountResponse(
                deposited.getId(),
                deposited.getBalance());
    }
}
