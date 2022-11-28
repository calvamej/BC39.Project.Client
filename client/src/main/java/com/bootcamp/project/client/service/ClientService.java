package com.bootcamp.project.client.service;

import com.bootcamp.project.client.entity.ClientEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    /*
    CRUD BÁSICO
    */
    public Mono<ClientEntity> getOne(String document);
    public Flux<ClientEntity> getAll();
    public Mono<ClientEntity> save(ClientEntity colEnt);
    public Mono<ClientEntity> update(String document, String type);
    public Mono<Void> delete(String document);

    /*
         MÉTODOS DE NEGOCIO
    */

    public Mono<ClientEntity> findClientByDocument(String document);
    public Mono<ClientEntity> registerClient(ClientEntity colEnt);
}
