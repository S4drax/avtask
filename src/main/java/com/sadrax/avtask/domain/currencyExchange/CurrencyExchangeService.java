package com.sadrax.avtask.domain.currencyExchange;

import com.sadrax.avtask.domain.currencyExchange.model.CurrencyExchange;

import java.math.BigDecimal;

public interface CurrencyExchangeService {
    CurrencyExchange getCurrencyExchange(String codeFrom, String codeTo, BigDecimal value);
}
