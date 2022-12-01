package com.bootcamp.project.client.service;

import com.bootcamp.project.client.entity.ClientEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    public Flux<ClientEntity> getAll();
    public Mono<ClientEntity> getOne(String documentNumber);

    public Mono<ClientEntity> save(ClientEntity colEnt);
    public Mono<ClientEntity> update(String documentNumber, String type);
    public Mono<Void> delete(String documentNumber);

    public Mono<ClientEntity> findByDocument(String documentNumber);
    public Mono<ClientEntity> register(ClientEntity colEnt);
    public Mono<Boolean> checkClient(String documentNumber);
}
