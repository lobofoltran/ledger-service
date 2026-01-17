package com.company.ledgerservice.domain.exception;

public class InvalidAmountException extends RuntimeException {

    public InvalidAmountException() {
        super("Amount must be greater than zero");
    }
}
