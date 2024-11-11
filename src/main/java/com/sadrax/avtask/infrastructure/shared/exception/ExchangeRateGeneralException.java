package com.sadrax.avtask.infrastructure.shared.exception;

public class ExchangeRateGeneralException extends RuntimeException {

  public ExchangeRateGeneralException() {
    super();
  }

  public ExchangeRateGeneralException(String message) {
    super(message);
  }

  public ExchangeRateGeneralException(String message, Throwable cause) {
    super(message, cause);
  }
}
