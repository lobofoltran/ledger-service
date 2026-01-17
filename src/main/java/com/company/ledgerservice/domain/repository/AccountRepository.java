package com.company.ledgerservice.domain.repository;

import java.util.Optional;

import com.company.ledgerservice.domain.model.Account;

public interface AccountRepository {
    Optional<Account> findById(String id);

    Account save(Account account);
}
