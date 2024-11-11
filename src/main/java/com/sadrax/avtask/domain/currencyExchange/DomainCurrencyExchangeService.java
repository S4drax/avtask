package com.sadrax.avtask.domain.currencyExchange;


import com.sadrax.avtask.domain.currencyExchange.model.CurrencyExchange;
import com.sadrax.avtask.domain.shared.NbpIntegration;
import com.sadrax.avtask.domain.shared.model.ExchangeRate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@RequiredArgsConstructor
public class DomainCurrencyExchangeService implements CurrencyExchangeService{
    private final DomainCurrencyExchangeMapper mapper;
    private final NbpIntegration nbpIntegration;
    private static final String PLN = "PLN";
    @Override
    public CurrencyExchange getCurrencyExchange(String codeFrom, String codeTo, BigDecimal value) {
        if (codeFrom.equals(PLN) && !codeTo.equals(PLN)) {
            return exchangeFromPln(codeFrom, codeTo, value);
        } else if (!codeFrom.equals(PLN) && codeTo.equals(PLN)) {
            return exchangeToPln(codeFrom, codeTo, value);
        } else {
            return exchangeCustom(codeFrom, codeTo, value);
        }
    }

    private CurrencyExchange exchangeCustom(String codeFrom, String codeTo, BigDecimal value) {
        ExchangeRate rateFrom = nbpIntegration.getExchangeRate(codeFrom);
        ExchangeRate rateTo = nbpIntegration.getExchangeRate(codeTo);
        BigDecimal newValue = value.multiply(rateFrom.getRate()).divide(rateTo.getRate(), 4, RoundingMode.HALF_UP);
        return mapper.buildCurrencyExchange(codeFrom, codeTo, newValue);
    }

    private CurrencyExchange exchangeToPln(String codeFrom, String codeTo, BigDecimal value) {
        ExchangeRate rate = nbpIntegration.getExchangeRate(codeFrom);
        BigDecimal newValue = value.multiply(rate.getRate());
        return mapper.buildCurrencyExchange(codeFrom, codeTo, newValue);
    }

    private CurrencyExchange exchangeFromPln(String codeFrom, String codeTo, BigDecimal value) {
        ExchangeRate rate = nbpIntegration.getExchangeRate(codeTo);
        BigDecimal newValue = value.divide(rate.getRate(), 4, RoundingMode.HALF_UP);
        return mapper.buildCurrencyExchange(codeFrom, codeTo, newValue);
    }
}
