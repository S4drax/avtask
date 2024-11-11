package com.sadrax.avtask.domain.bankAccount;

import com.sadrax.avtask.domain.bankAccount.model.BankAccountInfo;
import com.sadrax.avtask.domain.bankAccount.model.CreateBankAccount;
import com.sadrax.avtask.domain.bankAccount.model.UpdateBankAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DomainBankAccountService implements BankAccountService {

    private static final int ACCOUNT_PREFIX = 12345678;
    private static final long ACCOUNT_MIN = 10000000000000000L;
    private static final long ACCOUNT_MAX = 19999999999999999L;

    private final BankAccountRepository bankAccountRepository;
    @Override
    public BankAccountInfo getBankAccountInfo(UUID id) {
        return bankAccountRepository.getBankAccountInfo(id);
    }

    @Override
    public BankAccountInfo createBankAccount(CreateBankAccount createBankAccount) {
        String newBankAccount = generateAccountNumber();
        return bankAccountRepository.createBankAccount(createBankAccount, newBankAccount);
    }

    @Override
    public BankAccountInfo updateBankAccount(UpdateBankAccount updateBankAccount) {
        return bankAccountRepository.updateBankAccount(updateBankAccount);
    }

    @Override
    public void closeBankAccount(UUID id) {
        bankAccountRepository.closeBankAccount(id);
    }

    private String generateAccountNumber() {
        //Normally I'd put a sequence and fill the rest with zeros, or do sequence with same range as min/max, but i'm kinda pressed for time
        long randomPart = new Random().nextLong(ACCOUNT_MAX - ACCOUNT_MIN + 1) + ACCOUNT_MIN;
        //PL -> 2521
        String startingNumber = ACCOUNT_PREFIX + Long.toString(randomPart).substring(1)+"252100";
        int controlSum = calculateMod97(startingNumber);
        return "PL" +
                controlSum +
                ACCOUNT_PREFIX +
                Long.toString(randomPart).substring(1);
    }

    private int calculateMod97(String number) {
        int remainder = 0;
        for (int i = 0; i < number.length(); i++) {
            // Process each digit in groups, adding remainder from previous group
            remainder = (remainder * 10 + Character.getNumericValue(number.charAt(i))) % 97;
        }
        return remainder;
    }
}
