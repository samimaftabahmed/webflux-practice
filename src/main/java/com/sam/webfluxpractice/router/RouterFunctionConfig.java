package com.sam.webfluxpractice.router;

import com.sam.webfluxpractice.handler.FluxAndMonoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> route(FluxAndMonoHandler fluxAndMonoHandler) {

        return RouterFunctions
                .route(
                        RequestPredicates
                                .GET("/function/flux")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        fluxAndMonoHandler::functionalFlux)
                .andRoute(
                        RequestPredicates
                                .GET("/function/mono")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        fluxAndMonoHandler::functionalMono);
    }
}
