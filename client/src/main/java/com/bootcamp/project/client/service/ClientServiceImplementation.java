package com.bootcamp.project.client.service;

import com.bootcamp.project.client.entity.ClientEntity;
import com.bootcamp.project.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.apache.log4j.Logger;

@Service
public class ClientServiceImplementation implements ClientService{
    private static Logger Log = Logger.getLogger(ClientServiceImplementation.class);
    @Autowired
    private ClientRepository clientRepository;
    /*
        CRUD BÁSICO
    */
    @Override
    public Mono<ClientEntity> getOne(String document) {
        Log.info("Inicio método getOne.");
        Mono<ClientEntity> col = clientRepository.findAll().filter(x -> x.getDocument().equals(document)).next();
        return col;
    }

    @Override
    public Flux<ClientEntity> getAll() {
        Log.info("Inicio método getAll.");
        Flux<ClientEntity> col = clientRepository.findAll();
        return col;
    }

    @Override
    public Mono<ClientEntity> save(ClientEntity colEnt) {
        Log.info("Inicio método save.");
        return clientRepository.save(colEnt);
    }

    @Override
    public Mono<ClientEntity> update(String document, String type) {
        Log.info("Inicio método update.");
        Mono<ClientEntity> col = getOne(document);
        ClientEntity newCol = col.block();
        newCol.setType(type);
        return clientRepository.save(newCol);
    }

    @Override
    public Mono<Void> delete(String document) {
        Log.info("Inicio método delete.");
        Mono<ClientEntity> col = getOne(document);
        ClientEntity newCol = col.block();
        return clientRepository.delete(newCol);
    }
        /*
         MÉTODOS DE NEGOCIO
    */

    @Override
    public Mono<ClientEntity> findClientByDocument(String document) {
        Log.info("Inicio método findByDocument.");
        Mono<ClientEntity> col = clientRepository.findAll().filter(x -> x.getDocument().equals(document)).next();
        return col;
    }
    @Override
    public Mono<ClientEntity> registerClient(ClientEntity colEnt) {
        Log.info("Inicio método registerClient.");
        if(colEnt.equals("P") || colEnt.equals("E")) {
            return findClientByDocument(colEnt.getDocument())
                    .switchIfEmpty(clientRepository.save(colEnt));
        }
        else
        {
            return Mono.just(new ClientEntity());
        }
    }
}
