package com.sadrax.avtask.application.currencyExchange.mapper;

import com.sadrax.application.currencyExchange.rto.CurrencyExchangeRTO;
import com.sadrax.avtask.domain.currencyExchange.model.CurrencyExchange;
import org.mapstruct.Mapper;

@Mapper()
public interface CurrencyExchangeRTOMapper {
    CurrencyExchangeRTO mapToRTO(CurrencyExchange currencyExchange);
}
