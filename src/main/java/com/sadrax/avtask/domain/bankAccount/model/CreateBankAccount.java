package com.sadrax.avtask.domain.bankAccount.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Builder
@Value
public class CreateBankAccount {
    String firstName;
    String lastName;
    BigDecimal balance;
    String currency;
}
