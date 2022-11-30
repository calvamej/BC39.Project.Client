package com.bootcamp.project.client.service;

import com.bootcamp.project.client.entity.ClientEntity;
import com.bootcamp.project.client.exception.CustomInformationException;
import com.bootcamp.project.client.exception.CustomNotFoundException;
import com.bootcamp.project.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.apache.log4j.Logger;

import java.util.Date;

@Service
public class ClientServiceImplementation implements ClientService{
    private static Logger Log = Logger.getLogger(ClientServiceImplementation.class);
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Flux<ClientEntity> getAll() {
        return clientRepository.findAll().switchIfEmpty(Mono.error(new CustomNotFoundException("Clients not found")));
    }
    @Override
    public Mono<ClientEntity> getOne(String documentNumber) {
        return clientRepository.findAll().filter(x -> x.getDocumentNumber().equals(documentNumber)).next();
    }

    @Override
    public Mono<ClientEntity> save(ClientEntity colEnt) {
        return clientRepository.save(colEnt);
    }

    @Override
    public Mono<ClientEntity> update(String documentNumber, String type) {
        return getOne(documentNumber).flatMap(c -> {
            c.setType(type);
            c.setModifyDate(new Date());
            return clientRepository.save(c);
        }).switchIfEmpty(Mono.error(new CustomNotFoundException("Client not found")));
    }

    @Override
    public Mono<Void> delete(String documentNumber) {
        return getOne(documentNumber)
                .switchIfEmpty(Mono.error(new CustomNotFoundException("Client not found")))
                .flatMap(c -> {
            return clientRepository.delete(c);
        });
    }

    @Override
    public Mono<ClientEntity> findByDocument(String documentNumber) {
        return clientRepository.findAll().filter(x -> x.getDocumentNumber().equals(documentNumber)).next()
                .switchIfEmpty(Mono.error(new CustomNotFoundException("Client not found")));
    }
    @Override
    public Mono<ClientEntity> register(ClientEntity colEnt) {
        if(colEnt.getType().equals("P") || colEnt.getType().equals("E")) {
            return getOne(colEnt.getDocumentNumber())
                    .switchIfEmpty(clientRepository.save(colEnt));
        }
        else
        {
            return Mono.error(new CustomInformationException("Client type must be P or E"));
        }
    }
}
