package com.sadrax.avtask.domain.bankAccount.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Client {
    String firstName;
    String lastName;
}
