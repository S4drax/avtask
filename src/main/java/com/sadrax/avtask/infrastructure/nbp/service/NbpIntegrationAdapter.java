package com.sadrax.avtask.infrastructure.nbp.service;

import com.sadrax.avtask.domain.shared.NbpIntegration;
import com.sadrax.avtask.domain.shared.model.ExchangeRate;
import com.sadrax.avtask.infrastructure.nbp.model.ExchangeRatesSeriesRTO;
import com.sadrax.avtask.infrastructure.shared.exception.ExchangeRateGeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class NbpIntegrationAdapter implements NbpIntegration {
    @Autowired
    private final HttpClient client;
    private final NbpProperties properties;

    @Cacheable(value = "exchangeRatesCache", key = "#currencyCode")
    @Override
    public ExchangeRate getExchangeRate(String currencyCode) {
        final String xmlResponse = fetchExchangeRates(currencyCode);
        ExchangeRatesSeriesRTO unmarshalled = parseXMLToExchangeRatesSeries(xmlResponse);
        if (unmarshalled == null) {
            throw new ExchangeRateGeneralException("Error while unmarshalling the rate response, probably due to response being null.");
        }
        return ExchangeRate.builder()
                .code(unmarshalled.getCode())
                .rate(unmarshalled.getRates().stream().findFirst().get().getMid())
                .build();
    }


    private String fetchExchangeRates(String currencyCode) {
        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(properties.getUrl() + currencyCode))
                    .header("Accept", "application/xml") // Ensure XML response
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                log.error("Failed to fetch data. HTTP status code: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            log.error("Error while getting NBP response", e);
        }
        return null;
    }

    private ExchangeRatesSeriesRTO parseXMLToExchangeRatesSeries(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(ExchangeRatesSeriesRTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (ExchangeRatesSeriesRTO) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            log.error("Error while getting unmarshalling XML.", e);
        }
        return null;
    }
}
