package com.company.ledgerservice.application;

import com.company.ledgerservice.domain.model.Account;

public class TransferResult {

    private final Account origin;
    private final Account destination;

    public TransferResult(Account origin, Account destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Account getOrigin() {
        return origin;
    }

    public Account getDestination() {
        return destination;
    }
}