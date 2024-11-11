package com.sadrax.avtask.domain.bankAccount;

import com.sadrax.avtask.domain.bankAccount.model.BankAccountInfo;
import com.sadrax.avtask.domain.bankAccount.model.CreateBankAccount;
import com.sadrax.avtask.domain.bankAccount.model.UpdateBankAccount;

import java.util.UUID;

public interface BankAccountRepository {
  BankAccountInfo getBankAccountInfo(UUID id);
  BankAccountInfo createBankAccount(CreateBankAccount createBankAccount, String newBankAccNumber);
  BankAccountInfo updateBankAccount(UpdateBankAccount updateBankAccount);
  void closeBankAccount(UUID id);
}
