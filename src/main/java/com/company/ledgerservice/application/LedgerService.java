package com.company.ledgerservice.application;

import java.math.BigDecimal;

import com.company.ledgerservice.application.exception.AccountNotFoundException;
import com.company.ledgerservice.domain.model.Account;
import com.company.ledgerservice.domain.repository.AccountRepository;

public class LedgerService {

    private final AccountRepository repository;

    public LedgerService(AccountRepository repository) {
        this.repository = repository;
    }

    public BigDecimal getBalance(String accountId) {
        return repository.findById(accountId)
                .map(Account::getBalance)
                .orElseThrow(AccountNotFoundException::new);
    }

    public Account deposit(String accountId, BigDecimal amount) {
        Account account = repository.findById(accountId)
                .orElse(new Account(accountId, BigDecimal.ZERO));

        account.deposit(amount);

        return repository.save(account);
    }

    public Account withdraw(String accountId, BigDecimal amount) {
        Account account = repository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);

        account.withdraw(amount);

        return repository.save(account);
    }

    public TransferResult transfer(String originId, String destinationId, BigDecimal amount) {
        Account originAccount = repository.findById(originId)
                .orElseThrow(AccountNotFoundException::new);

        Account destinationAccount = repository.findById(destinationId)
                .orElse(new Account(destinationId, BigDecimal.ZERO));

        originAccount.withdraw(amount);
        destinationAccount.deposit(amount);

        repository.save(originAccount);
        repository.save(destinationAccount);

        return new TransferResult(originAccount, destinationAccount);
    }
}
