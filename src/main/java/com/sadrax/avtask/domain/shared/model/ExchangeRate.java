package com.sadrax.avtask.domain.shared.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ExchangeRate {
    private String code;
    private BigDecimal rate;
}
