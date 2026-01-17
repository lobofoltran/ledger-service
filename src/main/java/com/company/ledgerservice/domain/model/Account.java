package com.company.ledgerservice.domain.model;

import java.math.BigDecimal;

import com.company.ledgerservice.domain.exception.InsufficientBalanceException;
import com.company.ledgerservice.domain.exception.InvalidAmountException;

public class Account {
    private final String id;
    private BigDecimal balance;

    public Account(String id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        validateAmount(amount);

        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        validateAmount(amount);

        if (balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }

        balance = balance.subtract(amount);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException();
        }
    }
}
