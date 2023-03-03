package com.example.demo.router;

import com.example.demo.handler.PeopleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PeopleRouter {

    private static final String PATH = "api/people";

    @Bean
    RouterFunction<ServerResponse> peopleRoute(PeopleHandler peopleHandler){
        return RouterFunctions.route()
                .GET( PATH, peopleHandler::getAll)
                .GET( PATH + "/{id}", peopleHandler::getById)
                .GET( PATH + "/getMany/{ids}", peopleHandler::getByManyIds)
                .build();
    }

}
