package com.sadrax.avtask.infrastructure.bankAccount.persistence;

import com.sadrax.avtask.infrastructure.entity.generated.BankAccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankAccountRepositoryJPA extends JpaRepository<BankAccountsEntity, UUID> {
}
