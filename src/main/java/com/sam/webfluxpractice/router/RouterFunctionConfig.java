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
                .route(getPredicate("/function/flux", MediaType.APPLICATION_JSON), fluxAndMonoHandler::functionalFlux)
                .andRoute(getPredicate("/function/mono", MediaType.APPLICATION_JSON), fluxAndMonoHandler::functionalMono)
                .andRoute(getPredicate("/function/flux-stream", MediaType.APPLICATION_STREAM_JSON), fluxAndMonoHandler::functionalFluxStream);
    }

    private RequestPredicate getPredicate(String uri, MediaType mediaType) {

        return RequestPredicates.GET(uri).and(RequestPredicates.accept(mediaType));
    }
}
