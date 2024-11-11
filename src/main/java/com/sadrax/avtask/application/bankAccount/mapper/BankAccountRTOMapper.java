package com.sadrax.avtask.application.bankAccount.mapper;

import com.sadrax.application.bankAccount.rto.BankAccountInfoRTO;
import com.sadrax.application.bankAccount.rto.CreateBankAccountRequestRTO;
import com.sadrax.application.bankAccount.rto.UpdateBankAccountRequestRTO;
import com.sadrax.avtask.domain.bankAccount.model.BankAccountInfo;
import com.sadrax.avtask.domain.bankAccount.model.CreateBankAccount;
import com.sadrax.avtask.domain.bankAccount.model.UpdateBankAccount;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper()
public interface BankAccountRTOMapper {
    BankAccountInfoRTO mapInfoToRTO(BankAccountInfo bankAccountInfo);
    CreateBankAccount mapCreateToDomain(CreateBankAccountRequestRTO createBankAccountRequest);
    UpdateBankAccount mapUpdateToDomain(UUID id, UpdateBankAccountRequestRTO updateRequest);
}
