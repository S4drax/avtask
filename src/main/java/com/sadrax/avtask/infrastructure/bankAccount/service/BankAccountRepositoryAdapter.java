package com.sadrax.avtask.infrastructure.bankAccount.service;

import com.sadrax.avtask.domain.bankAccount.BankAccountRepository;
import com.sadrax.avtask.domain.bankAccount.model.BankAccountInfo;
import com.sadrax.avtask.domain.bankAccount.model.CreateBankAccount;
import com.sadrax.avtask.domain.bankAccount.model.UpdateBankAccount;
import com.sadrax.avtask.domain.currencyExchange.CurrencyExchangeService;
import com.sadrax.avtask.domain.currencyExchange.model.CurrencyExchange;
import com.sadrax.avtask.infrastructure.bankAccount.mapper.BankAccountDTOMapper;
import com.sadrax.avtask.infrastructure.bankAccount.persistence.BankAccountRepositoryJPA;
import com.sadrax.avtask.infrastructure.client.persistence.ClientRepositoryJPA;
import com.sadrax.avtask.infrastructure.entity.generated.BankAccountsEntity;
import com.sadrax.avtask.infrastructure.entity.generated.ClientsEntity;
import com.sadrax.avtask.infrastructure.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankAccountRepositoryAdapter implements BankAccountRepository {
    private final BankAccountDTOMapper mapper;
    private final BankAccountRepositoryJPA bankAccountRepository;
    private final ClientRepositoryJPA clientRepositoryJPA;
    private final CurrencyExchangeService currencyExchangeService;
    @Override
    public BankAccountInfo getBankAccountInfo(UUID id) {
        BankAccountsEntity bankAccountsEntity = bankAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bank Account not found"));
        BigDecimal secondCurrencValue = getSecondCurrencyValue(bankAccountsEntity.getCurrencyCode(), bankAccountsEntity.getFunds());
        return mapper.mapToDomain(bankAccountsEntity, secondCurrencValue);
    }

    @Override
    public BankAccountInfo createBankAccount(CreateBankAccount createBankAccount, String newBankAccNumber) {
        ClientsEntity newClient = ClientsEntity.builder()
                .firstName(createBankAccount.getFirstName())
                .lastName(createBankAccount.getLastName())
                .createUser("SYSTEM")
                .createDate(LocalDateTime.now())
                .updateUser("SYSTEM")
                .updateDate(LocalDateTime.now())
                .build();
        newClient = clientRepositoryJPA.save(newClient);
        BankAccountsEntity newBankAccount = BankAccountsEntity.builder()
                .currencyCode(createBankAccount.getCurrency())
                .accountNumber(newBankAccNumber)
                .funds(createBankAccount.getBalance())
                .clientsEntity(newClient)
                .createUser("SYSTEM")
                .createDate(LocalDateTime.now())
                .updateUser("SYSTEM")
                .updateDate(LocalDateTime.now())
                .build();
        BigDecimal secondCurrencValue = getSecondCurrencyValue(newBankAccount.getCurrencyCode(), newBankAccount.getFunds());

        return mapper.mapToDomain(bankAccountRepository.save(newBankAccount), secondCurrencValue);
    }

    @Override
    public BankAccountInfo updateBankAccount(UpdateBankAccount updateBankAccount) {
        BankAccountsEntity bankAccountsEntity = bankAccountRepository.findById(updateBankAccount.getId())
                .orElseThrow(() -> new NotFoundException("Bank Account not found"));
        BigDecimal valueToUse = updateBankAccount.getBalance();
        if (!bankAccountsEntity.getCurrencyCode().equals(updateBankAccount.getCurrency())) {
            CurrencyExchange afterExchange = currencyExchangeService.getCurrencyExchange(updateBankAccount.getCurrency(), bankAccountsEntity.getCurrencyCode(), updateBankAccount.getBalance());
            valueToUse = afterExchange.getValue();
        }
        switch (updateBankAccount.getOperationType()) {
            case PAYMENT -> bankAccountsEntity.setFunds(bankAccountsEntity.getFunds().subtract(valueToUse));
            case INSTALLMENT -> bankAccountsEntity.setFunds(bankAccountsEntity.getFunds().add(valueToUse));
        }
        bankAccountsEntity.setUpdateDate(LocalDateTime.now());
        bankAccountRepository.save(bankAccountsEntity);
        BigDecimal secondCurrencValue = getSecondCurrencyValue(bankAccountsEntity.getCurrencyCode(), bankAccountsEntity.getFunds());

        return mapper.mapToDomain(bankAccountsEntity, secondCurrencValue);
    }

    @Override
    public void closeBankAccount(UUID id) {
        BankAccountsEntity bankAccountsEntity = bankAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bank Account not found"));
        bankAccountsEntity.setActive(false);
        bankAccountRepository.save(bankAccountsEntity);
    }

    private BigDecimal getSecondCurrencyValue(String code, BigDecimal value) {
        if (code.equals("PLN")) {
            return currencyExchangeService.getCurrencyExchange(code, "USD", value).getValue();
        } else {
            return currencyExchangeService.getCurrencyExchange("PLN", code, value).getValue();
        }
    }

}
