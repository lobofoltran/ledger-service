package com.company.ledgerservice.infrastructure.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.company.ledgerservice.domain.model.Account;
import com.company.ledgerservice.domain.repository.AccountRepository;

public class InMemoryAccountRepository implements AccountRepository {

    private final Map<String, Account> store = new ConcurrentHashMap<>();

    @Override
    public Optional<Account> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public Account save(Account account) {
        store.put(account.getId(), account);

        return account;
    }
}
