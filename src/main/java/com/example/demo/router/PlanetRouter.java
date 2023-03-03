package com.example.demo.router;

import com.example.demo.handler.PlanetHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PlanetRouter {

    private static final String PATH = "api/planets";

    @Bean
    RouterFunction<ServerResponse> planetRoute(PlanetHandler planetHandler){
        return RouterFunctions.route()
                .GET( PATH, planetHandler::getAll)
                .GET( PATH + "/{id}", planetHandler::getById)
                .GET( PATH + "/getMany/{ids}", planetHandler::getByManyIds)
                .build();
    }
}
