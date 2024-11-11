package infrastructure.nbp.service

import com.sadrax.avtask.domain.shared.model.ExchangeRate
import com.sadrax.avtask.infrastructure.nbp.service.NbpIntegrationAdapter
import com.sadrax.avtask.infrastructure.nbp.service.NbpProperties
import spock.lang.Specification

import java.net.http.HttpClient
import java.net.http.HttpResponse

class NbpIntegrationAdapterTest extends Specification {
    NbpProperties nbpProperties = new NbpProperties()
    HttpClient httpClientMock
    NbpIntegrationAdapter adapter

    void setup() {
        nbpProperties.setUrl("https://localhost")
        httpClientMock = Mock(HttpClient)
        adapter = new NbpIntegrationAdapter(httpClientMock, nbpProperties)
    }

    static final String MOCK_XML_RESPONSE = """<ExchangeRatesSeries xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <Table>A</Table>
            <Currency>dolar amerykański</Currency>
            <Code>USD</Code>
            <Rates>
                <Rate>
                    <No>218/A/NBP/2024</No>
                    <EffectiveDate>2024-11-08</EffectiveDate>
                    <Mid>4.0117</Mid>
                </Rate>
            </Rates>
        </ExchangeRatesSeries>"""



    def "test fetch and parse exchange rates"() {
        def httpResponseMock = Mock(HttpResponse)
        def code = "USD"
        httpResponseMock.body() >> MOCK_XML_RESPONSE
        httpResponseMock.statusCode() >> 200

        when:
        ExchangeRate rate = adapter.getExchangeRate(code)

        then:
        1 * httpClientMock.send(_, _) >> httpResponseMock
        rate.code == "USD"
        rate.rate == new BigDecimal("4.0117")
    }
}
