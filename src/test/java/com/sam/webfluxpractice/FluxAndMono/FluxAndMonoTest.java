package com.sam.webfluxpractice.FluxAndMono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
    public void fluxTest() {

        Flux<String> stringFlux = Flux
                .just("Spring", "Spring Boot", "Reactive Spring", "Non blocking Threads")
//                .concatWith(Flux.error(new RuntimeException("Exception Handled")))
                .concatWith(Flux.just("After error"));
//                .log();

        stringFlux.subscribe(
                (s) -> System.out.println("Flux Element: " + s), // Executes for every element of a flux until an error
                (e) -> System.err.println("Exception is: " + e), // Executed when there is an exception
                () -> System.out.println("Flux Completed") // Executed when the flux iteration is completed without exception
        );
    }

    @Test
    public void fluxTestElementsWithoutErrors() {

        Flux<String> stringFlux = Flux
                .just("Spring", "Spring Boot", "Reactive Spring", "Non blocking Threads")
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                .expectNext("Non blocking Threads")
                .verifyComplete();
    }

    @Test
    public void fluxTestElementsWithErrors() {

        Flux<String> stringFlux = Flux
                .just("Spring", "Spring Boot", "Reactive Spring", "Non blocking Threads")
                .concatWith(Flux.error(new RuntimeException("Exception Handled")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring", "Spring Boot", "Reactive Spring", "Non blocking Threads")
//                .expectError(RuntimeException.class)
                .expectErrorMessage("Exception Handled")
                .verify();
    }

    @Test
    public void fluxTestElementsCountWithErrors() {

        Flux<String> stringFlux = Flux
                .just("Spring", "Spring Boot", "Reactive Spring", "Non blocking Threads")
                .concatWith(Flux.error(new RuntimeException("Exception Handled")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(4)  //Exception not counted
//                .expectError(RuntimeException.class)
                .expectErrorMessage("Exception Handled")
                .verify();
    }

    @Test
    public void monoTest() {

        Mono<String> stringMono = Mono.just("Spring");

        StepVerifier
                .create(stringMono.log())
                .expectNext("Spring")
                .verifyComplete();
    }

    @Test
    public void monoTestWithError() {

        Mono<String> stringMono = Mono.error(new RuntimeException());

        StepVerifier
                .create(stringMono.log())
                .expectError(RuntimeException.class)
                .verify();
    }
}
