package com.company.ledgerservice.application.exception;

public class AccountNotFoundException extends RuntimeException {
    
    public AccountNotFoundException() {
        super("Account not found");
    }
}
