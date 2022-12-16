package com.bootcamp.project.client.controller;

import com.bootcamp.project.client.entity.ClientEntity;
import com.bootcamp.project.client.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebFluxTest(ClientController.class)
public class ClientControllerImplementationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ClientService clientService;

    @Test
    public void save()
    {
        ClientEntity OE = new ClientEntity();
        Mono<ClientEntity> MTest = Mono.just(OE);
        when(clientService.save(OE)).thenReturn(MTest);
        webTestClient.post().uri("/Client/Save")
                .body(Mono.just(MTest),ClientEntity.class)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void update()
    {
        ClientEntity OE = new ClientEntity();
        Mono<ClientEntity> MTest = Mono.just(OE);
        when(clientService.update("ABC","VIP")).thenReturn(MTest);
        webTestClient.put().uri("/Client/Update/ABC/400")
                .body(Mono.just(MTest),ClientEntity.class)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void delete()
    {
        given(clientService.delete(any())).willReturn(Mono.empty());
        webTestClient.delete().uri("/Client/Delete/ABC")
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void getOne()
    {
        ClientEntity OE = new ClientEntity(null,"AAA",null,null,null,null,null,null,null,null,null);
        Mono<ClientEntity> MTest = Mono.just(OE);
        when(clientService.getOne(any())).thenReturn(MTest);
        Flux<ClientEntity> responseBody = webTestClient.get().uri("/Client/FindOne/AAA")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ClientEntity.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(p -> p.getDocumentNumber().equals("AAA"))
                .verifyComplete();
    }
    @Test
    public void getAll()
    {
        ClientEntity OE = new ClientEntity(null,"AAA",null,null,null,null,null,null,null,null,null);
        ClientEntity OE2 = new ClientEntity(null,"AAa",null,null,null,null,null,null,null,null,null);
        Flux<ClientEntity> MTest = Flux.just(OE,OE2);
        when(clientService.getAll()).thenReturn(MTest);
        Flux<ClientEntity> responseBody = webTestClient.get().uri("/Client/FindAll")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ClientEntity.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(OE)
                .expectNext(OE2)
                .verifyComplete();
    }
}
