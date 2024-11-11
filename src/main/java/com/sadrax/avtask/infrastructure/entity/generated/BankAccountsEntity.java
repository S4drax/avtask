package com.sadrax.avtask.infrastructure.entity.generated;
// Generated by Hibernate Tools 5.6.15.Final


import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

/**
 * BankAccountsEntity generated by hbm2java
*/
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="bank_accounts"
)
public class BankAccountsEntity  implements java.io.Serializable {


     private UUID bankAccountId;
     private ClientsEntity clientsEntity;
     private BigDecimal funds;
     private String accountNumber;
     private boolean active;
     private String currencyCode;
     private String createUser;
     private LocalDateTime createDate;
     private String updateUser;
     private LocalDateTime updateDate;

     @GenericGenerator(name="com.sadrax.avtask.infrastructure.entity.generated.BankAccountsEntityIdGenerator", strategy="org.hibernate.id.UUIDGenerator")@Id @GeneratedValue(generator="com.sadrax.avtask.infrastructure.entity.generated.BankAccountsEntityIdGenerator")

    
    @Column(name="bank_account_id", unique=true, nullable=false)
    public UUID getBankAccountId() {
        return this.bankAccountId;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="client_id", nullable=false)
    public ClientsEntity getClientsEntity() {
        return this.clientsEntity;
    }


    @Column(name="funds", nullable=false, precision=30, scale=4)
    public BigDecimal getFunds() {
        return this.funds;
    }


    @Column(name="account_number", nullable=false, length=28)
    public String getAccountNumber() {
        return this.accountNumber;
    }


    @Column(name="active", nullable=false)
    public boolean isActive() {
        return this.active;
    }


    @Column(name="currency_code", nullable=false, length=3)
    public String getCurrencyCode() {
        return this.currencyCode;
    }


    @Column(name="create_user", nullable=false, length=30)
    public String getCreateUser() {
        return this.createUser;
    }


    @Column(name="create_date", nullable=false, length=29)
    public LocalDateTime getCreateDate() {
        return this.createDate;
    }


    @Column(name="update_user", length=30)
    public String getUpdateUser() {
        return this.updateUser;
    }


    @Column(name="update_date", length=29)
    public LocalDateTime getUpdateDate() {
        return this.updateDate;
    }


}

