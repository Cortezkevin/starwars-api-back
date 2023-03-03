package com.example.demo.router;

import com.example.demo.handler.SpeciesHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SpeciesRouter {

    private static final String PATH = "api/species";

    @Bean
    RouterFunction<ServerResponse> speciesRoute(SpeciesHandler speciesHandler){
        return RouterFunctions.route()
                .GET( PATH, speciesHandler::getAll)
                .GET( PATH + "/{id}", speciesHandler::getById)
                .GET( PATH + "/getMany/{ids}", speciesHandler::getByManyIds)
                .build();
    }

}
