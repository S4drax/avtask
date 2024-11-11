package com.sadrax.avtask.infrastructure.bankAccount.mapper;

import com.sadrax.avtask.domain.bankAccount.model.BankAccountInfo;
import com.sadrax.avtask.infrastructure.entity.generated.BankAccountsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper()
public interface BankAccountDTOMapper {

    @Mapping(target = "client.firstName", source = "entity.clientsEntity.firstName")
    @Mapping(target = "client.lastName", source = "entity.clientsEntity.lastName")
    @Mapping(target = "id", source = "entity.bankAccountId")
    @Mapping(target = "currency", source = "entity.currencyCode")
    @Mapping(target = "balance", source = "entity.funds")
    @Mapping(target = "balance2", source = "secondValue")
    BankAccountInfo mapToDomain(BankAccountsEntity entity, BigDecimal secondValue);

}
