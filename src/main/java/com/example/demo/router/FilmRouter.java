package com.example.demo.router;

import com.example.demo.handler.FilmHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FilmRouter {

    private static final String PATH = "api/films";

    @Bean
    RouterFunction<ServerResponse> filmRoute(FilmHandler filmHandler){
        return RouterFunctions.route()
                .GET( PATH, filmHandler::getAll)
                .GET( PATH + "/{id}", filmHandler::getById)
                .GET( PATH + "/getMany/{ids}", filmHandler::getByManyIds)
                .build();
    }
}
