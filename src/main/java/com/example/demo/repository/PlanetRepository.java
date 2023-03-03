package com.example.demo.repository;

import com.example.demo.model.Planet;
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
public class PlanetRepository {

    private final WebClient webClient;

    public Mono<ResponseWrapper<Planet>> findAll(String page ){
        return webClient.get()
                .uri("planets?page="+ page + "&format=json")
                .retrieve()
                .bodyToMono(String.class)
                .flatMap( res -> {
                    Type type = new TypeToken<ResponseWrapper<Planet>>(){}.getType();
                    ResponseWrapper<Planet> planetResData = new Gson().fromJson(res, type);
                    return Mono.just( planetResData );
                });
    }

    public Mono<Planet> findById(String id ){
        return webClient.get()
                .uri("planets/"+ id)
                .retrieve()
                .bodyToMono(Planet.class)
                .onErrorResume( err -> Mono.empty() );
    }

}
