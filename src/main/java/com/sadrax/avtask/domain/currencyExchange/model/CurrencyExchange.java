package com.sadrax.avtask.domain.currencyExchange.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class CurrencyExchange {
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal value;
}
