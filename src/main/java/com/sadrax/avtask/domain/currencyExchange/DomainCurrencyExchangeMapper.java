package com.sadrax.avtask.domain.currencyExchange;

import com.sadrax.avtask.domain.currencyExchange.model.CurrencyExchange;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Mapper
public interface DomainCurrencyExchangeMapper {
    default CurrencyExchange buildCurrencyExchange(String codeFrom, String codeTo, BigDecimal newValue) {
        return CurrencyExchange.builder()
                .currencyFrom(codeFrom)
                .currencyTo(codeTo)
                .value(newValue)
                .build();
    }
}
