package com.sadrax.avtask.infrastructure.client.persistence;

import com.sadrax.avtask.infrastructure.entity.generated.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepositoryJPA extends JpaRepository<ClientsEntity, UUID> {
}
