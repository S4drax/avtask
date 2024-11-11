package com.sadrax.avtask.application.currencyExchange.rest;


import com.sadrax.application.currencyExchange.api.CurrencyExchangeApi;
import com.sadrax.application.currencyExchange.rto.CurrencyExchangeRTO;
import com.sadrax.application.currencyExchange.rto.CurrencyExchangeRequestRTO;
import com.sadrax.avtask.application.currencyExchange.mapper.CurrencyExchangeRTOMapper;
import com.sadrax.avtask.domain.currencyExchange.CurrencyExchangeService;
import com.sadrax.avtask.domain.currencyExchange.model.CurrencyExchange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CurrencyExchangeController implements CurrencyExchangeApi {

    private final CurrencyExchangeService currencyExchangeService;
    private final CurrencyExchangeRTOMapper mapper;

    @Override
    public ResponseEntity<CurrencyExchangeRTO> getBankAccountInfo(CurrencyExchangeRequestRTO requestRTO) {
        CurrencyExchange currencyExchange = currencyExchangeService.getCurrencyExchange(requestRTO.getCurrencyFrom(), requestRTO.getCurrencyTo(), requestRTO.getValue());
        return ResponseEntity.ok(mapper.mapToRTO(currencyExchange));

    }

}
