package com.sam.webfluxpractice.router;

import com.sam.webfluxpractice.handler.FluxAndMonoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> route(FluxAndMonoHandler fluxAndMonoHandler) {

        return RouterFunctions
                .route(getPredicate("/function/flux"), fluxAndMonoHandler::functionalFlux)
                .andRoute(getPredicate("/function/mono"), fluxAndMonoHandler::functionalMono);
    }

    private RequestPredicate getPredicate(String uri) {

        return RequestPredicates.GET(uri).and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
    }
}
