package domain.bankAccount

import com.sadrax.avtask.domain.bankAccount.BankAccountRepository
import com.sadrax.avtask.domain.bankAccount.DomainBankAccountService
import com.sadrax.avtask.domain.bankAccount.model.CreateBankAccount
import spock.lang.Specification

class DomainBankAccountServiceTest extends Specification {
    DomainBankAccountService service
    BankAccountRepository repository

    void setup() {
        repository = Mock(BankAccountRepository)
        service = new DomainBankAccountService(repository)
    }

    def 'should properly generate new account number'() {
        given:
        def createRequest = CreateBankAccount.builder()
                .firstName("FIRST")
                .lastName("LAST")
                .balance(BigDecimal.valueOf(100000L))
                .currency("PLN")
                .build()
        when:
        service.createBankAccount(createRequest)
        then:
        1 * repository.createBankAccount(_, _) >> { CreateBankAccount request, String accountNumber ->
            assert accountNumber.length() == 28
        }
    }
}
