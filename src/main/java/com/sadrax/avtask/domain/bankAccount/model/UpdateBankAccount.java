package com.sadrax.avtask.domain.bankAccount.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Value
public class UpdateBankAccount {
    UUID id;
    OperationType operationType;
    BigDecimal balance;
    String currency;
}
