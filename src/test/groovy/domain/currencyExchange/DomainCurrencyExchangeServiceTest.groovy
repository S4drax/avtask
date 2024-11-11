package domain.currencyExchange


import com.sadrax.avtask.domain.currencyExchange.DomainCurrencyExchangeMapper
import com.sadrax.avtask.domain.currencyExchange.DomainCurrencyExchangeMapperImpl
import com.sadrax.avtask.domain.currencyExchange.DomainCurrencyExchangeService
import com.sadrax.avtask.domain.shared.NbpIntegration
import com.sadrax.avtask.domain.shared.model.ExchangeRate
import spock.lang.Specification

class DomainCurrencyExchangeServiceTest extends Specification {
    DomainCurrencyExchangeService service
    DomainCurrencyExchangeMapper mapper
    NbpIntegration nbpIntegration

    void setup() {
        nbpIntegration = Mock(NbpIntegration)
        mapper = new DomainCurrencyExchangeMapperImpl()
        service = new DomainCurrencyExchangeService(mapper, nbpIntegration)
    }


    def 'test proper method calls'(String currTo, String currFrom, String toCall) {
        given:
        BigDecimal testValue = BigDecimal.TWO
        when:
        service.getCurrencyExchange(currTo, currFrom, testValue)
        then:
        1 * nbpIntegration.getExchangeRate(toCall) >> ExchangeRate.builder().rate(BigDecimal.valueOf(4)).code(toCall).build()
        where:
        currTo | currFrom | toCall
        "PLN" | "USD" | currFrom
        "USD" | "PLN" | currTo
    }

    def 'test proper method calls with custom exchange'() {
        given:
        def testValue = BigDecimal.TWO
        def usd = "USD"
        def gbp = "GBP"
        when:
        service.getCurrencyExchange(usd, gbp, testValue)
        then:
        1 * nbpIntegration.getExchangeRate(usd) >> ExchangeRate.builder().rate(BigDecimal.valueOf(5)).code(gbp).build()
        1 * nbpIntegration.getExchangeRate(gbp) >> ExchangeRate.builder().rate(BigDecimal.valueOf(4)).code(usd).build()
    }
}
