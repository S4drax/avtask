package com.sadrax.avtask.infrastructure.entity.generated;
// Generated by Hibernate Tools 5.6.15.Final


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * ClientsEntity generated by hbm2java
 */
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "clients"
)
public class ClientsEntity implements java.io.Serializable {

    private UUID clientId;
    private AddressesEntity addressesEntityByAddressId;
    private AddressesEntity addressesEntityByBillingAddressId;
    private String firstName;
    private String lastName;
    private String email;
    private String createUser;
    private LocalDateTime createDate;
    private String updateUser;
    private LocalDateTime updateDate;
    @Builder.Default
    private Set<BankAccountsEntity> bankAccountsEntities = new HashSet<BankAccountsEntity>(0);

    @GenericGenerator(name = "com.sadrax.avtask.infrastructure.entity.generated.ClientsEntityIdGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @GeneratedValue(generator = "com.sadrax.avtask.infrastructure.entity.generated.ClientsEntityIdGenerator")


    @Column(name = "client_id", unique = true, nullable = false)
    public UUID getClientId() {
        return this.clientId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    public AddressesEntity getAddressesEntityByAddressId() {
        return this.addressesEntityByAddressId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_address_id")
    public AddressesEntity getAddressesEntityByBillingAddressId() {
        return this.addressesEntityByBillingAddressId;
    }


    @Column(name = "first_name", length = 50)
    public String getFirstName() {
        return this.firstName;
    }


    @Column(name = "last_name", length = 50)
    public String getLastName() {
        return this.lastName;
    }


    @Column(name = "email", length = 50)
    public String getEmail() {
        return this.email;
    }


    @Column(name = "create_user", nullable = false, length = 30)
    public String getCreateUser() {
        return this.createUser;
    }


    @Column(name = "create_date", nullable = false, length = 29)
    public LocalDateTime getCreateDate() {
        return this.createDate;
    }


    @Column(name = "update_user", length = 30)
    public String getUpdateUser() {
        return this.updateUser;
    }


    @Column(name = "update_date", length = 29)
    public LocalDateTime getUpdateDate() {
        return this.updateDate;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "clientsEntity")
    public Set<BankAccountsEntity> getBankAccountsEntities() {
        return this.bankAccountsEntities;
    }


}

