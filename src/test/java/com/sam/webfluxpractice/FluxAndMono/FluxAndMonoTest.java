package com.sam.webfluxpractice.FluxAndMono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxAndMonoTest {

    @Test
    public void fluxTest() {

        Flux<String> stringFlux = Flux
                .just("Spring", "Spring Boot", "Reactive Spring", "Non blocking Threads")
//                .concatWith(Flux.error(new RuntimeException("Exception Handled")))
                .concatWith(Flux.just("After error"));
//                .log();

        stringFlux.subscribe(
                (s) -> System.out.println("Flux Element: " +  s), // Executes for every element of a flux until an error
                (e) -> System.err.println("Exception is: " + e), // Executed when there is an exception
                () -> System.out.println("Flux Completed") // Executed when the flux iteration is completed without exception
        );

//        System.out.println(LocalDateTime.now());

    }

}
