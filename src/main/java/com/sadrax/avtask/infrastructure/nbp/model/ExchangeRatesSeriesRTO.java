package com.sadrax.avtask.infrastructure.nbp.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "ExchangeRatesSeries")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRatesSeriesRTO {

    @XmlElement(name = "Table")
    private String table;

    @XmlElement(name = "Currency")
    private String currency;

    @XmlElement(name = "Code")
    private String code;

    @XmlElementWrapper(name = "Rates")
    @XmlElement(name = "Rate")
    private List<Rate> rates;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Rate {

        @XmlElement(name = "No")
        private String no;

        @XmlElement(name = "EffectiveDate")
        private String effectiveDate;

        @XmlElement(name = "Mid")
        private BigDecimal mid;
    }
}
