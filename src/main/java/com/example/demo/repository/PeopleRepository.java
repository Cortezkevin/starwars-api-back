package com.example.demo.repository;

import com.example.demo.model.People;
import com.example.demo.model.ResponseWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;

@Repository
@RequiredArgsConstructor
public class PeopleRepository {

    private final WebClient webClient;

    public Mono<ResponseWrapper<People>> findAll(String page ){
        return webClient.get()
                .uri("people?page="+ page + "&format=json")
                .retrieve()
                .bodyToMono(String.class)
                .flatMap( res -> {
                    Type type = new TypeToken<ResponseWrapper<People>>(){}.getType();
                    ResponseWrapper<People> peopleResData = new Gson().fromJson(res, type);
                    return Mono.just( peopleResData );
                });
    }

    public Mono<People> findById(String id ){
        return webClient.get()
                .uri("people/"+ id)
                .retrieve()
                .bodyToMono(People.class)
                .onErrorResume( err -> Mono.empty() );
    }

}
