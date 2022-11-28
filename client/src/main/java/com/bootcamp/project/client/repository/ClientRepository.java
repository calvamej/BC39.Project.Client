package com.bootcamp.project.client.repository;

import com.bootcamp.project.client.entity.ClientEntity;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ClientRepository extends ReactiveCrudRepository<ClientEntity, ObjectId> {
}
