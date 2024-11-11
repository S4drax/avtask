package com.sadrax.avtask.domain.bankAccount.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Value
public class BankAccountInfo {
    UUID id;
    Client client;
    String accountNumber;
    String currency;
    BigDecimal balance;
    /** If account is in PLN then USD, if other than PLN then PLN */
    BigDecimal balance2;
}
