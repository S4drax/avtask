package com.sadrax.avtask.application.bankAccount.rest;


import com.sadrax.application.bankAccount.api.BankAccountApi;
import com.sadrax.application.bankAccount.rto.BankAccountInfoRTO;
import com.sadrax.application.bankAccount.rto.CreateBankAccountRequestRTO;
import com.sadrax.application.bankAccount.rto.UpdateBankAccountRequestRTO;
import com.sadrax.avtask.application.bankAccount.mapper.BankAccountRTOMapper;
import com.sadrax.avtask.domain.bankAccount.BankAccountService;
import com.sadrax.avtask.domain.bankAccount.model.BankAccountInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BankAccountController implements BankAccountApi {

    private final BankAccountService bankAccountService;
    private final BankAccountRTOMapper mapper;

    @Override
    public ResponseEntity<BankAccountInfoRTO> getBankAccountInfo(UUID id) {
        BankAccountInfo bankAccountInfo = bankAccountService.getBankAccountInfo(id);
        return ResponseEntity.ok(mapper.mapInfoToRTO(bankAccountInfo));

    }

    @Override
    public ResponseEntity<BankAccountInfoRTO> updateBankAccount(UUID id, UpdateBankAccountRequestRTO updateBankAccountRequest) {
        BankAccountInfo bankAccountInfo = bankAccountService.updateBankAccount(mapper.mapUpdateToDomain(id, updateBankAccountRequest));

        return ResponseEntity.ok(mapper.mapInfoToRTO(bankAccountInfo));
    }

    @Override
    public ResponseEntity<BankAccountInfoRTO> createBankAccount(CreateBankAccountRequestRTO createBankAccountRequestRTO) {
        BankAccountInfo bankAccountInfo = bankAccountService.createBankAccount(mapper.mapCreateToDomain(createBankAccountRequestRTO));
        return ResponseEntity.ok(mapper.mapInfoToRTO(bankAccountInfo));
    }

    @Override
    public ResponseEntity<Void> closeBankAccount(UUID id) {
        bankAccountService.closeBankAccount(id);
        return ResponseEntity.noContent().build();
    }

}
