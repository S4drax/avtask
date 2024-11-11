package com.sadrax.avtask.domain.shared;

import com.sadrax.avtask.domain.shared.model.ExchangeRate;

public interface NbpIntegration {
    ExchangeRate getExchangeRate(String currencyCode);
}
