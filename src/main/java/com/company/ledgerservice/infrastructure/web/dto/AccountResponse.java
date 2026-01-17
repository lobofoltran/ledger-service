package com.company.ledgerservice.infrastructure.web.dto;

import java.math.BigDecimal;

public record AccountResponse(
    String id, BigDecimal balance
) {}
