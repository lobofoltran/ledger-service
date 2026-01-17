package com.company.ledgerservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.ledgerservice.application.LedgerService;
import com.company.ledgerservice.domain.repository.AccountRepository;
import com.company.ledgerservice.infrastructure.repository.InMemoryAccountRepository;

@Configuration
public class LedgerServiceConfig {
    
    @Bean
    public AccountRepository accountRepository() {
        return new InMemoryAccountRepository();
    }

    @Bean
    public LedgerService ledgerService(AccountRepository accountRepository) {
        return new LedgerService(accountRepository);
    }
}
