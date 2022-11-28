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

    @GetMapping(value = "/FindOne/{document}")
    public Mono<ClientEntity> Get_One(@PathVariable("document") String document){
        return clientService.getOne(document);
    }
    @GetMapping(value = "/FindAll")
    public Flux<ClientEntity> Get_All(){

        return clientService.getAll();
    }
    @PostMapping(value = "/Save")
    public Mono<ClientEntity> Save(@RequestBody ClientEntity col){

        return clientService.save(col);
    }
    @PutMapping(value = "/Update/{document}/{type}")
    public Mono<ClientEntity> Update(@PathVariable("document") String document,@PathVariable("type") String type){
        return clientService.update(document, type);
    }
    @DeleteMapping  (value = "/Delete/{document}")
    public Mono<Void> Delete(@PathVariable("document") String document){
        return clientService.delete(document);
    }

    @PostMapping(value = "/register")
    public Mono<ResponseEntity<ClientEntity>> registerClient(@RequestBody ClientEntity col){
        return clientService.registerClient(col).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
    @GetMapping(value = "/findClient/{document}")
    public Mono<ResponseEntity<ClientEntity>> findClientByDocument(@PathVariable("document") String document){
        return clientService.findClientByDocument(document).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
