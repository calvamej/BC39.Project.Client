package com.bootcamp.project.client.controller;

import com.bootcamp.project.client.entity.ClientEntity;
import com.bootcamp.project.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/Client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping(value = "/FindOne/{documentNumber}")
    public Mono<ClientEntity> Get_One(@PathVariable("documentNumber") String documentNumber){
        return clientService.getOne(documentNumber);
    }
    @GetMapping(value = "/FindAll")
    public Flux<ClientEntity> Get_All(){

        return clientService.getAll();
    }
    @PostMapping(value = "/Save")
    public Mono<ClientEntity> Save(@RequestBody ClientEntity col){

        return clientService.save(col);
    }
    @PutMapping(value = "/Update/{documentNumber}/{type}")
    public Mono<ClientEntity> Update(@PathVariable("documentNumber") String documentNumber,@PathVariable("type") String type){
        return clientService.update(documentNumber, type);
    }
    @DeleteMapping  (value = "/Delete/{documentNumber}")
    public Mono<Void> Delete(@PathVariable("documentNumber") String documentNumber){
        return clientService.delete(documentNumber);
    }

    @PostMapping(value = "/Register")
    public Mono<ClientEntity> registerClient(@RequestBody ClientEntity col){
        return clientService.register(col);
    }
    @GetMapping(value = "/FindByDocument/{documentNumber}")
    public Mono<ClientEntity> findClientByDocument(@PathVariable("documentNumber") String documentNumber){
        return clientService.findByDocument(documentNumber);
    }
    @GetMapping(value = "/CheckClient/{documentNumber}")
    public Mono<Boolean> checkClient(@PathVariable("documentNumber") String documentNumber){
        return clientService.checkClient(documentNumber);
    }
}
